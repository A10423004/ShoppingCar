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
		menuView.setTransportmethodText("�l�H");
		//load ShopItem list
		loadItemList();
		//show CarItem list
		showCarItems();
		/*actionlistener setting*/
		//�ӫ~�M���ť��
		menuView.addShopListSelectionListener(new ShopListSelectionListener());
		//>���s��ť��
		menuView.addButtonBuyActionListener(new ButtonBuyActionListener());
		//<���s��ť��
		menuView.addButtonDelActionListener(new ButtonDelActionListener());
		//Bill���s��ť��
		menuView.addBillActionListener(new BillActionListener());
		//RemoveAllItem���s��ť��
		menuView.addRemoveAllItemActionListener(new RemoveAllItemActionListener());
		//CheckOut���s��ť��
		menuView.addCheckOutActionListener(new CheckOutActionListener());
		//Exit���s��ť��
		menuView.addExitActionListener(new ExitActionListener());
		//Next���s��ť��
		menuView.addNextActionListener(new NextActionListener());
		//Bymail���s��ť��
		menuView.addRbBymailActionListener(new RbBymailActionListener());
		//Cashondelivery���s��ť��
		menuView.addRbCashondeliveryActionListener(new RbCashondeliveryActionListener());
		/*��ť���]�w����*/
		//sert "<�BCheckOut�BBill" visibility false
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
	
	//�]�w�ӫ~�C����ť��
	class ShopListSelectionListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {
			
			if(menuView.getSelectShopListIndex() >= 0){
				//show shop item price which is selected
				menuView.setShopItemPrice(shopList.getItemAt(menuView.getSelectShopListIndex()).getPrice());
			}
		}
	}
	
	//���U>�ʶR���ʧ@��ť��
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
		//���M���ʪ���
		menuView.carListClear();
		//�M���J����
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
	
	//���U<�R�����ʧ@��ť��
	class ButtonDelActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int selectIndex = menuView.getSelectCarListIndex() - 2;
			int num = menuView.getSelectNum();
			if(selectIndex >= 0){
				carList.remove(selectIndex, num);
				//��s�ʪ����e��
				showCarItems();
				//if carlist have nothing, then set "<, checkout" visibility false
				if(carList.getLength() == 0){
					menuView.setButtonDelEnabled(false);
					menuView.setCheckOutEnabled(false);
				}
			}
		}
	}
	
	//�������������s�ʧ@
	class RemoveAllItemActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			carList.removeAll();
			showCarItems();
			menuView.setButtonDelEnabled(false);
			menuView.setCheckOutEnabled(false);
		}
	}
	
	//CheckOut�����s�ʧ@
	class CheckOutActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			menuView.setNextEnabled(true);
			menuView.setBillEnabled(true);
			//To decide which strategy user select, then set strategy to user selected and pass strategy to carlist
			if(menuView.rbRbBymail_isSelected()){//�ϥΪ��I��mail�覡
				strategy = new MailPrice();
				carList.setStrategy(strategy);
				
			}
			else{//�ϥΪ��I��Cashondelivery�覡
				strategy = new CashOnDeliveryPrice();
				carList.setStrategy(strategy);
			}
			/*�H�U�i�J���b�{��*/
			
			//�}�l���b
			
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
	
	
	//Next���s�ʧ@
	class NextActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//start new view and close the older view
			MenuView newMenuView = new MenuView();
			menuView.hide();
			Controller newController = new Controller(newMenuView);
		}
	}
		
	//Bill���s�ʧ@
	class BillActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				Runtime.getRuntime().exec("cmd /c start Bill.txt");
			} catch (IOException e1) {
				// TODO �۰ʲ��ͪ� catch �϶�
				e1.printStackTrace();
			}
		}
	}
	
	//Exit���s�ʧ@
	class ExitActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	//Bymail���s�ʧ@
	class RbBymailActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			menuView.setTransportmethodText("�l�H");
		}
	}
	
	//Cashondelivery���s�ʧ@
	class RbCashondeliveryActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			menuView.setTransportmethodText("�f��I��");
		}
	}
	
}
