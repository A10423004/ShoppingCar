package Main;

import Model.Db2;
import Model.Item;
import Model.Iterator;
import Model.ShopItem;
import Model.ShopList;
import View.MenuView;

public class Main {
	public static void main(String[] args) {
		//MenuView mv = new MenuView();
		//mv.show();
		
		ShopList shopList = Db2.readItem();
		Iterator iterator = shopList.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.Next().getName());
		}
	}
}
