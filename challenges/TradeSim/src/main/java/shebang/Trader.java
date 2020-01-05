package shebang;

import org.quteshell.Shell;

import java.util.ArrayList;
import java.util.Random;

public class Trader {

    private static final String[] NICKNAMES = {
            "Charlotte",
            "Emma",
            "Noah",
            "Emily",
            "Jacob",
            "Nir",
            "Michael",
            "Jackson",
            "Nadav",
            "Amit",
            "Jack",
            "Riley",
            "Luke",
            "Stella",
            "Leah",
            "Caleb",
            "Brooklyn",
            "Nathan",
            "Thomas",
            "Claire",
            "Leo",
            "Lucy",
            "Anna",
            "Caroline",
            "Connor",
            "Emilia",
            "Aaron",
            "Samantha",
            "Landon",
            "Maya",
            "Ruby",
            "Eva",
            "Alon",
            "Ian",
            "Quinn",
            "Roman",
            "Ivy",
            "Sadie",
            "Yarin",
            "Jose",
            "Lydia",
            "Alexa",
            "Leonardo",
            "Bryson",
            "Arianna",
            "Parker",
            "Sophie",
            "Jason",
            "Madeline"
    };

    private int balance;
    private String name;
    private boolean bankrupt = false;
    private ArrayList<Tuple<Integer, Item>> inventory = new ArrayList<>();

    public Trader() {
        balance = 200;
        name = null;
        giveItems(4, 5);
    }

    public Trader(ArrayList<Trader> companions) {
        balance = (new Random().nextInt(8) + 2) * 50;
        pickName(companions);
        giveItems(5, 10);
    }

    private void giveItems(int howMany, int maxAmount) {
        for (int a = 0; a < howMany; a++) {
            add(Item.ITEMS[new Random().nextInt(Item.ITEMS.length - 1)], new Random().nextInt(maxAmount - 1) + 1);
        }
    }

    private void pickName(ArrayList<Trader> companions) {
        name = NICKNAMES[new Random().nextInt(NICKNAMES.length)];
        for (Trader companion : companions)
            if (companion.name.equals(name))
                pickName(companions);
    }

    public boolean buy(Shell quteshell, Trader from, Item item, int amount, int price, boolean bankrupcyProtection) {
        int totalPrice = amount * price;
        if (name == null) {
            quteshell.write("You");
            quteshell.write(" buy ");
        } else {
            quteshell.write(name + " buys ");
        }
        quteshell.write(amount + " " + item.getName() + " from ");
        quteshell.write(from.name == null ? "You" : from.name);
        quteshell.write(" at a price of " + totalPrice + "$ - ");
        if (totalPrice > balance) {
            // Can cause bankrupcy
            if (bankrupcyProtection) {
                quteshell.writeln("Not enough money");
            } else {
                quteshell.writeln(name + " went bankrupt");
                bankrupt = true;
            }
            return false;
        } else {
            Tuple<Integer, Item> entry = from.find(item);
            if (entry != null) {
                if (entry.getLeft() >= amount) {
                    this.balance -= totalPrice;
                    from.balance += totalPrice;
                    this.add(item, amount);
                    from.remove(item, amount);
                    quteshell.writeln("OK");
                    return true;
                } else {
                    quteshell.writeln("Not enough quantity");
                    return false;
                }
            } else {
                quteshell.writeln("No such item");
                return false;
            }
        }
    }

    protected Tuple<Integer, Item> find(Item item) {
        for (Tuple<Integer, Item> i : inventory) {
            if (i.getRight().equals(item)) {
                return i;
            }
        }
        return null;
    }

    protected void add(Item item, int amount) {
        Tuple<Integer, Item> entry = find(item);
        if (entry != null) {
            entry.setLeft(entry.getLeft() + amount);
        } else {
            entry = new Tuple<>(amount, item);
            inventory.add(entry);
        }
    }

    protected void remove(Item item, int amount) {
        Tuple<Integer, Item> entry = find(item);
        if (entry != null) {
            entry.setLeft(entry.getLeft() - amount);
            if (entry.getLeft() <= 0)
                inventory.remove(entry);
        }
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public ArrayList<Tuple<Integer, Item>> getInventory() {
        return inventory;
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public static class User extends Trader {

        private String id;

        public User(String id) {
            this.id = id;
        }

        public String getID() {
            return id;
        }
    }
}
