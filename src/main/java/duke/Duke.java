package duke;

import duke.Command.Command;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws Exception {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        run();
    }

    private static void run() throws Exception {
        print(Message.MESSAGE_WELCOME);

        Scanner scanner = new Scanner(System.in);
        String input;

        while (!(input = scanner.nextLine()).equals("bye")) {
            print(input);
        }

        scanner.close();
        print(input);

    }

    private static void print(String message) throws Exception {
        if (message.equals(Message.MESSAGE_WELCOME)) {
            System.out.println(Message.INDENT + Message.BORDERS + "\n"
                    + Message.INDENT + Message.MESSAGE_WELCOME + "\n"
                    + Message.INDENT + Message.BORDERS);
        } else {
            System.out.println(Message.INDENT + Message.BORDERS + "\n"
                    + Message.INDENT + Command.parse(message) + "\n"
                    + Message.INDENT + Message.BORDERS);
        }
    }
}
