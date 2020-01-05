package shebang.commands;

import org.quteshell.Command;
import org.quteshell.Elevation;
import org.quteshell.Shell;
import shebang.*;

import java.io.Console;

@Elevation(Elevation.DEFAULT)
public class inventory extends Command {

    public inventory(Shell shell) {
        super(shell);
    }

    @Override
    public void execute(String arguments) {
        shell.writeln("Your inventory:");
        int summery = 0;
        for (Tuple<Integer, Item> entry : Market.getUser(shell.getIdentifier()).getInventory()) {
            shell.write(entry.getRight().getName());
            shell.write(", " + entry.getLeft() + "pcs, totaling ");
            shell.write(String.valueOf(entry.getLeft() * entry.getRight().getValue()));
            shell.write("$ - ");
            shell.write(String.valueOf(entry.getRight().getValue()));
            shell.writeln("$ each.");
            summery += entry.getLeft() * entry.getRight().getValue();
        }
        shell.write("Your inventory totals to ");
        shell.write(String.valueOf(summery));
        shell.write("$, and you have ");
        shell.write(String.valueOf(Market.getUser(shell.getIdentifier()).getBalance()));
        shell.writeln("$ cash.");
    }

}
