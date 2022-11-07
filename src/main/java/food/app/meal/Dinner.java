package food.app.meal;

import java.util.Arrays;

import food.app.entity.Category;
import food.app.entity.Item;
import food.app.exception.InvalidOrderException;
import food.app.menu.Menu;
import food.app.printer.OrderPrinter;

public class Dinner extends Meal {

	public Dinner(Menu menu, OrderPrinter orderPrinter) {
		super(menu, orderPrinter);
	}

	@Override
	public void validateOrderForMeal() throws InvalidOrderException {
		if(!currentItemsInOrder.containsKey(Category.DESSERT)) {
			throw new InvalidOrderException("Dessert is Missing");
		}
		for(Category category : Arrays.asList(Category.MAIN, Category.SIDE, Category.DRINK, Category.DESSERT)) {
			validateItemsAndQuantityPerCategory(category);
		}
		currentItemsInOrder.get(Category.DRINK).put(new Item(-1, "Water", Category.DRINK), 1);
	}

}
