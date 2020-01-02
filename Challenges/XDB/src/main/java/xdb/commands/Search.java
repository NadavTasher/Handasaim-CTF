package xdb.commands;

import org.quteshell.Command;
import org.quteshell.Elevation;
import org.quteshell.Shell;
import org.quteshell.commands.Help;
import xdb.Database;

import java.util.ArrayList;

@Elevation(Elevation.DEFAULT)
@Help.Description("You can search through the phone-book using:\n'search name' or 'search'")
public class Search extends Command {

    public static final ArrayList<Database> dbs = new ArrayList<>();

    public Search(Shell shell) {
        super(shell);
    }

    @Override
    public void execute(String arguments) {
        Database db = Search.get(shell.getIdentifier());
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

    public static Database get(String id) {
        for (Database db : dbs) {
            if (db.id.equals(id)) {
                return db;
            }
        }
        return null;
    }

    public static void add(String id) {
        Database db = new Database();
        db.id = id;
        dbs.add(db);
    }
}
