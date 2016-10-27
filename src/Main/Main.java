package Main;

import Controller.Controller;
import View.MenuView;

public class Main {
	public static void main(String[] args) {
		MenuView mv = new MenuView();
		Controller controller = new Controller(mv);
	}
}
