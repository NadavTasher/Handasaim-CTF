package shebang.commands;

import org.quteshell.Command;
import org.quteshell.Elevation;
import org.quteshell.Shell;
import org.quteshell.commands.Help;
import shebang.Trader.User;
import shebang.*;

@Elevation(Elevation.DEFAULT)
@Help.Description("sell - offer a trader an item from your inventory.\ne.g. 'sell 1 potato nadav 12' or 'sell [Amount] [Item] [Trader] [Price per Piece]'")
public class sell extends Command {

    public sell(Shell shell) {
        super(shell);
    }

    @Override
    public void execute(String arguments) {
        if (arguments != null) {
            String[] args = arguments.split(" ");
            if (args.length == 4) {
                String amountWord = args[0];
                String itemName = args[1];
                String traderName = args[2];
                String pppWord = args[3];
                try {
                    int amount = Integer.parseInt(amountWord);
                    int ppp = Integer.parseInt(pppWord);
                    User user = Market.getUser(shell.getIdentifier());
                    Item item = null;
                    for (Tuple<Integer, Item> i : user.getInventory()) {
                        if (i.getRight().getName().toLowerCase().equals(itemName.toLowerCase()))
                            item = i.getRight();
                    }
                    Trader trader = null;
                    for (Trader t : Market.getMarket(shell.getIdentifier())) {
                        if (t.getName().toLowerCase().equals(traderName.toLowerCase()))
                            trader = t;
                    }
                    if (trader != null) {
                        if (!trader.isBankrupt()) {
                            if (item != null) {
                                shell.write("You");
                                shell.write(" offered " + trader.getName() + " to buy " + amount + " " + item.getName() + " at a price of " + ppp * amount + "$ - ");
                                if (ppp <= (1.3 * ((double) item.getValue()))) {
                                    shell.writeln("Approved");
                                    trader.buy(shell, user, item, amount, ppp, true);
                                } else {
                                    shell.writeln("Declined");
                                }
                            } else {
                                shell.writeln("You don't have that item");
                            }
                        } else {
                            shell.writeln(trader.getName() + " is bankrupt");
                        }
                    } else {
                        shell.writeln("No such trader");
                    }
                } catch (Exception e) {
                    shell.writeln("Number conversion error");
                }
            } else {
                shell.writeln("Too many/few arguments");
            }
        } else {
            shell.writeln("Missing arguments");
        }
    }
}
