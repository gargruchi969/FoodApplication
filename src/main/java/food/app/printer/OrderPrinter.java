package food.app.printer;

import java.util.Map;

import food.app.entity.Category;
import food.app.entity.Item;

public interface OrderPrinter {
	public void print(Map<Category, Map<Item, Integer>> order);
}
