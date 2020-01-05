package shebang;

public class Item {
    public static final Item[] ITEMS = {
            new Item("Shampoo", 6),
            new Item("Deodorant", 8),
            new Item("Lemon", 2),
            new Item("Orange", 3),
            new Item("Apple", 4),
            new Item("Potato", 10),
            new Item("Gummybear", 1),
            new Item("Beef", 14),
            new Item("Burger", 10),
            new Item("Egg", 4),
            new Item("Bread", 6),
            new Item("Sourcandy", 3),
            new Item("Cutlery", 20),
            new Item("Phone", 100),
            new Item("Headphones", 50),
            new Item("Donut", 8),
            new Item("Laptop", 105),
            new Item("Mouse", 50),
            new Item("Paper", 25),
            new Item("Tomato", 6),
            new Item("Dog", 60),
            new Item("Cat", 150)
    };

    private int value;
    private String name;

    public Item(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
