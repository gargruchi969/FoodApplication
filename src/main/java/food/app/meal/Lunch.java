package food.app.meal;

import java.util.Arrays;

import food.app.entity.Category;
import food.app.exception.InvalidOrderException;
import food.app.menu.Menu;
import food.app.printer.OrderPrinter;

public class Lunch extends Meal{

	public Lunch(Menu menu, OrderPrinter orderPrinter) {
		super(menu, orderPrinter);
	}

	@Override
	public void validateOrderForMeal() throws InvalidOrderException {
		for(Category category : Arrays.asList(Category.MAIN, Category.DRINK)) {
			validateItemsAndQuantityPerCategory(category);
		}		
	}

}
