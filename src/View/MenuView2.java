package View;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionListener;
import javax.swing.JRadioButton;
import java.awt.*;

public class MenuView2 {
	private JFrame frmShoppingcart;
	/**
	 * Create the application.
	 */
	public MenuView2() {
		initialize();
	}
	public void show(){
		this.frmShoppingcart.setVisible(true);
	}
	public void hide(){
		this.frmShoppingcart.setVisible(false);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private JLabel lblShopItem;
	private DefaultListModel<String> itemModel;
	private JList<String> itemList;
	private JLabel lblPrice;
	private JButton button_buy;
	private JButton button_del;
	private JLabel lblShoppingcart;
	private DefaultListModel<String> cartModel;
	private JList<String> cartList;
	private JComboBox<Integer> comboBox;
	private JButton btnRemoveallitem;
	private JButton btnCheckOut;
	private JButton btnBill;
	private JButton btnExit;
	private JRadioButton rbBymail;//郵寄按鈕
	private JRadioButton rbCashondelivery;//貨到付款按鈕
	private TextField textTransportmethod;
	private JLabel lbTransportmethod;//運送方式顯示
	private ButtonGroup rbuttonGroup;//群組
	
	private void initialize() {
		frmShoppingcart = new JFrame();
		frmShoppingcart.setTitle("ShoppingCart");
		frmShoppingcart.setBounds(100, 100, 863, 393);
		frmShoppingcart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmShoppingcart.getContentPane().setLayout(springLayout);
		
		lblShopItem = new JLabel("Shop Item");
		springLayout.putConstraint(SpringLayout.NORTH, lblShopItem, 26, SpringLayout.NORTH, frmShoppingcart.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblShopItem, 36, SpringLayout.WEST, frmShoppingcart.getContentPane());
		frmShoppingcart.getContentPane().add(lblShopItem);
		
		
		itemModel = new DefaultListModel<>();
		itemList = new JList<String>(itemModel);
		itemList.setFont(new Font("Consolas", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.NORTH, itemList, 6, SpringLayout.SOUTH, lblShopItem);
		springLayout.putConstraint(SpringLayout.WEST, itemList, 0, SpringLayout.WEST, lblShopItem);
		springLayout.putConstraint(SpringLayout.SOUTH, itemList, 230, SpringLayout.SOUTH, lblShopItem);
		springLayout.putConstraint(SpringLayout.EAST, itemList, 219, SpringLayout.WEST, frmShoppingcart.getContentPane());
		frmShoppingcart.getContentPane().add(itemList);
		
		lblPrice = new JLabel("Price : ");
		springLayout.putConstraint(SpringLayout.NORTH, lblPrice, 6, SpringLayout.SOUTH, itemList);
		springLayout.putConstraint(SpringLayout.WEST, lblPrice, 121, SpringLayout.WEST, frmShoppingcart.getContentPane());
		frmShoppingcart.getContentPane().add(lblPrice);

		
		button_buy = new JButton(">");
		springLayout.putConstraint(SpringLayout.NORTH, button_buy, 117, SpringLayout.NORTH, frmShoppingcart.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, button_buy, 6, SpringLayout.EAST, itemList);
		frmShoppingcart.getContentPane().add(button_buy);
		
		button_del = new JButton("<");
		springLayout.putConstraint(SpringLayout.NORTH, button_del, 28, SpringLayout.SOUTH, button_buy);
		springLayout.putConstraint(SpringLayout.WEST, button_del, 6, SpringLayout.EAST, itemList);
		frmShoppingcart.getContentPane().add(button_del);
		
		lblShoppingcart = new JLabel("ShoppingCart");
		springLayout.putConstraint(SpringLayout.NORTH, lblShoppingcart, 0, SpringLayout.NORTH, lblShopItem);
		springLayout.putConstraint(SpringLayout.WEST, lblShoppingcart, 186, SpringLayout.EAST, lblShopItem);
		frmShoppingcart.getContentPane().add(lblShoppingcart);
		
		cartModel = new DefaultListModel<>();
		cartList = new JList<String>(cartModel);
		springLayout.putConstraint(SpringLayout.NORTH, cartList, 6, SpringLayout.SOUTH, lblShoppingcart);
		springLayout.putConstraint(SpringLayout.SOUTH, cartList, 0, SpringLayout.SOUTH, itemList);
		cartList.setFont(new Font("Consolas", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.WEST, cartList, 6, SpringLayout.EAST, button_buy);
		springLayout.putConstraint(SpringLayout.EAST, cartList, -10, SpringLayout.EAST, frmShoppingcart.getContentPane());
		frmShoppingcart.getContentPane().add(cartList);
		
		comboBox = new JComboBox<Integer>();
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 146, SpringLayout.NORTH, frmShoppingcart.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 6, SpringLayout.EAST, itemList);
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, -1, SpringLayout.NORTH, button_del);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 0, SpringLayout.EAST, button_buy);
		frmShoppingcart.getContentPane().add(comboBox);

		btnRemoveallitem = new JButton("Remove All Item");
		frmShoppingcart.getContentPane().add(btnRemoveallitem);
		
		btnCheckOut = new JButton("Check Out");
		springLayout.putConstraint(SpringLayout.NORTH, btnRemoveallitem, 0, SpringLayout.NORTH, btnCheckOut);
		springLayout.putConstraint(SpringLayout.EAST, btnRemoveallitem, -67, SpringLayout.WEST, btnCheckOut);
		springLayout.putConstraint(SpringLayout.EAST, btnCheckOut, -10, SpringLayout.EAST, frmShoppingcart.getContentPane());
		frmShoppingcart.getContentPane().add(btnCheckOut);
		
		btnBill = new JButton("Bill");
		springLayout.putConstraint(SpringLayout.SOUTH, btnCheckOut, -6, SpringLayout.NORTH, btnBill);
		springLayout.putConstraint(SpringLayout.SOUTH, btnBill, -10, SpringLayout.SOUTH, frmShoppingcart.getContentPane());
		frmShoppingcart.getContentPane().add(btnBill);
		
		btnExit = new JButton("Exit");
		springLayout.putConstraint(SpringLayout.EAST, btnBill, -6, SpringLayout.WEST, btnExit);
		springLayout.putConstraint(SpringLayout.NORTH, btnExit, 0, SpringLayout.NORTH, btnBill);
		springLayout.putConstraint(SpringLayout.EAST, btnExit, 0, SpringLayout.EAST, cartList);
		frmShoppingcart.getContentPane().add(btnExit);
		
		rbBymail = new JRadioButton("By mail");
		springLayout.putConstraint(SpringLayout.EAST, rbBymail, -515, SpringLayout.EAST, frmShoppingcart.getContentPane());
		frmShoppingcart.getContentPane().add(rbBymail);
		
		rbCashondelivery = new JRadioButton("Cash on delivery");
		springLayout.putConstraint(SpringLayout.WEST, rbCashondelivery, 7, SpringLayout.EAST, rbBymail);
		frmShoppingcart.getContentPane().add(rbCashondelivery);
		
		lbTransportmethod = new JLabel("Transport Method\uFF1A");
		springLayout.putConstraint(SpringLayout.SOUTH, rbCashondelivery, -3, SpringLayout.NORTH, lbTransportmethod);
		springLayout.putConstraint(SpringLayout.SOUTH, rbBymail, -3, SpringLayout.NORTH, lbTransportmethod);
		springLayout.putConstraint(SpringLayout.EAST, lbTransportmethod, -47, SpringLayout.WEST, btnRemoveallitem);
		springLayout.putConstraint(SpringLayout.NORTH, lbTransportmethod, 30, SpringLayout.SOUTH, cartList);
		springLayout.putConstraint(SpringLayout.WEST, lbTransportmethod, 1, SpringLayout.WEST, lblShoppingcart);
		springLayout.putConstraint(SpringLayout.SOUTH, lbTransportmethod, 47, SpringLayout.NORTH, lblPrice);
		frmShoppingcart.getContentPane().add(lbTransportmethod);
		
		/*textTransportmethod = new TextField();
		springLayout.putConstraint(SpringLayout.WEST, textTransportmethod, 6, SpringLayout.EAST, lbTransportmethod);
		springLayout.putConstraint(SpringLayout.SOUTH, textTransportmethod, 0, SpringLayout.SOUTH, lbTransportmethod);
		springLayout.putConstraint(SpringLayout.EAST, textTransportmethod, 111, SpringLayout.EAST, lbTransportmethod);
		frmShoppingcart.getContentPane().add(textTransportmethod);*/
		
		//批次數量1~5
		comboBox.addItem(1);
		comboBox.addItem(2);
		comboBox.addItem(3);
		comboBox.addItem(4);
		comboBox.addItem(5);
		//按紐分組
		ButtonGroup bg = new ButtonGroup();  
		bg.add(rbBymail);
		bg.add(rbCashondelivery);
		
	}
	//設計商品列表的商品資料
	public void addShopItem(String item) {
		itemModel.addElement(item);
	}
	//設定商品列表的監聽器
	public void addShopListSelectionListener(ListSelectionListener listSelectionListener) {
		itemList.addListSelectionListener(listSelectionListener);
	}
	//取得目前選擇的商品清單index
	public int  getSelectShopListIndex() {
		return itemList.getSelectedIndex();
	}
	//設定price資訊
	public void setShopItemPrice(Double price) {
		lblPrice.setText("Price : " + price + " NTD");
	}
	
