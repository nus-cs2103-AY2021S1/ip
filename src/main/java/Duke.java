import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private final String VERSION_NUMBER = "1.0.0";
    private final Scanner sc = new Scanner(System.in);
    private final String NEW_LINE = "\n";
    private final String HORIZONTAL_LINE =
            "    ____________________________________________________________";
    private final String PADDING = "      ";

    private enum Commands {
        EXIT("BYE"), ADD("ADD"), LIST("LIST"),
        DONE("DONE"), TODO("TODO"), EVENT("EVENT"),
        DEADLINE("DEADLINE");
        private final String str;
        Commands(String str){
            this.str = str;
        }
        public String getString() {
            return this.str;
        }
    }

    private final String MESSAGE_TEMPLATE = HORIZONTAL_LINE + NEW_LINE + PADDING + "%s"
            + NEW_LINE + HORIZONTAL_LINE + NEW_LINE + NEW_LINE;
    private final String MESSAGE_TEMPLATE_VERBAL = HORIZONTAL_LINE + NEW_LINE + PADDING + "Deuk: %s"
            + NEW_LINE + HORIZONTAL_LINE + NEW_LINE + NEW_LINE;
    private final String MESSAGE_TEMPLATE_ERROR = HORIZONTAL_LINE + NEW_LINE + PADDING
            + "☹ OOPS!!! %s"
            + NEW_LINE + HORIZONTAL_LINE + NEW_LINE + NEW_LINE;
    private final List<Task> storageList = new ArrayList<>();

    public void init() {
        sayHello();

        while(true) {
            if (sc.hasNext()) {
                String input = sc.nextLine().trim();
                if (input.toUpperCase().equals(Commands.EXIT.getString())) { // to make command case-insensitive
                    sayGoodbye();
                    break;
                }
                if (input.toUpperCase().equals(Commands.LIST.getString())) {
                    displayStorageList();
                }
                else if (input.length() >= 4 && input.substring(0,4).toUpperCase().equals(Commands.DONE.getString())) {
                    try {
                        int index = Integer.parseInt(input.substring(4).trim());
                        setTaskDone(index);
                    } catch (NumberFormatException ex) {
                        printError("Please input an Integer for the \"Done\" command.");
                    }
                }
                else if (input.length() >= 4 && input.substring(0,4).toUpperCase().equals(Commands.TODO.getString())) {
                    try {
                        String name = input.substring(4).trim();
                        addToStorageList(new Todo(name));
                    } catch (NumberFormatException ex) {
                        printError("Please input an Integer for the \"Done\" command.");
                    }
                }
                else {
                    addToStorageList(new Task(input));
                }
            }
        }
    }

    private void echoBack(String message) {
        System.out.printf(MESSAGE_TEMPLATE_VERBAL, message);
    }

    private void addToStorageList(Task obj) {
        this.storageList.add(obj);
        String numOfTasks = this.storageList.size() == 1 ? "1 task" : this.storageList.size() + " tasks";
        String message = "Got it. I've added the following task: " + NEW_LINE
                + PADDING + "  " + obj.toString() + NEW_LINE
                + PADDING + "Now you have "  + numOfTasks + " in total.";
        System.out.printf(MESSAGE_TEMPLATE, message);
    }

    private void displayStorageList() {
        String output = "You have the following tasks in your list:" + NEW_LINE;
        int counter = 1;
        for (Task ele: this.storageList) {
            output += PADDING + counter + ". " + ele.toString() + NEW_LINE;
            counter++;
        }
        output = output.substring(0, output.length()-1);
        System.out.printf(MESSAGE_TEMPLATE, output);
    }

    private void setTaskDone(int index) {
        // TODO: throw DukeException instead
        if (index <= 0 || index > this.storageList.size()) {
            System.out.printf(MESSAGE_TEMPLATE_ERROR, "Invalid index, cannot find task.");
            return;
        }
        this.storageList.get(index-1).setDoneness(true);
        String message = "Nice job! I'll mark that as done:" + NEW_LINE + PADDING + this.storageList.get(index-1).toString();
        System.out.printf(MESSAGE_TEMPLATE, message);
    }

    private void printError(String error) {
        System.out.printf(MESSAGE_TEMPLATE_ERROR, error);
    }

    private void sayGoodbye() {
        System.out.printf(MESSAGE_TEMPLATE_VERBAL, "Goodbye, hope to see you again!");
    }

    private void sayHello() {
        String logo =
                  "     _____             _    \n"
                + "    |  __ \\           | |   \n"
                + "    | |  | | ___ _   _| | __\n"
                + "    | |  | |/ _ \\ | | | |/ /\n"
                + "    | |__| |  __/ |_| |   < \n"
                + "    |_____/ \\___|\\__,_|_|\\_\\  v" + VERSION_NUMBER + "\n";
        String introMessage = "I'm Deuk, nice to meet you\n" + PADDING +
                "How can I be of service today?";
        System.out.printf(logo + MESSAGE_TEMPLATE_VERBAL, introMessage);
    }

    public static void main(String[] args) {
        new Duke().init();
    }
}
