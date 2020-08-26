import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Duke class that contains the main logic for the chat bot DUKE.
 */
public class Duke {
    private Storage storage;
    private String output;
    private ArrayList<Task> list;
    private boolean exited;

    /**
     * File path of saved text file
     */
    // If testing using runtest.sh, use pathname "../data/duke.txt", else use "/data/duke.txt"
    public static final String FILEPATH = System.getProperty("user.dir").endsWith("text-ui-test")
            ? "../data/duke.txt"
            : "data/duke.txt";

    /**
     * UI line divider
     */
    public static final String lineDivider = "------------------------------------------\n";

    /**
     * Introduction message
     */
    public static final String GREET =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n"
                    + lineDivider
                    + "Hello! I'm Duke\nWhat can I do for you?\n";

    public static final String FAREWELL = "Bye. Hope to see you again soon!\n";

    private Duke() throws IOException {
        ArrayList<Task> tempList;
        this.storage = new Storage(FILEPATH);
        this.output = GREET;
        try {
            tempList = storage.getList();
        } catch (FileNotFoundException e) {
            System.out.println("New list initialized.");
            tempList = new ArrayList<>();
        }
        this.list = tempList;
        this.exited = false;
    }

    private Duke(String output, ArrayList<Task> list) throws IOException {
        this.storage = new Storage(FILEPATH);
        this.output = output;
        this.list = list;
        this.exited = false;
    }

    private Duke(String output, ArrayList<Task> list, boolean exited) throws IOException {
        this.storage = new Storage(FILEPATH);
        this.output = output;
        this.list = list;
        this.exited = exited;
    }

    private void updateStorage() throws IOException {
        storage.updateFile(this.list);
    }

    private String addMessage(Task t) {
        return "Got it. I've added this task:\n"
                + t.toString()
                + "\nNow you have "
                + this.list.size()
                + " tasks in the list.\n";
    }

    private String addTodo(String description) throws IOException {
        ToDo todo = new ToDo(description);
        this.list.add(todo);
        updateStorage();
        return addMessage(todo);
    }

    private String addDeadline(String description, String by) throws IOException {
        Deadline deadline = new Deadline(description, by);
        this.list.add(deadline);
        updateStorage();
        return addMessage(deadline);
    }

    private String addEvent(String description, String at) throws IOException {
        Event event = new Event(description, at);
        this.list.add(event);
        updateStorage();
        return addMessage(event);
    }

    private String showList() {
        StringBuffer lst = new StringBuffer();
        lst.append("Here are the tasks in your list:\n");
        int listSize = this.list.size();
        for (int i = 0; i < listSize; i++) {
            lst.append((i + 1) + ". " + this.list.get(i).toString() + "\n");
            //if (i + 1 != listSize) {
            //    lst.append((i + 1) + ". " + this.list.get(i).toString() + "\n");
            //} else {
            //    lst.append((i + 1) + ". " + this.list.get(i).toString());
            //}
        }
        return lst.toString();
    }

    private String doneTask(int num) throws InvalidDoneInputException, IOException {
        if (num > this.list.size() || num <= 0) {
            throw new InvalidDoneInputException();
        }
        int index = num - 1;
        this.list.get(index).markAsDone();
        updateStorage();
        return "Nice! I've marked this task as done:\n"
                + this.list.get(index).toString() + "\n";
    }

    private String deleteTask(int num) throws InvalidDeleteInputException, IOException {
        if (num > this.list.size() || num <= 0) {
            throw new InvalidDeleteInputException();
        }
        int index = num - 1;
        String taskInfo = this.list.get(index).toString();
        this.list.remove(index);
        updateStorage();
        return "Noted. I've removed this task:\n"
                + taskInfo
                + "\nNow you have "
                + this.list.size()
                + " tasks in the list.\n";
    }

    private Duke execute(Command c) throws IOException{
        switch (c) {
            case LIST:
                return new Duke(showList(), this.list);
            case EXIT:
                return new Duke(FAREWELL, this.list, true);
            default:
                return this;
        }
    }

    private Duke execute(Command c, String input) throws DukeException, IOException {
        switch (c) {

            case DONE:
                try {
                    int num = Integer.parseInt(input.substring(4).replaceAll("\\s+", ""));
                    String outputDone = doneTask(num);
                    return new Duke(outputDone, this.list);
                } catch (InvalidDoneInputException e) {
                    throw e;
                } catch (NumberFormatException e) {
                    throw new InvalidDoneInputException();
                }

            case DELETE:
                try {
                    int num = Integer.parseInt(input.substring(6).replaceAll("\\s+", ""));
                    String outputDelete = deleteTask(num);
                    return new Duke(outputDelete, this.list);
                } catch (InvalidDeleteInputException e) {
                    throw e;
                } catch (NumberFormatException e) {
                    throw new InvalidDeleteInputException();
                }

            case TODO:
                if (input.split(" ").length < 2) {
                    throw new InvalidTodoInputException();
                }
                String outputTodo = addTodo(input.substring(5));
                return new Duke(outputTodo, this.list);

            case DEADLINE:
                if (input.length() < 9) {
                    throw new NullDeadlineInputException();
                }
                String deadlineTask = input.substring(9);
                String[] deadlineTaskArray = deadlineTask.split(" /by ");
                if (deadlineTaskArray.length != 2) {
                    throw new InvalidDeadlineInputException();
                }
                String deadlineDescription = deadlineTaskArray[0];
                String by = deadlineTaskArray[1];
                String outputDeadline = addDeadline(deadlineDescription, by);
                return new Duke(deadlineDescription, this.list);

            case EVENT:
                if (input.length() < 6) {
                    throw new NullEventInputException();
                }
                String eventTask = input.substring(6);
                String[] eventTaskArray = eventTask.split(" /at ");
                if (eventTaskArray.length != 2) {
                    throw new InvalidEventInputException();
                }
                String eventDescription = eventTaskArray[0];
                String at = eventTaskArray[1];
                String outputEvent = addEvent(eventDescription, at);
                return new Duke(outputEvent, this.list);

            default:
                return this;
        }
    }

    public String getOutput() {
        return lineDivider + this.output + lineDivider;
    }

    public boolean getExited() {
        return this.exited;
    }

    public Duke processCommand(String input) throws DukeException, IOException {
        try {
            if (input.equals("list")) {
                return execute(Command.LIST);
            } else if (input.equals("bye")) {
                return execute(Command.EXIT);
            } else if (input.length() >= 4 && input.startsWith("done")) {
                return execute(Command.DONE, input);
            } else if (input.length() >= 6 && input.startsWith("delete")) {
                return execute(Command.DELETE, input);
            } else if (input.length() >= 4 && input.startsWith("todo")) {
                return execute(Command.TODO, input);
            } else if (input.length() >= 8 && input.startsWith("deadline")) {
                return execute(Command.DEADLINE, input);
            } else if (input.length() >= 5 && input.startsWith("event")) {
                return execute(Command.EVENT, input);
            } else {
                throw new InvalidInputException();
            }
        } catch (DukeException | IOException e) {
            throw e;
        }
    }

    /**
     * Main logic for the chat bot
     * @param args User input
     */
    public static void main(String[] args) {

        try {
            Duke duke = new Duke();
            System.out.println(duke.getOutput());
            Scanner sc = new Scanner(System.in);

            while(!duke.getExited()) {
                try {
                    String msg = sc.nextLine();
                    duke = duke.processCommand(msg);
                    System.out.println(duke.getOutput());
                } catch (DukeException e) {
                    System.out.println(e.toString());
                } catch (IOException e) {
                    System.out.println("File not found.");
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
