import java.io.File;
import java.util.Scanner;

public class Duke {

    private static final String DIVIDER = "________________________________________________________\n";

    enum Command {
        EXIT("bye"),
        LIST("list"),
        DONE("done"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete");

        private final String keyword;

        Command(String keyword) {
            this.keyword = keyword;
        }

        public static Command findCommand(String keyword) throws DukeException {
            for (Command c : values()) {
                if (keyword.equals(c.keyword)) {
                    return c;
                }
            }
            throw new DukeException("Wakarimasen~");
        }
    }

    // TODO: Consider moving prependIndent to utils class/package for usage in other classes.
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

    private static void formattedPrint(String content) {
        System.out.print(prependIndent(DIVIDER, 4));
        System.out.print(prependIndent(content, 4));
        System.out.println(prependIndent(DIVIDER, 4));
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
        Store storage = FileSaver.load(FileSaver.FILE_PATH);
        Scanner sc = new Scanner(System.in);
        String rawInput = "";
        Boolean exit = false;
        while (!exit) {
            rawInput = sc.nextLine();
            try {
                String[] inputs = rawInput.split(" ", 2);
                // By spec, inputs is guaranteed to have at least one element.
                String commandString = inputs[0];
                Command command = Command.findCommand(commandString);
                switch (command) {
                case EXIT:
                    FileSaver.save(storage, FileSaver.FILE_PATH);
                    exit = true;
                    break;
                case LIST:
                    formattedPrint(prependIndent(storage.listItems(), 1));
                    break;
                case DONE:
                    try {
                        int index = Integer.parseInt(rawInput.split(" ")[1]) - 1;
                        formattedPrint(prependIndent(storage.markAsDone(index), 1));
                        break;
                    } catch (NumberFormatException e) {
                        throw new DukeException("This isn't harry potter, please use only integers.");
                    }
                case DEADLINE:
                    try {
                        String deadlineDescription = inputs[1].split("/")[0].trim();
                        try {
                            String due = inputs[1].split("/by", 2)[1].trim();
                            Deadline newDeadline = new Deadline(deadlineDescription, due);
                            formattedPrint(prependIndent(storage.add(newDeadline), 1));
                            break;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("So you never did plan on doing it huh...");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("What are you rushing for? To wait?");
                    }
                case TODO:
                    try {
                        ToDo newToDo = new ToDo(inputs[1]);
                        formattedPrint(prependIndent(storage.add(newToDo), 1));
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("I know your life is empty but your todo can't be empty.");
                    }
                case EVENT:
                    try {
                        String eventDescription = inputs[1].split("/")[0].trim();
                        try {
                            String time = inputs[1].split("/at", 2)[1].trim();
                            Event newEvent = new Event(eventDescription, time);
                            formattedPrint(prependIndent(storage.add(newEvent), 1));
                            break;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("Wow that sure is one long event...");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("Are you going to attend a nameless event?");
                    }
                case DELETE:
                    try {
                        int index = Integer.parseInt(rawInput.split(" ")[1]) - 1;
                        formattedPrint(prependIndent(storage.deleteTask(index), 1));
                        break;
                    } catch (NumberFormatException e) {
                        throw new DukeException("This isn't harry potter, please use only integers.");
                    }
                }
            } catch (DukeException e) {
                formattedPrint(prependIndent(e.getMessage(), 1));
            }
        }
    }

    public static void main(String[] args) {
        greet();
        chat();
        exit();
    }
}
