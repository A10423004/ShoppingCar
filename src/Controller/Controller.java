package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.CarList;
import Model.CartItem;
import Model.CartListIterator;
import Model.CashOnDeliveryPrice;
import Model.Db;
import Model.Iterator;
import Model.MailPrice;
import Model.ShopList;
import Model.Strategy;
import View.MenuView;

public class Controller {
	private MenuView menuView;
	private ShopList shopList;
	private CarList carList;
	private Strategy strategy;
	private Db shopping;
	
	public Controller(MenuView menuView) {
		this.menuView = menuView;
		this.menuView.show();
		//read the item list from "Item.txt"
		shopping = Db.getProduct();
		shopList = shopping.readItem();
		carList = new CarList();
		//set default transport method
		menuView.setTransportmethodText("郵寄");
		//load ShopItem list
		loadItemList();
		//show CarItem list
		showCarItems();
		/*actionlistener setting*/
		//商品清單監聽器
		menuView.addShopListSelectionListener(new ShopListSelectionListener());
		//>按鈕監聽器
		menuView.addButtonBuyActionListener(new ButtonBuyActionListener());
		//<按鈕監聽器
		menuView.addButtonDelActionListener(new ButtonDelActionListener());
		//Bill按鈕監聽器
		menuView.addBillActionListener(new BillActionListener());
		//RemoveAllItem按鈕監聽器
		menuView.addRemoveAllItemActionListener(new RemoveAllItemActionListener());
		//CheckOut按鈕監聽器
		menuView.addCheckOutActionListener(new CheckOutActionListener());
		//Exit按鈕監聽器
		menuView.addExitActionListener(new ExitActionListener());
		//Next按鈕監聽器
		menuView.addNextActionListener(new NextActionListener());
		//Bymail按鈕監聽器
		menuView.addRbBymailActionListener(new RbBymailActionListener());
		//Cashondelivery按鈕監聽器
		menuView.addRbCashondeliveryActionListener(new RbCashondeliveryActionListener());
		/*監聽器設定結束*/
		//sert "<、CheckOut、Bill" visibility false
		menuView.setNextEnabled(false);
		menuView.setButtonDelEnabled(false);
		menuView.setCheckOutEnabled(false);
		menuView.setBillEnabled(false);
	}
	//load item list
	public void loadItemList() {
		Iterator iterator = shopList.iterator();
		while(iterator.hasNext()){
			menuView.addShopItem(iterator.Next().getName());
		}
	}
	
	//設定商品列表的監聽器
	class ShopListSelectionListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {
			
			if(menuView.getSelectShopListIndex() >= 0){
				//show shop item price which is selected
				menuView.setShopItemPrice(shopList.getItemAt(menuView.getSelectShopListIndex()).getPrice());
			}
		}
	}
	
	//按下>購買的動作監聽器
	class ButtonBuyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int selectIndex = menuView.getSelectShopListIndex();
			if(selectIndex >= 0){
				String itemName = shopList.getItemAt(selectIndex).getName();
				int num = menuView.getSelectNum();
				double price = shopList.getItemAt(selectIndex).getPrice();
				carList.add(new CartItem(itemName, num, price));
				//update carItem list
				showCarItems();
				//set "<, Checkout" visibility true
				menuView.setButtonDelEnabled(true);
				menuView.setCheckOutEnabled(true);
			}
		}
	}
	
	//update carItem list
	public void showCarItems() {
		//先清空購物車
		menuView.carListClear();
		//然後填入項目
		menuView.addCarItem("No.|                Item Name|  Quantity|    Price|    Subtotal");
		menuView.addCarItem("---+-------------------------+----------+---------+-----------+\n");
		Iterator iterator = carList.iterator();
		int i = 0;
		while(iterator.hasNext()){
			CartItem cartItem = (CartItem) iterator.Next();
			String name = cartItem.getName();
			int quantity = cartItem.getQuantity();
			double price = cartItem.getPrice();
			menuView.addCarItem(String.format("%3d|%25s|%10d|%9.2f|%12.2f\n", i + 1, name, quantity, price, quantity * price));
			i++;
		}
	}
	
	//按下<刪除的動作監聽器
	class ButtonDelActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int selectIndex = menuView.getSelectCarListIndex() - 2;
			int num = menuView.getSelectNum();
			if(selectIndex >= 0){
				carList.remove(selectIndex, num);
				//更新購物車畫面
				showCarItems();
				//if carlist have nothing, then set "<, checkout" visibility false
				if(carList.getLength() == 0){
					menuView.setButtonDelEnabled(false);
					menuView.setCheckOutEnabled(false);
				}
			}
		}
	}
	
	//移除全部的按鈕動作
	class RemoveAllItemActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			carList.removeAll();
			showCarItems();
			menuView.setButtonDelEnabled(false);
			menuView.setCheckOutEnabled(false);
		}
	}
	
	//CheckOut的按鈕動作
	class CheckOutActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			menuView.setNextEnabled(true);
			menuView.setBillEnabled(true);
			//To decide which strategy user select, then set strategy to user selected and pass strategy to carlist
			if(menuView.rbRbBymail_isSelected()){//使用者點選mail方式
				strategy = new MailPrice();
				carList.setStrategy(strategy);
				
			}
			else{//使用者點選Cashondelivery方式
				strategy = new CashOnDeliveryPrice();
				carList.setStrategy(strategy);
			}
			/*以下進入結帳程序*/
			
			//開始結帳
			
			String msg = "The total price is " + carList.getTotalCost() + "NTD.\nThank you and come again.";
			menuView.showCheckOutMessage(msg);
			String str = "";
			str += "No.|            Item Name|  Quantity|    Price|    Subtotal|\n";
			str += "---+-----------------------+----------+---------+------------\n";
			Iterator iterator = carList.iterator();
			int i = 0;
			while(iterator.hasNext()){
				CartItem cartItem = (CartItem) iterator.Next();
				String name = cartItem.getName();
				int quantity = cartItem.getQuantity();
				double price = cartItem.getPrice();
				
				str += String.format("%3d|%21s|%10d|%9.2f|%12.2f\n", i, name, quantity, price, quantity * price);
				i++;
			}
			double transprice = carList.getStrategy();
			str += "---+-----------------------+----------+---------+------------\n";
			str += String.format("Transportprice: %.2f\nTotal: %.2f\n",transprice, carList.getTotalCost());
			try {
				FileWriter fw = new FileWriter("Bill.txt");
				fw.write(str);
				fw.flush();
				fw.close();
				Runtime.getRuntime().exec("cmd /c start Bill.txt");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	//Next按鈕動作
	class NextActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//start new view and close the older view
			MenuView newMenuView = new MenuView();
			menuView.hide();
			Controller newController = new Controller(newMenuView);
		}
	}
		
	//Bill按鈕動作
	class BillActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				Runtime.getRuntime().exec("cmd /c start Bill.txt");
			} catch (IOException e1) {
				// TODO 自動產生的 catch 區塊
				e1.printStackTrace();
			}
		}
	}
	
	//Exit按鈕動作
	class ExitActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	//Bymail按鈕動作
	class RbBymailActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			menuView.setTransportmethodText("郵寄");
		}
	}
	
	//Cashondelivery按鈕動作
	class RbCashondeliveryActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			menuView.setTransportmethodText("貨到付款");
		}
	}
	
}
