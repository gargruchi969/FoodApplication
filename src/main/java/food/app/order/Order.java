package food.app.order;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import food.app.entity.Category;
import food.app.entity.Item;
import food.app.exception.InvalidOrderException;
import food.app.exception.ItemNotFoundException;
import food.app.factory.MealFactory;

import java.util.Scanner;

import food.app.meal.Meal;
import food.app.menu.Menu;
import food.app.printer.OrderPrinter;
import food.app.printer.PrintingByCategory;

public class Order {
	Meal meal;
	
	public Order(Meal meal) {
		this.meal = meal;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		String r = sc.nextLine();
		String[] splitA = r.split(" ", 2);
		String[] splitItems;
		if(splitA.length > 1)
			splitItems = splitA[1].split(",");
		else
			splitItems = new String[0];
		
		Map<Integer, Item> bMenu = getBreakfastMenu();
		Map<Integer, Item> lMenu = getLunchMenu();
		Map<Integer, Item> dMenu = getDinnerMenu();
		Menu menu;
		if(splitA[0].equalsIgnoreCase("breakfast")) menu = new Menu(bMenu);
		else if(splitA[0].equalsIgnoreCase("lunch")) menu = new Menu(lMenu);
		else if(splitA[0].equalsIgnoreCase("dinner")) menu = new Menu(dMenu);
		else {
			System.out.println("Restaurant closed");
			throw new Exception("Restaurant closed");
		}
		//Menu BreakfastMenu = new Menu(bMenu);
		List<Category> categories = Arrays.asList(Category.MAIN, Category.SIDE, Category.DRINK);
		if(splitA[0].equalsIgnoreCase("dinner")) categories.add(Category.DESSERT);
		OrderPrinter op = new PrintingByCategory(categories);
		MealFactory mF = new MealFactory(op, menu);
		Meal meal = mF.getMealObject(splitA[0]);
		for(int i = 0; i < splitItems.length; i++) {
			try {
				meal.addItem(Integer.parseInt(splitItems[i]));
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch(ItemNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		try {
			meal.validateAndBuildOrder();
			op.print(meal.getMeal());
		} catch (InvalidOrderException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static Map<Integer, Item> getBreakfastMenu(){
		Map<Integer, Item> bMenu = new HashMap<Integer, Item>();
		bMenu.put(1, new Item(1, "Eggs", Category.MAIN));
		bMenu.put(2, new Item(2, "Toast", Category.SIDE));
		bMenu.put(3, new Item(3, "Coffee", Category.DRINK));
		return bMenu;
	}
	
	static Map<Integer, Item> getLunchMenu(){
		Map<Integer, Item> bMenu = new HashMap<Integer, Item>();
		bMenu.put(1, new Item(1, "Sandwich", Category.MAIN));
		bMenu.put(2, new Item(2, "Chips", Category.SIDE));
		bMenu.put(3, new Item(3, "Soda", Category.DRINK));
		return bMenu;
	}
	
	static Map<Integer, Item> getDinnerMenu(){
		Map<Integer, Item> bMenu = new HashMap<Integer, Item>();
		bMenu.put(1, new Item(1, "Steak", Category.MAIN));
		bMenu.put(2, new Item(2, "Potatoes", Category.SIDE));
		bMenu.put(3, new Item(3, "Wine", Category.DRINK));
		bMenu.put(4, new Item(4, "Cake", Category.DESSERT));
		return bMenu;
	}
	
}
