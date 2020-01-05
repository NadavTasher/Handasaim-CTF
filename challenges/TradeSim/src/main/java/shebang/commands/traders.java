package shebang.commands;

import org.quteshell.Command;
import org.quteshell.Elevation;
import org.quteshell.Shell;
import shebang.Market;
import shebang.Trader;

import java.util.ArrayList;

@Elevation(Elevation.DEFAULT)
public class traders extends Command {

    public traders(Shell shell) {
        super(shell);
    }

    @Override
    public void execute(String arguments) {
        ArrayList<Trader> traders = Market.getMarket(shell.getIdentifier());
        for (Trader trader : traders) {
            shell.write(trader.getName() + " - ");
            shell.writeln((trader.isBankrupt() ? "Bankrupt" : "OK"));
        }
    }

}
