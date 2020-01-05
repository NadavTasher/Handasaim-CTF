package shebang;

import org.quteshell.Shell;
import shebang.Trader.User;

import java.util.ArrayList;
import java.util.Random;

public class Market {

    private static final ArrayList<User> users = new ArrayList<User>();

    private static final ArrayList<Tuple<String, ArrayList<Trader>>> markets = new ArrayList<>();

    public static void begin(Shell shell) {
        int flags = new Random().nextInt(25) + 25;
        ArrayList<Trader> traders = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            traders.add(new Trader(traders));
        }
        while (flags > 0) {
            Trader trader = traders.get(new Random().nextInt(traders.size()));
            trader.add(Item.ITEMS[Item.ITEMS.length - 1], 1);
            flags--;
        }
        markets.add(new Tuple<>(shell.getIdentifier(), traders));
        new Thread(() -> {
            while (shell.isRunning()) {
                try {
                    Trader buyer = choose(traders);
                    Trader seller = choose(traders);
                    if (buyer != null && seller != null) {
                        Tuple<Integer, Item> item = seller.getInventory().get(new Random().nextInt(seller.getInventory().size()));
                        int amount = 1;
                        if (item.getLeft() > 1)
                            amount += new Random().nextInt((item.getLeft() - 1) / 2);
                        int pricePerPiece = item.getRight().getValue();
                        if (pricePerPiece > 1)
                            pricePerPiece += new Random().nextInt(pricePerPiece / 2) * (new Random().nextInt(2) - 1);
                        buyer.buy(shell, seller, item.getRight(), amount, pricePerPiece, false);
                    } else {
                        shell.execute("exit");
                    }
                } catch (Exception e) {
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
            }
        }).start();
    }

    private static Trader choose(ArrayList<Trader> traders) {
        ArrayList<Trader> notBankrupt = new ArrayList<>();
        int bankrupt = 0;
        for (Trader t : traders)
            if (t.isBankrupt())
                bankrupt++;
            else
                notBankrupt.add(t);
        if (bankrupt != traders.size())
            return notBankrupt.get(new Random().nextInt(notBankrupt.size()));
        return null;
    }

    public static ArrayList<Trader> getMarket(String id) {
        for (Tuple<String, ArrayList<Trader>> m : markets) {
            if (m.getLeft().equals(id))
                return m.getRight();
        }
        return new ArrayList<>();
    }

    public static User getUser(String id) {
        for (User user : users) {
            if (user.getID().equals(id)) {
                return user;
            }
        }
        User user = new User(id);
        users.add(user);
        return user;
    }
}
