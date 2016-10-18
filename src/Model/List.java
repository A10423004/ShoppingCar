package Model;

public interface List {
	public abstract void add(Item item);
	public abstract Item remove(int index);
	public abstract int getLength();
	public abstract Item getItemAt(int index);
	public abstract Iterator iterator();
}
