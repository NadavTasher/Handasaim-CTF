package quteshell.xdb.commands;

import quteshell.Quteshell;
import quteshell.command.Command;
import quteshell.command.Elevation;
import quteshell.commands.Help;
import quteshell.xdb.Database;

import java.util.ArrayList;

@Elevation(Elevation.DEFAULT)
@Help.Description("connect connects you to a database")
public class Connect implements Command {

    private static final ArrayList<Database> dbs = new ArrayList<>();

    @Override
    public void execute(Quteshell shell, String arguments) {
        if (get(shell.getID())==null) {
            Database db = new Database();
            db.id = shell.getID();
            dbs.add(db);
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
}
