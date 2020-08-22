package duke;

import duke.Command.Command;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        run();

    }

    private static void run() {
        print(Message.MESSAGE_WELCOME);

        Scanner scanner = new Scanner(System.in);
        String input;

        while (!(input = scanner.nextLine()).equals("bye")) {
            print(input);
        }

        scanner.close();
        print(input);
    }

    private static void print(String message) {
        String messageB = Message.BORDERS;
        try {
            if (message.equals(Message.MESSAGE_WELCOME)) {
                System.out.println(messageB + "\n"
                        + Message.MESSAGE_WELCOME + "\n"
                        + messageB);
            } else {
                System.out.println(messageB + "\n"
                        + Command.parse(message) + "\n"
                        + messageB);
            }
        } catch (DukeException e) {
            System.out.println(messageB + "\n" + e + "\n"
                    + messageB);
        }
    }
}