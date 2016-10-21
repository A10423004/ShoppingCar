package Model;

public class CashondeliveryPrice implements Strategy{
	
	@Override
	public double getTransportPrice() {
		return 60.0;
	}
}
