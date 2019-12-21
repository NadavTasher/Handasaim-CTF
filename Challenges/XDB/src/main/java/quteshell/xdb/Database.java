package quteshell.xdb;

import java.util.ArrayList;
import java.util.Random;

public class Database {

    private static final String[] NAMES = {
            "Oliver", "George", "Harry", "Noah", "Jack", "Charlie", "Leo", "Jacob", "Freddie", "Alfie", "Archie", "Theo", "Oscar", "Arthur", "Thomas", "Logan", "Henry", "Joshua", "James", "William", "Max", "Isaac", "Lucas", "Ethan", "Teddy", "Finley", "Mason", "Harrison", "Hunter", "Alexander", "Daniel", "Joseph", "Tommy", "Arlo", "Reggie", "Edward", "Jaxon", "Adam", "Sebastian", "Rory", "Riley", "Dylan", "Elijah", "Carter", "Albie", "Louie", "Toby", "Benjamin", "Reuben", "Jude", "Samuel", "Harley", "Luca", "Frankie", "Ronnie", "Jenson", "Hugo", "Jake", "David", "Theodore", "Roman", "Bobby", "Alex", "Caleb", "Ezra", "Ollie", "Finn", "Jackson", "Zachary", "Jayden", "Harvey", "Albert", "Lewis", "Blake", "Stanley", "Elliot", "Grayson", "Liam", "Louis", "Matthew", "Elliott", "Tyler", "Luke", "Michael", "Gabriel", "Ryan", "Dexter", "Kai", "Jesse", "Leon", "Nathan", "Ellis", "Connor", "Jamie", "Rowan", "Sonny", "Dominic", "Eli", "Aaron", "Jasper", "Olivia", "Amelia", "Isla", "Ava", "Emily", "Sophia", "Grace", "Mia", "Poppy", "Ella", "Lily", "Evie", "Isabella", "Sophie", "Ivy", "Freya", "Harper", "Willow", "Charlotte", "Jessica", "Rosie", "Daisy", "Alice", "Elsie", "Sienna", "Florence", "Evelyn", "Phoebe", "Aria", "Ruby", "Isabelle", "Esme", "Scarlett", "Matilda", "Sofia", "Millie", "Eva", "Layla", "Chloe", "Luna", "Maisie", "Lucy", "Erin", "Eliza", "Ellie", "Mila", "Imogen", "Bella", "Lola", "Molly", "Maya", "Violet", "Lilly", "Holly", "Thea", "Emilia", "Hannah", "Penelope", "Harriet", "Georgia", "Emma", "Lottie", "Nancy", "Rose", "Amber", "Elizabeth", "Gracie", "Zara", "Darcie", "Summer", "Hallie", "Aurora", "Ada", "Anna", "Orla", "Robyn", "Bonnie", "Abigail", "Darcy", "Eleanor", "Arabella", "Lexi", "Clara", "Heidi", "Lyla", "Annabelle", "Jasmine", "Nevaeh", "Victoria", "Amelie", "Myla", "Maria", "Julia", "Niamh", "Mya", "Annie", "Darcey", "Zoe", "Felicity", "Iris"
    };

    public String id;

    private ArrayList<Table> tables = new ArrayList<>();

    public Database() {
        Table privates = new Table("flag");
        for (int i = 0; i < 49; i++)
            privates.add("Flag", "");
        privates.add("Flag", "KAF{s1mpl3_and_1ns3cure_}");
        tables.add(privates);
        Table publics = new Table("phonebook");
        for (int i = 0; i < 50; i++)
            publics.add(NAMES[new Random().nextInt(NAMES.length)], "+" + (new Random().nextInt(99999999) + 100000000));
        tables.add(publics);
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public Table getTable(String table) throws Exception {
        for (Table t : tables)
            if (t.name.equals(table))
                return t;
        throw new Exception("No such table");
    }

    public ArrayList<Table.Entry> dynamic(String query) throws Exception {
        query = eval(query);
        System.out.println(query);
        String[] split = query.split(" ");
        String table = null, entry = null;
        if (split.length > 1)
            if (split[0].equals("at")) {
                table = split[1];
                if (split.length > 3)
                    if (split[2].equals("is")) {
                        entry = split[3];
                    }
            }
        if (entry == null)
            return getTable(table).getEntries();
        return getTable(table).get(entry);
    }

    private String eval(String source) {
        int index = source.indexOf("'-'");
        if (index > 0) {
            return eval(source.substring(0, index - 1) + source.substring(index + 3));
        } else {
            return source;
        }
    }

    public class Table {

        private String name;
        private ArrayList<Entry> entries = new ArrayList<>();

        public Table(String name) {
            this.name = name;
        }

        public void add(String name, String value) {
            entries.add(new Entry(name, value));
        }

        public void remove(String name, int index) {
            entries.remove(get(name, index));
        }

        public Entry get(String name, int index) {
            ArrayList<Entry> entries = get(name);
            if (index < entries.size())
                return entries.get(index);
            return null;
        }

        public ArrayList<Entry> get(String name) {
            ArrayList<Entry> list = new ArrayList<>();
            for (Entry entry : entries) {
                if (entry.getName().toLowerCase().equals(name.toLowerCase()))
                    list.add(entry);
            }
            return list;
        }

        public ArrayList<Entry> getEntries() {
            return entries;
        }

        public class Entry {

            private String name, value;

            public Entry(String name, String value) {
                this.name = name;
                this.value = value;
            }

            public String getName() {
                return name;
            }

            public String getValue() {
                return value;
            }

            public String getLeft() {
                return getName();
            }

            public String getRight() {
                return getValue();
            }
        }
    }
}
