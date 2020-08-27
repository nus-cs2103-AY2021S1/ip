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
        EXIT("BYE"), DELETE("DELETE "), LIST("LIST"),
        DONE("DONE "), TODO("TODO "), EVENT("EVENT "),
        DEADLINE("DEADLINE "), DELETEALL("DELETE ALL");
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
                else if (input.length() >= 5 && input.substring(0,5).toUpperCase().equals(Commands.DONE.getString())) {
                    try {
                        int index = Integer.parseInt(input.substring(5).trim());
                        setTaskDone(index);
                    } catch (NumberFormatException ex) {
                        printError("Please input an Integer for the \"Done\" command.");
                    } catch (DukeException ex) {
                        printError(ex.getMessage());
                    }
                }
                else if (input.toUpperCase().equals(Commands.DELETEALL.getString())) {
                    try {
                        deleteAllTasks();
                    } catch (DukeException ex) {
                        printError(ex.getMessage());
                    }
                }
                else if (input.length() >= 7 && input.substring(0,7).toUpperCase().equals(Commands.DELETE.getString())) {
                    try {
                        int index = Integer.parseInt(input.substring(7).trim());
                        deleteTask(index);
                    } catch (NumberFormatException ex) {
                        printError("Please input an Integer for the \"Delete\" command.");
                    } catch (DukeException ex) {
                        printError(ex.getMessage());
                    }
                }
                else if (input.length() >= 5 && input.substring(0,5).toUpperCase().equals(Commands.TODO.getString())) {
                    try {
                        String name = input.substring(5).trim();
                        addToStorageList(Todo.createTodo(name));
                    } catch (DukeException ex) {
                        printError(ex.getMessage());
                    }
                }
                else if (input.length() >= 6 && input.substring(0,6).toUpperCase().equals(Commands.EVENT.getString())) {
                    try {
                        int limiterPosition = input.indexOf(" /at ");
                        String name;
                        String timing;
                        if (limiterPosition != -1) {
                            name = input.substring(6, limiterPosition).trim();
                            timing = input.substring(limiterPosition + 5).trim();
                        } else {
                            throw new DukeException("Missing date for Event task");
                        }
                        addToStorageList(Event.createEvent(name, timing));
                    } catch (DukeException ex) {
                        printError(ex.getMessage());
                    }
                }
                else if (input.length() >= 9 && input.substring(0,9).toUpperCase().equals(Commands.DEADLINE.getString())) {
                    try {
                        int limiterPosition = input.indexOf(" /by ");
                        String name;
                        String dueDate;

                        if (limiterPosition != -1) {
                            name = input.substring(9, limiterPosition).trim();
                            dueDate = input.substring(limiterPosition + 5).trim();
                        } else {
                            throw new DukeException("Missing deadline for Deadline task");
                        }
                        addToStorageList(Deadline.createDeadline(name, dueDate));
                    } catch (DukeException ex) {
                        printError(ex.getMessage());
                    }
                }
                else {
                    printError("Sorry I don't know what that means :(");
                }
            }
        }
    }

    private void echoBack(String message) {
        System.out.printf(MESSAGE_TEMPLATE_VERBAL, message);
    }

    private void addToStorageList(Task obj) throws DukeException{
        if (obj.getName().length() == 0) {
            throw new DukeException(obj.missingNameError());
        }
        this.storageList.add(obj);
        String numOfTasks = this.storageList.size() == 1 ? "1 task" : this.storageList.size() + " tasks";
        String message = "Got it. I've added the following task: " + NEW_LINE
                + PADDING + "  " + obj.toString() + NEW_LINE
                + PADDING + "Now you have "  + numOfTasks + " in total.";
        System.out.printf(MESSAGE_TEMPLATE, message);
    }

    private void displayStorageList() {
        if (this.storageList.size() == 0) {
            System.out.printf(MESSAGE_TEMPLATE, "Your list is empty, try adding some tasks to it");
            return;
        }
        String output = "You have the following tasks in your list:" + NEW_LINE;
        int counter = 1;
        for (Task ele: this.storageList) {
            output += PADDING + counter + ". " + ele.toString() + NEW_LINE;
            counter++;
        }
        output = output.substring(0, output.length()-1);
        System.out.printf(MESSAGE_TEMPLATE, output);
    }

    private void setTaskDone(int index) throws DukeException {
        if (index <= 0 || index > this.storageList.size()) {
            throw new DukeException("Invalid index, cannot find task.");
        }
        this.storageList.get(index-1).setDoneness(true);
        String message = "Nice job! I'll mark that as done:" + NEW_LINE + PADDING
                + "  " + this.storageList.get(index-1).toString();
        System.out.printf(MESSAGE_TEMPLATE, message);
    }

    private void deleteTask(int index) throws DukeException {
        if (index <= 0 || index > this.storageList.size()) {
            throw new DukeException("Invalid index, cannot find task.");
        }
        Task task = this.storageList.get(index-1);
        this.storageList.remove(index-1);
        String numOfTasks = this.storageList.size() == 1 ? "1 task" : this.storageList.size() + " tasks";
        String message = "Noted. The following task has been removed:"
                + NEW_LINE + PADDING + "  " + task.toString() + NEW_LINE
                + PADDING + "Now you have "  + numOfTasks + " left.";;
        System.out.printf(MESSAGE_TEMPLATE, message);
    }

    private void deleteAllTasks() throws DukeException {
        if (this.storageList.size() == 0) {
            throw new DukeException("Your list is already empty.");
        }
        this.storageList.clear();
        String message = "Noted. All tasks have been removed.";
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
