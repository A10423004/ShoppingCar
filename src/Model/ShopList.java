package Model;

import java.util.ArrayList;

public class ShopList implements List{
	ArrayList<ShopItem> list;
	public ShopList() {
		list = new ArrayList<ShopItem>();
	}
	public void add(Item item) {
		list.add((ShopItem)item);
	}
	public ShopItem remove(int index) {
		ShopItem item = list.get(index);
		list.remove(index);
		return item;
	}
	public void removeAll() {
		list.clear();
	}
	public int getLength() {
		return list.size();
	}
	public ShopItem getItemAt(int index) {
		return list.get(index);
	}
	public Iterator iterator() {
        return new ShopListIterator(this);
    }
}
