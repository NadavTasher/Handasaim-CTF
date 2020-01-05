package shebang.commands;

import org.quteshell.Command;
import org.quteshell.Elevation;
import org.quteshell.Shell;
import org.quteshell.commands.Help;
import shebang.Item;

@Elevation(Elevation.DEFAULT)
@Help.Description("valueof tells you the value of a specific item.\ne.g. 'valueof phone'")
public class valueof extends Command {

    public valueof(Shell shell) {
        super(shell);
    }

    @Override
    public void execute(String arguments) {
        if (arguments != null) {
            Item item = null;
            for (Item i : Item.ITEMS) {
                if (i.getName().toLowerCase().equals(arguments.toLowerCase()))
                    item = i;
            }
            if (item != null) {
                shell.write("The value of 1 " + item.getName() + " is ");
                shell.write(String.valueOf(item.getValue()));
                shell.writeln("$.");
            } else {
                shell.writeln("No such item");
            }
        } else {
            shell.writeln("Missing arguments");
        }
    }
}
