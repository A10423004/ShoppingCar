package Model;

import java.util.ArrayList;

public class CarList implements List{
	ArrayList<CartItem> list;
	public CarList() {
		list = new ArrayList<CartItem>();
	}
	public void add(Item item) {
		list.add((CartItem)item);
	}
	public Item remove(int index) {
		CartItem item = list.get(index);
		list.remove(index);
		return item;
	}
	public int getLength() {
		return list.size();
	}
	public CartItem getItemAt(int index) {
		return list.get(index);
	}
	public Iterator iterator() {
		return new CartListIterator(this);
	}

}
