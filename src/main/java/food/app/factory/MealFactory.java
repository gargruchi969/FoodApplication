package food.app.factory;

import food.app.meal.Breakfast;
import food.app.meal.Dinner;
import food.app.meal.Lunch;
import food.app.meal.Meal;
import food.app.menu.Menu;
import food.app.printer.OrderPrinter;

public class MealFactory {
	private OrderPrinter orderPrinter;
	private Menu menu;
	public MealFactory(OrderPrinter orderPrinter, Menu menu) {
		super();
		this.orderPrinter = orderPrinter;
		this.menu = menu;
	}
	public Meal getMealObject(String meal){
		if(meal.equalsIgnoreCase("breakfast")) return new Breakfast(menu, orderPrinter);
		else if(meal.equalsIgnoreCase("lunch")) return new Lunch(menu, orderPrinter);
		else return new Dinner(menu, orderPrinter);
	}
}
