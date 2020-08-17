import java.util.Scanner;

public class Duke {

    private static final String DIVIDER = "________________________________________________________\n";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String EXIT_COMMAND = "bye";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";

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
            try {
                String[] rawInput = input.split(" ", 2);
                String command = rawInput[0];
                switch (command) {
                case LIST_COMMAND:
                    System.out.printf(prependIndent(storage.listItems(), 5));
                    break;
                case DONE_COMMAND:
                    try {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        System.out.printf(prependIndent(storage.markAsDone(index), 5));
                        break;
                    } catch (NumberFormatException e) {
                        throw new DukeException("This isn't harry potter, please use only integers.");
                    }
                case TODO_COMMAND:
                    try {
                        ToDo newToDo = new ToDo(rawInput[1]);
                        System.out.printf(prependIndent(storage.add(newToDo), 5));
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("I know your life is empty but your todo can't be empty.");
                    }
                case DEADLINE_COMMAND:
                    try {
                        String deadlineDescription = rawInput[1].split("/")[0].trim();
                        try {
                            String due = rawInput[1].split("/by", 2)[1].trim();
                            Deadline newDeadline = new Deadline(deadlineDescription, due);
                            System.out.printf(prependIndent(storage.add(newDeadline), 5));
                            break;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("So you never did plan on doing it huh...");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("What are you rushing for? To wait?");
                    }
                case EVENT_COMMAND:
                    try {
                        String eventDescription = rawInput[1].split("/")[0].trim();
                        try {
                            String time = rawInput[1].split("/at", 2)[1].trim();
                            Event newEvent = new Event(eventDescription, time);
                            System.out.printf(prependIndent(storage.add(newEvent), 5));
                            break;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("Wow that sure is one long event...");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("Are you going to attend a nameless event?");
                    }
                case DELETE_COMMAND:
                    try {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        System.out.printf(prependIndent(storage.deleteTask(index), 5));
                        break;
                    } catch (NumberFormatException e) {
                        throw new DukeException("This isn't harry potter, please use only integers.");
                    }
                default:
                    throw new DukeException("Wakarimasen~");
                }
            } catch (DukeException dukeEx) {
                System.out.printf(prependIndent(dukeEx.getMessage(), 5));
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
