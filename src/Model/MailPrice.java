package Model;

public class MailPrice implements Strategy{
	
	@Override
	public double getTransportPrice() {
		return 30.0;
	}
}
