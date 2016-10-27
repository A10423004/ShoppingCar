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

public class MenuView {
	private JFrame frmShoppingcart;
	/**
	 * Create the application.
	 */
	public MenuView() {
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
	private JButton btnBill;
	private JButton btnNext;
	private JLabel lblShoppingcart;
	private DefaultListModel<String> cartModel;
	private JList<String> cartList;
	private JComboBox<Integer> comboBox;
	private JButton btnRemoveallitem;
	private JButton btnCheckOut;
	private JButton btnExit;
	private JRadioButton rbBymail;//mail button
	private JRadioButton rbCashondelivery;//Cashondelivery button
	private JLabel lbTransportmethod;
	private JLabel lbTransportmethodText;//transportmethod label
	
	private void initialize() {
		frmShoppingcart = new JFrame();
		frmShoppingcart.setTitle("Book Orders System");
		frmShoppingcart.setBounds(100, 100, 804, 412);
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
		springLayout.putConstraint(SpringLayout.WEST, lblPrice, 75, SpringLayout.WEST, frmShoppingcart.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblPrice, 180, SpringLayout.WEST, frmShoppingcart.getContentPane());
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
		springLayout.putConstraint(SpringLayout.NORTH, btnRemoveallitem, 6, SpringLayout.SOUTH, cartList);
		springLayout.putConstraint(SpringLayout.EAST, btnRemoveallitem, -251, SpringLayout.EAST, frmShoppingcart.getContentPane());
		frmShoppingcart.getContentPane().add(btnRemoveallitem);
		
		btnCheckOut = new JButton("Check Out");
		springLayout.putConstraint(SpringLayout.NORTH, btnCheckOut, 6, SpringLayout.SOUTH, cartList);
		springLayout.putConstraint(SpringLayout.EAST, btnCheckOut, 0, SpringLayout.EAST, cartList);
		frmShoppingcart.getContentPane().add(btnCheckOut);
		
		btnBill = new JButton("Bill");
		springLayout.putConstraint(SpringLayout.NORTH, btnBill, 6, SpringLayout.SOUTH, btnCheckOut);
		frmShoppingcart.getContentPane().add(btnBill);
		
		btnExit = new JButton("Exit");
		springLayout.putConstraint(SpringLayout.EAST, btnBill, -6, SpringLayout.WEST, btnExit);
		springLayout.putConstraint(SpringLayout.NORTH, btnExit, 6, SpringLayout.SOUTH, btnCheckOut);
		springLayout.putConstraint(SpringLayout.EAST, btnExit, -10, SpringLayout.EAST, frmShoppingcart.getContentPane());
		frmShoppingcart.getContentPane().add(btnExit);
		
		rbBymail = new JRadioButton("By mail");
		rbBymail.setSelected(true);
		springLayout.putConstraint(SpringLayout.EAST, rbBymail, -515, SpringLayout.EAST, frmShoppingcart.getContentPane());
		frmShoppingcart.getContentPane().add(rbBymail);
		
		rbCashondelivery = new JRadioButton("Cash on delivery");
		springLayout.putConstraint(SpringLayout.WEST, rbCashondelivery, 7, SpringLayout.EAST, rbBymail);
		frmShoppingcart.getContentPane().add(rbCashondelivery);
		
		lbTransportmethod = new JLabel("Transport Method\uFF1A");
		springLayout.putConstraint(SpringLayout.SOUTH, rbCashondelivery, -3, SpringLayout.NORTH, lbTransportmethod);
		springLayout.putConstraint(SpringLayout.SOUTH, rbBymail, -3, SpringLayout.NORTH, lbTransportmethod);
		springLayout.putConstraint(SpringLayout.WEST, lbTransportmethod, 10, SpringLayout.WEST, rbBymail);
		springLayout.putConstraint(SpringLayout.SOUTH, lbTransportmethod, -31, SpringLayout.SOUTH, frmShoppingcart.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lbTransportmethod, 0, SpringLayout.EAST, rbCashondelivery);
		springLayout.putConstraint(SpringLayout.NORTH, lbTransportmethod, 30, SpringLayout.SOUTH, cartList);
		frmShoppingcart.getContentPane().add(lbTransportmethod);
		
		lbTransportmethodText = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, lbTransportmethodText, 3, SpringLayout.SOUTH, rbCashondelivery);
		springLayout.putConstraint(SpringLayout.WEST, lbTransportmethodText, 331, SpringLayout.WEST, frmShoppingcart.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lbTransportmethodText, -31, SpringLayout.SOUTH, frmShoppingcart.getContentPane());
		frmShoppingcart.getContentPane().add(lbTransportmethodText);
		
		btnNext = new JButton("Next Order");
		springLayout.putConstraint(SpringLayout.SOUTH, btnNext, 0, SpringLayout.SOUTH, frmShoppingcart.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNext, 0, SpringLayout.EAST, cartList);
		frmShoppingcart.getContentPane().add(btnNext);
		
		//add item one to five
		comboBox.addItem(1);
		comboBox.addItem(2);
		comboBox.addItem(3);
		comboBox.addItem(4);
		comboBox.addItem(5);
		
		//radiobutton group
		ButtonGroup bg = new ButtonGroup();  
		bg.add(rbBymail);
		bg.add(rbCashondelivery);
		
	}
	//add ShopItem content
	public void addShopItem(String item) {
		itemModel.addElement(item);
	}
	//itemList actionlistener
	public void addShopListSelectionListener(ListSelectionListener listSelectionListener) {
		itemList.addListSelectionListener(listSelectionListener);
	}
	//get the selected item index
	public int  getSelectShopListIndex() {
		return itemList.getSelectedIndex();
	}
	//set the price label
	public void setShopItemPrice(Double price) {
		lblPrice.setText("Price : " + price + " NTD");
	}
	
