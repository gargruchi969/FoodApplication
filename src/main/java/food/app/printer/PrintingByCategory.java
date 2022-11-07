package food.app.printer;

import java.util.List;
import java.util.Map;

import food.app.entity.Category;
import food.app.entity.Item;

public class PrintingByCategory implements OrderPrinter{

	List<Category> categories;
	
	public PrintingByCategory(List<Category> categories) {
		this.categories = categories;
	}
	
	public void print(Map<Category, Map<Item, Integer>> order) {
		StringBuilder sb = new StringBuilder();
		for(Category category: categories) {
			printItemsInCategory(order, sb, category);
		}
		sb.delete(sb.length() - 2, sb.length());
		System.out.println(sb.toString());
	}

	private void printItemsInCategory(Map<Category, Map<Item, Integer>> order, StringBuilder sb, Category category) {
		if(order.containsKey(category)) {
			Map<Item, Integer> items = order.get(category);
			for(Map.Entry<Item, Integer> entry : items.entrySet()) {
				sb.append(entry.getKey().getName());
				if(entry.getValue() > 1) sb.append("(" + entry.getValue() + ")");
				sb.append(", ");
			}
		}
	}

}
