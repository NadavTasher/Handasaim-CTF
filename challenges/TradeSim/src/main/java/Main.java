import org.quteshell.Shell;
import org.quteshell.commands.Echo;
import org.quteshell.commands.History;
import org.quteshell.commands.Welcome;
import shebang.commands.*;

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
        Shell.Configuration.setPromptState(false);
        Shell.Configuration.setColorState(false);
        Shell.Configuration.Commands.remove(Welcome.class);
        Shell.Configuration.Commands.remove(Echo.class);
        Shell.Configuration.Commands.remove(History.class);
        Shell.Configuration.Commands.add(buy.class);
        Shell.Configuration.Commands.add(finish.class);
        Shell.Configuration.Commands.add(inventory.class);
        Shell.Configuration.Commands.add(sell.class);
        Shell.Configuration.Commands.add(traders.class);
        Shell.Configuration.Commands.add(valueof.class);
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
