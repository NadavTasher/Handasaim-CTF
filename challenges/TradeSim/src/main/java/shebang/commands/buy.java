package shebang.commands;

import org.quteshell.Command;
import org.quteshell.Elevation;
import org.quteshell.Shell;
import org.quteshell.commands.Help;
import shebang.*;

@Elevation(Elevation.DEFAULT)
@Help.Description("buy - buy from a trader.\ne.g. 'buy 1 mouse maya 36' or 'buy [Amount] [Item] [Trader] [Price per Piece]'")
public class buy extends Command {

    public buy(Shell shell) {
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
                    Trader.User user = Market.getUser(shell.getIdentifier());
                    Trader trader = null;
                    for (Trader t : Market.getMarket(shell.getIdentifier())) {
                        if (t.getName().toLowerCase().equals(traderName.toLowerCase()))
                            trader = t;
                    }
                    if (trader != null) {
                        if (!trader.isBankrupt()) {
                            Item item = null;
                            for (Tuple<Integer, Item> i : trader.getInventory()) {
                                if (i.getRight().getName().toLowerCase().equals(itemName.toLowerCase()))
                                    item = i.getRight();
                            }
                            if (item != null) {
                                shell.write("You");
                                shell.write(" offered to buy " + amount + " " + item.getName() + " from " + trader.getName() + " at a price of " + ppp * amount + "$ - ");
                                if (ppp >= (0.8 * ((double) item.getValue()))) {
                                    shell.writeln("Approved");
                                    user.buy(shell, trader, item, amount, ppp, true);
                                } else {
                                    shell.writeln("Declined");
                                }
                            } else {
                                shell.writeln(trader.getName() + " doesn't have that item");
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
