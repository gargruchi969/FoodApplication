package food.app.entity;

import java.util.Objects;

public class Item {
	
	private int id;
	private String name;
	private Category category;
	private int hashCode;
	
	public Item(int id, String name, Category category){
		this.id = id;
		this.name = name;
		this.category = category;
		hashCode = Objects.hash(this.id);
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Category getCategory() {
		return category;
	}
	
	@Override
	public boolean equals(Object i) {
		Item j = (Item) i;
		if(this.id == j.getId())
			return true;
		return false;
	}
	
	@Override
    public int hashCode() {
        return this.hashCode;
    }
}
