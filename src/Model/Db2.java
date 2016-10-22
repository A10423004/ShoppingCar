package Model;

import java.io.BufferedReader;
import java.io.FileReader;

public class Db2 {
	private static Db2 db2;
	private Db2(){
	}
	public static Db2 getProduct(){
		if(db2 == null){
			synchronized(Db2.class){
				if(db2 == null){
					db2 = new Db2();
				}
			}
		}
		return db2;
	}
	
	public ShopList readItem() {
		ShopList shopList = new ShopList();
		ShopItem ci;
		FileReader fr;
		try {
			fr = new FileReader("Item.txt");
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				String[] str = br.readLine().split(",");
				ci = new ShopItem(str[0], Double.parseDouble(str[1]));
				shopList.add(ci);
			}
			fr.close();
		} catch (Exception ex) {
			System.out.println("Can't open file Or can't find file.");
		}
		return shopList;
		
	}
}
