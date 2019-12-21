package quteshell.xdb.commands;

import quteshell.Quteshell;
import quteshell.command.Command;
import quteshell.command.Elevation;
import quteshell.commands.Help;
import quteshell.xdb.Database;

@Elevation(Elevation.DEFAULT)
@Help.Description("You can search through the phone-book using:\n'search name' or 'search'")
public class Search implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        Database db = Connect.get(shell.getID());
        if (arguments != null) {
            shell.writeln("Results:");
            try {
                for (Database.Table.Entry e : db.dynamic("at phonebook is " + arguments)) {
                    shell.write(e.getLeft());
                    shell.write(" - ");
                    shell.writeln(e.getRight());
                }
            } catch (Exception e) {
                shell.writeln(e.getMessage());
            }
        } else {
            shell.writeln("List of people:");
            try {
                for (Database.Table.Entry e : db.getTable("phonebook").getEntries()) {
                    shell.write(e.getLeft());
                    shell.write(" - ");
                    shell.writeln(e.getRight());
                }
            } catch (Exception e) {
            }
        }
    }
}
