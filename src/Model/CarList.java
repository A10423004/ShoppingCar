package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CarList implements List{
	ArrayList<CartItem> list;
	Strategy strategy;
	public CarList() {
		list = new ArrayList<CartItem>();
	}
	public void add(Item item) {
		for (CartItem tt : list) {
			if(tt.getName().equals(item.getName())){
				tt.setQuantity(tt.getQuantity() + ((CartItem) item).getQuantity());
				return;
			}
		}
		list.add((CartItem)item);
	}
	public Item remove(int index) {
		CartItem item = list.get(index);
		list.remove(index);
		return item;
	}
	public void removeAll() {
		list.clear();
	}
	//多型，指定要刪除幾個Item
	public Item remove(int index, int num) {
		try {
			CartItem item = list.get(index);
			item.setQuantity(item.getQuantity() - num);
			if (item.getQuantity() <= 0) {
				list.remove(index);
				return item;
			}
			return null;
		} catch (Exception ex) {
			return null;
		}
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
	//算總價錢
	public double getTotalCost() {
		double total = 0.0;
		try{
			for(CartItem item : list){
				total += item.getPrice() * item.getQuantity();
			}
			total += getStrategy();
		}
		catch(Exception e){
			System.out.println("請選擇運送方式!!");
		}
		return total;
	}
	//set取得Strategy的方法
	public void setStrategy(Strategy s){
		this.strategy = s;
	}
	//get取得Strategy的getTransportPrice()方法
	public double getStrategy(){
		return strategy.getTransportPrice();
	}
	
}
