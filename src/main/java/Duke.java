import java.util.Scanner;

public class Duke {

    private static final String DIVIDER = "________________________________________________________\n";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String EXIT_COMMAND = "bye";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";

    private static String prependIndent(String content, int indent) {
        String spaces = "";
        for (int i = 0; i < indent; i++) {
            spaces += " ";
        }
        String[] contentArray = content.split("\n");
        String result = "";
        for (int i = 0; i < contentArray.length; i++) {
            result += spaces + contentArray[i] + "\n";
        }
        return result;
    }

    private static void greet() {
        String welcomeMessage = "Konnichiwa!\n"
                + "What can I do for you?\n";
        System.out.printf(prependIndent(DIVIDER, 4));
        System.out.printf(prependIndent(welcomeMessage, 5));
        System.out.println(prependIndent(DIVIDER, 4));
    }

    private static void exit() {
        String exitMessage = "Ja ne!\n";
        System.out.printf(prependIndent(DIVIDER, 4));
        System.out.printf(prependIndent(exitMessage, 5));
        System.out.println(prependIndent(DIVIDER, 4));
    }

    private static void chat() {
        Store storage = new Store();
        Scanner sc = new Scanner(System.in);
        String input = "";
        input = sc.nextLine();
        while (!input.equals(EXIT_COMMAND)) {
            System.out.print(prependIndent(DIVIDER, 4));
            String[] rawInput = input.split(" ", 2);
            String command = rawInput[0];
            switch(command) {
            case LIST_COMMAND:
                System.out.printf(prependIndent(storage.listItems(), 5));
                break;
            case DONE_COMMAND:
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                System.out.printf(prependIndent(storage.markAsDone(index), 5));
                break;
            case TODO_COMMAND:
                ToDo newToDo = new ToDo(rawInput[1]);
                System.out.printf(prependIndent(storage.add(newToDo), 5));
                break;
            case DEADLINE_COMMAND:
                String deadlineDescription = rawInput[1].split("/")[0].trim();
                String due = rawInput[1].split("/", 2)[1].split(" ", 2)[1].trim();
                Deadline newDeadline = new Deadline(deadlineDescription, due);
                System.out.printf(prependIndent(storage.add(newDeadline), 5));
                break;
            case EVENT_COMMAND:
                String eventDescription = rawInput[1].split("/")[0].trim();
                String time = rawInput[1].split("/", 2)[1].split(" ", 2)[1].trim();
                Event newEvent = new Event(eventDescription, time);
                System.out.printf(prependIndent(storage.add(newEvent), 5));
                break;
            }
            System.out.println(prependIndent(DIVIDER, 4));
            input = sc.nextLine();
        }
    }

    public static void main(String[] args) {
        greet();
        chat();
        exit();
    }
}
