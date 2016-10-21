package View;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import Controller.ShoppingCartController;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MenuView {
	private JFrame frmShoppingcart;

	/**
	 * Launch the application.
	 */
	/*in the Main.java
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuView window = new MenuView();
					window.frmShoppingcart.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

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
	private ShoppingCartController scc;
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
	private void initialize() {
		scc = new ShoppingCartController();
		frmShoppingcart = new JFrame();
		frmShoppingcart.setTitle("ShoppingCart");
		frmShoppingcart.setBounds(100, 100, 804, 391);
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
		springLayout.putConstraint(SpringLayout.NORTH, btnRemoveallitem, 0, SpringLayout.NORTH, lblPrice);
		springLayout.putConstraint(SpringLayout.EAST, btnRemoveallitem, -251, SpringLayout.EAST, frmShoppingcart.getContentPane());
		frmShoppingcart.getContentPane().add(btnRemoveallitem);
		
		btnCheckOut = new JButton("Check Out");
		springLayout.putConstraint(SpringLayout.NORTH, btnCheckOut, 0, SpringLayout.NORTH, lblPrice);
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
		
		//以上是版面設計，以下是動作
		button_del.setEnabled(false);//先關起來等購物車有物品再打開
		btnCheckOut.setEnabled(false);//先關起來等購物車有物品再打開
		btnBill.setEnabled(false);//等結帳後才有帳單
		//顯示商品列表
		for(String str:scc.showShopItem()){
			itemModel.addElement(str);
		}
		//當選擇商品時，在Label顯示價格
		itemList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				lblPrice.setText("Price : " + scc.getItemPrice(itemList.getSelectedIndex()) + " NTD");
			}
		});
		itemList.setSelectedIndex(0);//預設選擇第一個
		
		comboBox.addItem(1);//批次數量1~5
		comboBox.addItem(2);
		comboBox.addItem(3);
		comboBox.addItem(4);
		comboBox.addItem(5);
		showCartItem();
		
		//添加物品至購物車
		button_buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectIndex = itemList.getSelectedIndex();
				int num = (int)comboBox.getSelectedItem();
				addToCart(selectIndex, num);
				showCartItem();
			}
		});
		//移除指定購物車商品
		button_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectIndex = cartList.getSelectedIndex();
				int num = (int)comboBox.getSelectedItem();
				delCartItem(selectIndex - 2, num);
				showCartItem();
				cartList.setSelectedIndex(selectIndex);
				if(cartModel.size() <= 2){
					button_del.setEnabled(false);//關起來等購物車有物品再打開
					btnCheckOut.setEnabled(false);//關起來等購物車有物品再打開
				}
			}
		});
		
		
		//移除全部購物車商品
		btnRemoveallitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delCartItemAll();
				showCartItem();
			}
		});
		//結帳
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_buy.setEnabled(false);
				button_del.setEnabled(false);
				btnRemoveallitem.setEnabled(false);
				btnCheckOut.setEnabled(false);
				btnBill.setEnabled(true);
				String msg = "The total price is " + scc.getTotalCost() + "NTD.\nThank you and come again.";
				JOptionPane.showMessageDialog(frmShoppingcart, msg, "Check Out!", JOptionPane.INFORMATION_MESSAGE);
				scc.chockOut_Bill();
				try {
					Runtime.getRuntime().exec("cmd /c start Bill.txt");
				} catch (IOException e1) {
					// TODO 自動產生的 catch 區塊
					e1.printStackTrace();
				}
			}
		});
		//Bill button
		btnBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("cmd /c start Bill.txt");
				} catch (IOException e1) {
					// TODO 自動產生的 catch 區塊
					e1.printStackTrace();
				}
			}
		});
		//exit
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	public void showCartItem(){
		cartModel.clear();
		//顯示購物車列表
		for(String str:scc.showCartItem()){
			cartModel.addElement(str);
		}
	}
	public void addToCart(int itemIndex, int num){
		scc.addToCart(itemIndex, num);
		button_del.setEnabled(true);
		btnCheckOut.setEnabled(true);
	}
	public void delCartItem(int cartItemIndex, int num){
		scc.removeCartItem(cartItemIndex, num);
	}
	public void delCartItemAll(){
		scc.removeCartItemAll();
		button_del.setEnabled(false);//關起來等購物車有物品再打開
		btnCheckOut.setEnabled(false);//關起來等購物車有物品再打開
	}
	
	
	
}
