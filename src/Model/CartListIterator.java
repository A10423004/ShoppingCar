package Model;

public class CartListIterator implements Iterator {
	CarList carlist;
	int index;
	public CartListIterator(CarList carlist) {
		this.carlist = carlist;
		index = 0;
	}

	@Override
	public boolean hasNext() {
		if(index >= carlist.getLength() || carlist == null){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public CartItem Next() {
		CartItem item = carlist.getItemAt(index);
		index++;
		return item;
	}

}
