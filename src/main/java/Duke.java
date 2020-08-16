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
        String firstInput = splitInput[0];
        String[] sections;

        switch (firstInput) {
            case "list":
                store.list();
                break;
            case "done":
                store.markTaskAsDone(Integer.parseInt(splitInput[1]));
                break;
            case "todo":
            case "deadline":
            case "event":
                try {
                    sections = StringUtils.parseInputSections(input, firstInput, getTaskBreakPt(firstInput));
                    store.add(sections, firstInput);
                } catch (DukeException e) {
                    throw e;
                }
                break;
            default:
                throw new DukeException("Aww! The first word of your input is wrong!");
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