	//設定按下>的監聽器
	public void addButtonBuyActionListener(ActionListener actionListener) {
		button_buy.addActionListener(actionListener);
	}
	
	//取得批次數量
	public int getSelectNum() {
		return comboBox.getItemAt(comboBox.getSelectedIndex()).intValue();
	}
	
	//清空購物車
	public void carListClear() {
		cartModel.clear();
	}
	
	//填入購物車
	public void addCarItem(String item) {
		cartModel.addElement(item);
	}
	
	//設定按下<的監聽器
	public void addButtonDelActionListener(ActionListener actionListener) {
		button_del.addActionListener(actionListener);
	}
	
	//取得選擇的購物車清單index
	public int getSelectCarListIndex() {
		return cartList.getSelectedIndex();
	}
	
	//設定按下RemoveAllItem的監聽器
	public void addRemoveAllItemActionListener(ActionListener actionListener) {
		btnRemoveallitem.addActionListener(actionListener);
	}
	//設定>可否使用
	public void setButtonBuyEnabled(boolean b) {
		button_buy.setEnabled(b);
	}
	//設定<可否使用
	public void setButtonDelEnabled(boolean b) {
		button_del.setEnabled(b);
	}
	//設定RemoveAllItem可否使用
	public void setRemoveAllItemEnabled(boolean b) {
		btnRemoveallitem.setEnabled(b);
	}
	//設定CheckOut可否使用
	public void setCheckOutEnabled(boolean b) {
		btnCheckOut.setEnabled(b);
	}
	//設定Bill可否使用
	public void setBillEnabled(boolean b) {
		btnBill.setEnabled(b);
	}
	
