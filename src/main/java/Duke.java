import java.util.Scanner;

import operation.Operation;

public class Duke {
    private static String startMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String message = "Greetings, what may I do for you?";
        return logo + message;
    }

    private static String getSeparatorLine() {
        return "-------------------------------------------------";
    }

    private static void runDuke() {
        boolean done = false;
        Scanner userInput = new Scanner(System.in);
        CommandParser commandParser = new CommandParser();

        while (!done) {
            System.out.printf("\n");
            String command = userInput.nextLine();

            System.out.println(Duke.getSeparatorLine());
            Operation operation = commandParser.parse(command);
            operation.execute();
            System.out.println(Duke.getSeparatorLine());

            if (operation.isExit()) {
                done = true;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Duke.startMessage());
        Duke.runDuke();
    }
}
