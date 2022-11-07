package food.app.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import food.app.entity.Category;
import food.app.entity.Item;
import food.app.exception.InvalidOrderException;
import food.app.exception.ItemNotFoundException;
import food.app.factory.MealFactory;
import food.app.meal.Meal;
import food.app.menu.Menu;
import food.app.printer.OrderPrinter;
import food.app.printer.PrintingByCategory;


public class FoodAppTest {
	
	Map<Integer, Item> bMenu;
	Map<Integer, Item> lMenu;
	Map<Integer, Item> dMenu;
	List<Category> categories;
	String exception;
	
	Map<Integer, Item> getBreakfastMenu(){
		Map<Integer, Item> bMenu = new HashMap<Integer, Item>();
		bMenu.put(1, new Item(1, "Eggs", Category.MAIN));
		bMenu.put(2, new Item(2, "Toast", Category.SIDE));
		bMenu.put(3, new Item(3, "Coffee", Category.DRINK));
		return bMenu;
	}
	
	Map<Integer, Item> getLunchMenu(){
		Map<Integer, Item> bMenu = new HashMap<Integer, Item>();
		bMenu.put(1, new Item(1, "Sandwich", Category.MAIN));
		bMenu.put(2, new Item(2, "Chips", Category.SIDE));
		bMenu.put(3, new Item(3, "Soda", Category.DRINK));
		return bMenu;
	}
	
	Map<Integer, Item> getDinnerMenu(){
		Map<Integer, Item> bMenu = new HashMap<Integer, Item>();
		bMenu.put(1, new Item(1, "Steak", Category.MAIN));
		bMenu.put(2, new Item(2, "Potatoes", Category.SIDE));
		bMenu.put(3, new Item(3, "Wine", Category.DRINK));
		bMenu.put(4, new Item(4, "Cake", Category.DESSERT));
		return bMenu;
	}
	
	@Before
	public void setUp() {
		bMenu = getBreakfastMenu();
		lMenu = getLunchMenu();
		dMenu = getDinnerMenu();
		categories = Arrays.asList(Category.MAIN, Category.SIDE, Category.DRINK);
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void test_breakfast1() {
		String in = "Breakfast 1,2,3";
		System.out.println("in: " + in);
		String[] splitA = in.split(" ", 2);
		String[] splitItems = splitA[1].split(",");
		Menu menu = new Menu(bMenu);
		Meal meal = commonSetup(splitA, splitItems, menu);
		Assert.assertEquals(meal.getMeal().size(), 3);
	}
	
	@Test
	public void test_breakfast2() {
		String in = "Breakfast 2,3,1";
		System.out.println("in: " + in);
		String[] splitA = in.split(" ", 2);
		String[] splitItems = splitA[1].split(",");
		Menu menu = new Menu(bMenu);
		Meal meal = commonSetup(splitA, splitItems, menu);
		Assert.assertEquals(meal.getMeal().size(), 3);
	}
	
	@Test
	public void test_breakfast3() {
		String in = "Breakfast 1,2,3,3";
		System.out.println("in: " + in);
		String[] splitA = in.split(" ", 2);
		String[] splitItems = splitA[1].split(",");
		Menu menu = new Menu(bMenu);
		Meal meal = commonSetup(splitA, splitItems, menu);
		Assert.assertEquals(meal.getMeal().size(), 3);
	}
	
	@Test
	public void test_breakfast4() {
		String in = "Breakfast 1";
		System.out.println("in: " + in);
		String[] splitA = in.split(" ", 2);
		String[] splitItems = splitA[1].split(",");
		Menu menu = new Menu(bMenu);
		Meal meal = commonSetup(splitA, splitItems, menu);
		Assert.assertEquals(exception, "Unable to Process : Side is missing");
	}
	
	@Test
	public void test_lunch1() {
		String in = "Lunch 1,2,3";
		System.out.println("in: " + in);
		String[] splitA = in.split(" ", 2);
		String[] splitItems = splitA[1].split(",");
		Menu menu = new Menu(lMenu);
		Meal meal = commonSetup(splitA, splitItems, menu);
		Assert.assertEquals(meal.getMeal().size(), 3);
	}
	
	
	@Test
	public void test_lunch2() {
		String in = "Lunch 1,2";
		System.out.println("in: " + in);
		String[] splitA = in.split(" ", 2);
		String[] splitItems = splitA[1].split(",");
		Menu menu = new Menu(lMenu);
		Meal meal = commonSetup(splitA, splitItems, menu);
		Assert.assertEquals(meal.getMeal().size(), 3);
	}
	
	@Test
	public void test_lunch3() {
		String in = "Lunch 1,1,2,3";
		System.out.println("in: " + in);
		String[] splitA = in.split(" ", 2);
		String[] splitItems = splitA[1].split(",");
		Menu menu = new Menu(lMenu);
		Meal meal = commonSetup(splitA, splitItems, menu);
		Assert.assertEquals(exception, "Unable to Process : Sandwich cannot be ordered more than once");
	}
	
	@Test
	public void test_lunch4() {
		String in = "Lunch 1,2,2";
		System.out.println("in: " + in);
		String[] splitA = in.split(" ", 2);
		String[] splitItems = splitA[1].split(",");
		Menu menu = new Menu(lMenu);
		Meal meal = commonSetup(splitA, splitItems, menu);
		Assert.assertEquals(meal.getMeal().size(), 3);
	}
	
	@Test
	public void test_lunch5() {
		String in = "Lunch";
		System.out.println("in: " + in);
		String[] splitA = in.split(" ", 2);
		String[] splitItems;
		if(splitA.length > 1)
			splitItems = splitA[1].split(",");
		else
			splitItems = new String[0];
		Menu menu = new Menu(lMenu);
		Meal meal = commonSetup(splitA, splitItems, menu);
		Assert.assertEquals(exception, "Unable to Process : Main is missing, Side is missing");
	}
	
	@Test
	public void test_dinner1() {
		String in = "Dinner 1,2,3,4";
		System.out.println("in: " + in);
		String[] splitA = in.split(" ", 2);
		String[] splitItems = splitA[1].split(",");
		Menu menu = new Menu(dMenu);
		Meal meal = commonSetup(splitA, splitItems, menu);
		Assert.assertEquals(meal.getMeal().size(), 4);
	}
	
	@Test
	public void test_dinner2() {
		String in = "Dinner 1,2,3";
		System.out.println("in: " + in);
		String[] splitA = in.split(" ", 2);
		String[] splitItems = splitA[1].split(",");
		Menu menu = new Menu(dMenu);
		Meal meal = commonSetup(splitA, splitItems, menu);
		Assert.assertEquals(exception, "Unable to Process : Dessert is Missing");
	}
	
	private Meal commonSetup(String[] splitA, String[] splitItems, Menu menu) {
		OrderPrinter op = new PrintingByCategory(categories);
		MealFactory mF = new MealFactory(op, menu);
		Meal meal = mF.getMealObject(splitA[0]);
		order(splitItems, op, meal);
		return meal;
	}

	private void order(String[] splitItems, OrderPrinter op, Meal meal) {
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
			exception = e.getMessage();
			System.out.println(e.getMessage());
		}
	}

}
