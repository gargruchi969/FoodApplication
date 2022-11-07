package food.app.menu;

import java.util.Map;

import food.app.entity.Item;
import food.app.exception.ItemNotFoundException;

public class Menu{
	Map<Integer, Item> bMenu;
	
	public Menu(Map<Integer, Item> bMenu) {
		this.bMenu = bMenu;
	}
	
	public void add(Item item) {
		bMenu.put(item.getId(), item);
	}

	public void remove(Item item) {
		bMenu.remove(item.getId());
	}
	
	public Item getItem(int id) throws ItemNotFoundException {
		if(bMenu.containsKey(id)) {
			return bMenu.get(id);
		}else {
			throw new ItemNotFoundException("Given item is not present in the Menu");
		}
	}

}
