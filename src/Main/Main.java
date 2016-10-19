package Main;

import Controller.Controller;
import Model.Db2;
import Model.Item;
import Model.Iterator;
import Model.ShopItem;
import Model.ShopList;
import View.MenuView;
import View.MenuView2;

public class Main {
	public static void main(String[] args) {
		MenuView2 mv = new MenuView2();
		Controller controller = new Controller(mv);
	}
}
