
import org.quteshell.Shell;
import org.quteshell.commands.Echo;
import org.quteshell.commands.History;
import org.quteshell.commands.Welcome;
import xdb.commands.Search;

import java.net.ServerSocket;
import java.util.ArrayList;

/**
 * Copyright (c) 2019 Nadav Tasher
 * https://github.com/NadavTasher/Quteshell/
 **/

public class Main {

    private static final int PORT = 8000;
    private static final ArrayList<Shell> quteshells = new ArrayList<>();

    private static boolean listening = true;

    public static void main(String[] args) {
        Shell.Configuration.Commands.remove(Welcome.class);
        Shell.Configuration.Commands.remove(Echo.class);
        Shell.Configuration.Commands.remove(History.class);
        Shell.Configuration.Commands.add(Search.class);
        Shell.Configuration.setColorState(false);
        Shell.Configuration.setName("XDB");
        Shell.Configuration.setOnConnect(shell -> {
            shell.writeln("----- Welcome to my phonebook! -----");
            shell.writeln(" Type 'help' for a list of commands ");
            // Database setup
            Search.add(shell.getIdentifier());
        });
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (listening) {
                quteshells.add(new Shell(serverSocket.accept()));
            }
        } catch (Exception e) {
            System.out.println("Host - " + e.getMessage());
        }
    }
}
