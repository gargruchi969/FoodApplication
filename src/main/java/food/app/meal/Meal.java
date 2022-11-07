package food.app.meal;

import java.util.HashMap;
import java.util.Map;

import food.app.constants.Constants;
import food.app.entity.Category;
import food.app.entity.Item;
import food.app.exception.InvalidOrderException;
import food.app.exception.ItemNotFoundException;
import food.app.menu.Menu;
import food.app.printer.OrderPrinter;

public abstract class Meal {
	protected Menu menu;
	protected Map<Category, Map<Item, Integer>> currentItemsInOrder;
	protected OrderPrinter orderPrinter;
	
	public Meal(Menu menu, OrderPrinter orderPrinter) {
		this.menu = menu;
		currentItemsInOrder = new HashMap<Category, Map<Item, Integer>>();
		this.orderPrinter = orderPrinter;
	}
	
	public void addItem(int id) throws ItemNotFoundException {
		Item item = menu.getItem(id);
		Map<Item, Integer> items = currentItemsInOrder.getOrDefault(item.getCategory(), new HashMap<Item, Integer>());
		items.put(item, items.getOrDefault(item, 0) + 1);
		currentItemsInOrder.put(item.getCategory(), items);
	}
	
	private void validateMainSideInOrder() throws InvalidOrderException {
		if(!currentItemsInOrder.containsKey(Category.MAIN) && !currentItemsInOrder.containsKey(Category.SIDE))
			throw new InvalidOrderException(Constants.MISSING_MAIN_SIDE);
		else if(!currentItemsInOrder.containsKey(Category.MAIN))
			throw new InvalidOrderException(Constants.MISSING_MAIN);
		else if(!currentItemsInOrder.containsKey(Category.SIDE))
			throw new InvalidOrderException(Constants.MISSING_SIDE);
	}
	
	private void addWaterIfDrinkMissing() {
		if(!currentItemsInOrder.containsKey(Category.DRINK)) {
			Map<Item, Integer> drinks = currentItemsInOrder.getOrDefault(Category.DRINK, new HashMap<Item, Integer>());
			drinks.put(new Item(-1, "Water", Category.DRINK), 1);
			currentItemsInOrder.put(Category.DRINK, drinks);
		}
	}
	
	public void validateAndBuildOrder() throws InvalidOrderException {
		validateMainSideInOrder();
		addWaterIfDrinkMissing();
		validateOrderForMeal(); 
	}
	
	protected void validateItemsAndQuantityPerCategory(Category category) throws InvalidOrderException{
		validateMultipleItemsPerCategory(category);
		validateQuantityPerItem(category);
	}

	protected void validateQuantityPerItem(Category category) throws InvalidOrderException {
		for(Map.Entry<Item, Integer> entry : currentItemsInOrder.get(category).entrySet()) {
			if(entry.getValue() > 1) {
				throw new InvalidOrderException(entry.getKey().getName() + Constants.ORDERED_MULTIPLE_ERROR);
			}
		}
	}

	protected void validateMultipleItemsPerCategory(Category category) throws InvalidOrderException {
		if(currentItemsInOrder.get(category).size() > 1)
			throw new InvalidOrderException(Constants.MULTIPLE_ITEMS_IN_CATEGORY_ERROR + category);
	}
	
	public Map<Category, Map<Item, Integer>> getMeal(){
		return this.currentItemsInOrder;
	}
	
	public abstract void validateOrderForMeal() throws InvalidOrderException;
	
}