	//set the button ">" acctionlistener
	public void addButtonBuyActionListener(ActionListener actionListener) {
		button_buy.addActionListener(actionListener);
	}
	
	//get the combobox selected index
	public int getSelectNum() {
		return comboBox.getItemAt(comboBox.getSelectedIndex()).intValue();
	}
	
	//clear CarList
	public void carListClear() {
		cartModel.clear();
	}
	
	//load carlist item
	public void addCarItem(String item) {
		cartModel.addElement(item);
	}
	
	//set button "<" actionlistener
	public void addButtonDelActionListener(ActionListener actionListener) {
		button_del.addActionListener(actionListener);
	}
	
	//get carList selected index
	public int getSelectCarListIndex() {
		return cartList.getSelectedIndex();
	}
	
	//set button "RemoveAllItem" actionlistener
	public void addRemoveAllItemActionListener(ActionListener actionListener) {
		btnRemoveallitem.addActionListener(actionListener);
	}
	//set button ">" visibility
	public void setButtonBuyEnabled(boolean b) {
		button_buy.setEnabled(b);
	}
	//set button "<" visibility
	public void setButtonDelEnabled(boolean b) {
		button_del.setEnabled(b);
	}
	//set button "RemoveAllItem" visibility
	public void setRemoveAllItemEnabled(boolean b) {
		btnRemoveallitem.setEnabled(b);
	}
	//set button "CheckOut" visibility
	public void setCheckOutEnabled(boolean b) {
		btnCheckOut.setEnabled(b);
	}
	
	//set button "Bill" visibility
	public void setBillEnabled(boolean b) {
		btnBill.setEnabled(b);
	}
	
	//set button "Next Order" visibility
	public void setNextEnabled(boolean b){
		btnNext.setEnabled(b);
	}
		
	//set button "Bill" actionlistener
	public void addBillActionListener(ActionListener actionListener) {
		btnBill.addActionListener(actionListener);
	}
	
	//set button "Next Order" actionlistener
	public void addNextActionListener(ActionListener actionListener){
		btnNext.addActionListener(actionListener);
	}
	
	//set button "CheckOut" actionlistener
	public void addCheckOutActionListener(ActionListener actionListener) {
		btnCheckOut.addActionListener(actionListener);
	}
	
	//Show checkout message
	public void showCheckOutMessage(String msg) {
		JOptionPane.showMessageDialog(frmShoppingcart, msg, "Check Out!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//set button "Exit" actionlistener
	public void addExitActionListener(ActionListener actionListener) {
		btnExit.addActionListener(actionListener);
	}
	
	//set button "By mail" actionlistener
	public void addRbBymailActionListener(ActionListener actionListener) {
		rbBymail.addActionListener(actionListener);
	}
	
	//set button "Cash on delivery" actionlistener
	public void addRbCashondeliveryActionListener(ActionListener actionListener){
		rbCashondelivery.addActionListener(actionListener);
	}
	
	//set label "TransportmethodText" text
	public void setTransportmethodText(String text) {
		lbTransportmethodText.setText(text);
	}
	
	//set button "By mail" visibility
	public void setRbBymailEnabled(boolean b) {
		rbBymail.setEnabled(b);
	}
	
	//set button "Cash on delivery" visibility
	public void setRbCashondeliveryEnabled(boolean b) {
		rbCashondelivery.setEnabled(b);
	}
	
	//set radiobutton "By mail" isselected
	public void setRbBymailSelected(boolean b) {
		rbBymail.setSelected(b);
	}
	
	//set radiobutton "Cash on delivery" isselected
	public void setRbCashondeliverySelected(boolean b) {
		rbCashondelivery.setSelected(b);
	}
	
	//Check button "By mail" isselected
	public boolean rbRbBymail_isSelected() {
		return rbBymail.isSelected();
	}
	
	//Check button "Cash on delivery" isselected
	public boolean rbCashondelivery_isSelected() {
		return rbCashondelivery.isSelected();
	}
}