	//設定CheckOut按鈕監聽器
	public void addCheckOutActionListener(ActionListener actionListener) {
		btnCheckOut.addActionListener(actionListener);
	}
	
	//顯示結帳訊息框
	public void showCheckOutMessage(String msg) {
		JOptionPane.showMessageDialog(frmShoppingcart, msg, "Check Out!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//Bill按鈕監聽器
	public void addBillActionListener(ActionListener actionListener) {
		btnBill.addActionListener(actionListener);
	}
	
	//設定Exit按鈕監聽器
	public void addExitActionListener(ActionListener actionListener) {
		btnExit.addActionListener(actionListener);
	}
	
	//rbBymail的按鈕監聽器
	public void RbBymailActionListener(ActionListener actionListener) {
		rbBymail.addActionListener(actionListener);
	}
	
	//rbCashondelivery的按鈕監聽器
	public void RbCashondeliveryActionListener(ActionListener actionListener){
		rbCashondelivery.addActionListener(actionListener);
	}
	
	//Get運送方式的按鈕方法
	public void rbBｙmailActionPerformed(boolean b){
		rbBymail.setEnabled(b);
		lbTransportmethod.setText("Transport Method：郵寄");
	}
	public void rbCashondeliveryActionPerformed(boolean b){
		rbCashondelivery.setEnabled(b);
		lbTransportmethod.setText("Transport Method：貨到付款");
	}
	
}
