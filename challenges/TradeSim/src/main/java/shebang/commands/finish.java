package shebang.commands;

import org.quteshell.Command;
import org.quteshell.Elevation;
import org.quteshell.Shell;
import shebang.*;

import java.util.ArrayList;

@Elevation(Elevation.DEFAULT)
public class finish extends Command {

    public finish(Shell shell) {
        super(shell);
    }

    @Override
    public void execute(String arguments) {
        ArrayList<Trader> traders = Market.getMarket(shell.getIdentifier());
        boolean hadAll = true;
        for (Trader trader : traders)
            for (Tuple<Integer, Item> entry : trader.getInventory())
                if (entry.getRight().equals(Item.ITEMS[Item.ITEMS.length - 1])) hadAll = false;
        if (hadAll) {
            shell.writeln("Finished!");
            shell.writeln("H{th3_l0gic_i5_pr3tty_s1mpl3}");
        } else {
            shell.writeln("You don't have all of the cats!");
            shell.execute("exit");
        }
    }
}
