package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.CarList;
import Model.CartItem;
import Model.CashOnDeliveryPrice;
import Model.Db2;
import Model.Iterator;
import Model.MailPrice;
import Model.ShopList;
import Model.Strategy;
import View.MenuView2;

public class Controller {
	private MenuView2 menuView2;
	private ShopList shopList;
	private CarList carList;
	private Strategy strategy;
	private Db2 db2;
	
	public Controller(MenuView2 menuView2) {
		this.menuView2 = menuView2;
		this.menuView2.show();
		db2 = Db2.getProduct();
		shopList = db2.readItem();
		carList = new CarList();
		//顯示商品列表
		loadItemList();
		//顯示購物車標頭
		showCarItems();
		/*以下為監聽器設定*/
		//商品清單監聽器
		menuView2.addShopListSelectionListener(new ShopListSelectionListener());
		//>按鈕監聽器
		menuView2.addButtonBuyActionListener(new ButtonBuyActionListener());
		//<按鈕監聽器
		menuView2.addButtonDelActionListener(new ButtonDelActionListener());
		//RemoveAllItem按鈕監聽器
		menuView2.addRemoveAllItemActionListener(new RemoveAllItemActionListener());
		//CheckOut按鈕監聽器
		menuView2.addCheckOutActionListener(new CheckOutActionListener());
		//Bill按鈕監聽器
		menuView2.addBillActionListener(new BillActionListener());
		//Exit按鈕監聽器
		menuView2.addExitActionListener(new ExitActionListener());
		//Bymail按鈕監聽器
		menuView2.addRbBymailActionListener(new RbBymailActionListener());
		//Cashondelivery按鈕監聽器
		menuView2.addRbCashondeliveryActionListener(new RbCashondeliveryActionListener());
		/*監聽器設定結束*/
		
		//限制<、CheckOut、Bill按鈕不能點選
		menuView2.setButtonDelEnabled(false);
		menuView2.setCheckOutEnabled(false);
		menuView2.setBillEnabled(false);
	}
	
	//載入商品清單
	private void loadItemList() {
		Iterator iterator = shopList.iterator();
		while(iterator.hasNext()){
			menuView2.addShopItem(iterator.Next().getName());
		}
	}
	
	//設定商品列表的監聽器
	class ShopListSelectionListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {
			//如果按到奇怪的index就不要顯示
			if(menuView2.getSelectShopListIndex() >= 0){
				menuView2.setShopItemPrice(shopList.getItemAt(menuView2.getSelectShopListIndex()).getPrice());
			}
		}
	}
	
	//按下>購買的動作監聽器
	class ButtonBuyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int selectIndex = menuView2.getSelectShopListIndex();
			//有按在項目欄才有動作
			if(selectIndex >= 0){
				String itemName = shopList.getItemAt(selectIndex).getName();
				int num = menuView2.getSelectNum();
				double price = shopList.getItemAt(selectIndex).getPrice();
				carList.add(new CartItem(itemName, num, price));
				//更新購物車畫面
				showCarItems();
				//打開<、CheckOut按鈕供點選
				menuView2.setButtonDelEnabled(true);
				menuView2.setCheckOutEnabled(true);
			}
		}
	}
	
	//更新購物車清單
	public void showCarItems() {
		//先清空購物車
		menuView2.carListClear();
		//然後填入項目
		menuView2.addCarItem("No.|                Item Name|  Quantity|    Price|    Subtotal");
		menuView2.addCarItem("---+-------------------------+----------+---------+-----------+\n");
		Iterator iterator = carList.iterator();
		int i = 0;
		while(iterator.hasNext()){
			CartItem cartItem = (CartItem) iterator.Next();
			String name = cartItem.getName();
			int quantity = cartItem.getQuantity();
			double price = cartItem.getPrice();
			menuView2.addCarItem(String.format("%3d|%25s|%10d|%9.2f|%12.2f\n", i, name, quantity, price, quantity * price));
			i++;
		}
	}
	
	//按下<刪除的動作監聽器
	class ButtonDelActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int selectIndex = menuView2.getSelectCarListIndex() - 2;
			int num = menuView2.getSelectNum();
			//按在標頭欄無動作，按在項目欄才有動作
			if(selectIndex >= 0){
				carList.remove(selectIndex, num);
				//更新購物車畫面
				showCarItems();
				//如果刪光了就不能再刪除與結帳了
				if(carList.getLength() == 0){
					menuView2.setButtonDelEnabled(false);
					menuView2.setCheckOutEnabled(false);
				}
			}
		}
	}
	
	//移除全部的按鈕動作
	class RemoveAllItemActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			carList.removeAll();
			showCarItems();
			menuView2.setButtonDelEnabled(false);
			menuView2.setCheckOutEnabled(false);
		}
	}
	
	//CheckOut的按鈕動作
	class CheckOutActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//判斷Strategy的運送方式是哪一種，再把相對應的Strategy new出來，傳進CarList
			if(menuView2.rbRbBymail_isSelected()){//使用者點選mail方式
				strategy = new MailPrice();
				carList.setStrategy(strategy);
			}
			else if(menuView2.rbCashondelivery_isSelected()){//使用者點選Cashondelivery方式
				strategy = new CashOnDeliveryPrice();
				carList.setStrategy(strategy);
			}else{//若都沒有點選則跳出
				String msg = "請選擇運送方式!!";
				menuView2.showCheckOutMessage(msg);
				return;
			}
			/*以下進入結帳程序*/
			//按鈕全部關閉
			menuView2.setButtonBuyEnabled(false);
			menuView2.setButtonDelEnabled(false);
			menuView2.setRemoveAllItemEnabled(false);
			menuView2.setCheckOutEnabled(false);
			menuView2.setRbBymailEnabled(false);
			menuView2.setRbCashondeliveryEnabled(false);
			//開始結帳
			String msg = "The total price is " + carList.getTotalCost() + "NTD.\nThank you and come again.";
			menuView2.showCheckOutMessage(msg);
			String str = "";
			str += "No.|                Item Name|  Quantity|    Price|    Subtotal\n";
			str += "---+-------------------------+----------+---------+------------\n";
			Iterator iterator = carList.iterator();
			int i = 0;
			while(iterator.hasNext()){
				CartItem cartItem = (CartItem) iterator.Next();
				String name = cartItem.getName();
				int quantity = cartItem.getQuantity();
				double price = cartItem.getPrice();
				str += String.format("%3d|%25s|%10d|%9.2f|%12.2f\n", i, name, quantity, price, quantity * price);
				i++;
			}
			str += "---+-------------------------+----------+---------+------------\n";
			str += String.format("Total: %.2f\n", carList.getTotalCost());
			try {
				FileWriter fw = new FileWriter("Bill.txt");
				fw.write(str);
				fw.flush();
				fw.close();
				Runtime.getRuntime().exec("cmd /c start Bill.txt");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			//最後打開Bill按鈕
			menuView2.setBillEnabled(true);
		}
	}
	
	//Bill按鈕動作
	class BillActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				Runtime.getRuntime().exec("cmd /c start Bill.txt");
			} catch (IOException e1) {
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
			menuView2.setTransportmethodText("郵寄");
		}
	}
	
	//Cashondelivery按鈕動作
	class RbCashondeliveryActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			menuView2.setTransportmethodText("貨到付款");
		}
	}
	
}
