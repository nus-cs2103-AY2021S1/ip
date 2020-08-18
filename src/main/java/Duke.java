package main.java;

import java.util.ArrayList;

public class Duke {
    public static boolean exitLoop = false;
    public static ArrayList<String> inputThings = new ArrayList<>();

    private static void greet() {
        UIPrint.drawLine(UIPrint.star, 50);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        UIPrint.drawLine(UIPrint.star, 50);
    }

    private static void echo(String str) {
        UIPrint.drawLine(UIPrint.star, 50);
        System.out.println(str);
        UIPrint.drawLine(UIPrint.star, 50);
    }

    private static void storeInput(String str) {
        UIPrint.drawLine(UIPrint.star, 50);
        System.out.println("added: " + str);
        UIPrint.drawLine(UIPrint.star, 50);

        inputThings.add(str);
    }

    private static boolean checkCommand(String str) {
        String[] inputParts = str.split(" ", 2);
        String possibleCommand = inputParts[0];
        Command command = DukeCommandSet.getInstance().getCommand(possibleCommand);

        if (command != null) {
            command.execute();
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        while (!exitLoop) {
            String inputLine = UserInput.getOneLine();

            boolean hasCommand = checkCommand(inputLine);

            if (!hasCommand) {
                storeInput(inputLine);
            }
        }
    }
}
