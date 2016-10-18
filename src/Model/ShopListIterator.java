package Model;


public class ShopListIterator implements Iterator {
	ShopList shoplist;
	int index;
	public ShopListIterator(ShopList shoplist) {
		this.shoplist = shoplist;
		index = 0;
	}

	@Override
	public boolean hasNext() {
		if(index >= shoplist.getLength() || shoplist == null){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public ShopItem Next() {
		ShopItem item = shoplist.getItemAt(index);
		index++;
		return item;
	}

}
