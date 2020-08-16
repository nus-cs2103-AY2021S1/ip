package main.java;
import java.util.*;

public class Duke {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static void startMessage() {
        System.out.println("┌────────────────────────────────────────────────┐");
    }

    private static void endMessage() {
        System.out.println("└────────────────────────────────────────────────┘");
    }

    private static void greet() {
        startMessage();
        System.out.println("Hello from\n" + logo);
        endMessage();
    }

    private static void handleCommand(String cmd) {
        startMessage();
        if (!cmd.equals("bye")) {
            System.out.println(cmd);
        } else {
            System.out.println("See you again!");
        }
        endMessage();
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        String input;
        do {
            input = sc.next();
            handleCommand((input));
        }
        while (!input.equals("bye"));
    }
}
