import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Store store = new Store();

        String[] welcomeTexts = new String[]{
                "Hello, my name is ",
                " ____        _        ",
                "|  _ \\ _   _| | _____ ",
                "| | | | | | | |/ / _ \\",
                "| |_| | |_| |   <  __/",
                "|____/ \\__,_|_|\\_\\___|",
                "How may I help you?"
        };

        StringUtils.printWithWrapper(welcomeTexts, false);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                processInput(input.trim(), store);
            } catch (DukeException e) {
                e.printError();
            }
            input = sc.nextLine();
        }

        StringUtils.printWithWrapper(new String[]{"Bye bye! Hope to see you again soon!"}, false);
    }

    private static void processInput(String input, Store store) throws DukeException {
        if (input.equals("")) {
            throw new DukeException("Empty input");
        }

        String[] splitInput = input.split(" ");
        String firstInput = splitInput[0].trim();
        String[] sections;

        Command command;
        try {
            command = Command.toCommand(firstInput);
        } catch (DukeException e) {
            throw e;
        }

        switch (command) {
            case SAVE:
                try {
                    store.save();
                } catch (IOException e) {
                    throw new DukeIOException(e.getMessage());
                }
                break;
            case LIST:
                store.list();
                break;
            case DONE:
                store.markTaskAsDone(Integer.parseInt(splitInput[1].trim()));
                break;
            case DELETE:
                store.delete(Integer.parseInt(splitInput[1].trim()));
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                try {
                    sections = StringUtils.parseInputSections(input, firstInput, getTaskBreakPt(firstInput));
                    store.add(sections, firstInput);
                } catch (DukeException e) {
                    throw e;
                }
                break;
        }
    }

    private static String getTaskBreakPt(String taskName) {
        switch (taskName) {
            case "deadline":
                return "/by";
            case "event":
                return "/at";
            default:
                return "";
        }
    }
}
