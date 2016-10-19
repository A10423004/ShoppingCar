package Main;

import Controller.Controller;
import View.MenuView2;

public class Main {
	public static void main(String[] args) {
		MenuView2 mv = new MenuView2();
		Controller controller = new Controller(mv);
	}
}
