package food.app.meal;

import java.util.Arrays;
import java.util.Map;

import food.app.entity.Category;
import food.app.entity.Item;
import food.app.exception.InvalidOrderException;
import food.app.menu.Menu;
import food.app.printer.OrderPrinter;

public class Breakfast extends Meal{

	public Breakfast(Menu menu, OrderPrinter orderPrinter) {
		super(menu, orderPrinter);
	}

	@Override
	public void validateOrderForMeal() throws InvalidOrderException {
		for(Category category : Arrays.asList(Category.MAIN, Category.SIDE)) {
			validateItemsAndQuantityPerCategory(category);
		}
		validateMultipleItemsPerCategory(Category.DRINK);
		Map<Item, Integer> drinks = currentItemsInOrder.get(Category.DRINK);
		for(Map.Entry<Item, Integer> entry : drinks.entrySet()) {
			if(!(entry.getKey().getName().equalsIgnoreCase("Coffee") || entry.getKey().getName().equalsIgnoreCase("Water")) && entry.getValue() > 1) {
				throw new InvalidOrderException("Multiple Drinks other than Coffee Ordered");
			}
		}
	}
}
