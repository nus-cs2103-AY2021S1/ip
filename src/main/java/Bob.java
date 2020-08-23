// Package dependencies
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Represents the task-managing ChatBot.
 * @author Lim Zi Yang
 */
public class Bob {
    private final Storage storage;
    private final String output;
    private final ArrayList<Task> list;
    private final boolean hasExited;

    /**
     * Introduction message
     */
    private static final String INTRO =
            "/*----------------- Welcome to BOB -----------------*/\n"
                    + "                    █▀▀▄ █▀▀█ █▀▀▄\n"
                    + "                    █▀▀▄ █  █ █▀▀▄\n"
                    + "                    ▀▀▀  ▀▀▀▀ ▀▀▀\n"
                    + "            Also known as BERY ORDINARY BOT\n"
                    + "Hi, my name is BOB.\n"
                    + "What can I do for you?";

    /**
     * Exit message
     */
    private static final String OUTRO = "See you soon, maybe?";

    /**
     * Divider to be display on UI
     */
    private static final String DIVIDER = "========================================================\n";

    /**
     * Default file path of saved task list
     */
    // If testing using runtest.bat, use pathname "../data/bob.txt", else use "/data/bob.txt"
    public static final String FILEPATH = System.getProperty("user.dir").endsWith("text-ui-test")
                                            ? "../data/bob.txt"
                                            : "data/bob.txt";

    /**
     * Initializes an active Bob.
     */
    private Bob() throws IOException {
        ArrayList<Task> tempList;

        this.storage = new Storage(FILEPATH);
        this.output = INTRO;
        // Checks if file exists; If exist, converts file into list, else initialize a new list
        try {
            tempList = storage.getList();
        } catch (FileNotFoundException e) {
            System.out.println("I will initialize a new list for you.");
            tempList = new ArrayList<>();
        }
        this.list = tempList;
        this.hasExited = false;
    }

    /**
     * Creates an active Bob.
     *
     * @param output Current message to print to user.
     * @param list   List of tasks.
     */
    private Bob(String output, ArrayList<Task> list) throws IOException {
        this.storage = new Storage(FILEPATH);
        this.output = output;
        this.list = list;
        this.hasExited = false;
    }

    /**
     * Creates a Bob.
     *
     * @param output    Current message to print to user.
     * @param list      List of tasks.
     * @param hasExited Whether Bob has exited.
     */
    private Bob(String output, ArrayList<Task> list, boolean hasExited) throws IOException {
        this.storage = new Storage(FILEPATH);
        this.output = output;
        this.list = list;
        this.hasExited = hasExited;
    }

    /**
     * Updates saved file with updated list of tasks.
     *
     * @throws IOException If error occurs while updating file.
     */
    private void updateData() throws IOException {
        storage.updateFile(this.list);
    }

    /**
     * Adds a task to the list.
     *
     * @param description Description of the task.
     * @return String message regarding adding of task.
     */
    private String addTodo(String description) throws IOException {
        Task task = new Task(description);
        this.list.add(task);
        updateData();
        return returnAddMessage(task);
    }

    /**
     * Adds a deadline to the list.
     *
     * @param description Description of the deadline.
     * @param timeAndDate Time and date of the deadline.
     * @return String message regarding adding of deadline.
     */
    private String addDeadline(String description, String timeAndDate) throws IOException {
        Deadline deadline = new Deadline(description, timeAndDate);
        this.list.add(deadline);
        updateData();
        return returnAddMessage(deadline);
    }

    /**
     * Adds an event to the list.
     *
     * @param description Description of the event.
     * @param time        Time of the event.
     * @return String message regarding adding of event.
     */
    private String addEvent(String description, String time) throws IOException {
        Event event = new Event(description, time);
        this.list.add(event);
        updateData();
        return returnAddMessage(event);
    }

    /**
     * Returns message after adding task.
     *
     * @param task Task that was added to list.
     * @return String message.
     */
    private String returnAddMessage(Task task) {
        return "Yes boss, I have added this task to your list:\n" + "  " + task + "\n"
                + "Currently you have " + this.list.size() + " tasks in your list.";
    }

    /**
     * Converts the list as a readable String.
     *
     * @return String message regarding tasks in list.
     */
    private String convertList() {
        String output = "";
        int taskCompleted = 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).checkIsDone()) {
                taskCompleted++;
            }
            int taskNumber = i + 1;
            output += taskNumber + ". " + list.get(i) + "\n";
        }

        return list.size() == 0
                ? "You currently have no tasks."
                : taskCompleted == list.size()
                ? "Wow congrats, you finished all your tasks.\n" + output
                : "You have " + (list.size() - taskCompleted) + " unfinished tasks.\n" + output;
    }

    /**
     * Marks task in a list as done.
     *
     * @param taskNum Task number.
     * @return String message regarding marking of task.
     * @throws BobListIndexOutOfBoundsException If taskNum is > list.size() or <= 0.
     */
    private String markTaskDone(int taskNum) throws BobListIndexOutOfBoundsException, IOException {
        if (taskNum > list.size() || taskNum <= 0) {
            throw new BobListIndexOutOfBoundsException(list.size(), taskNum, "mark");
        }
        int index = taskNum - 1;
        Task task = list.get(index).markDone();
        list.set(index, task);
        updateData();
        return "I have marked the task as done, good job.\n" + task + "\n";
    }

    /**
     * Deletes task from the list.
     *
     * @param taskNum Task number.
     * @return String message regarding deleting of task.
     * @throws BobListIndexOutOfBoundsException If taskNum is > list.size() or <= 0.
     */
    private String deleteTask(int taskNum) throws BobListIndexOutOfBoundsException, IOException {
        if (taskNum > list.size() || taskNum <= 0) {
            throw new BobListIndexOutOfBoundsException(list.size(), taskNum, "delete");
        }
        int index = taskNum - 1;
        Task task = list.get(index);
        list.remove(index);
        updateData();
        return "I have deleted the task.\n" + task + "\n";
    }

    /**
     * Executes Bob's next action based on command.
     *
     * @param command User's command.
     * @return Updated Bob with the next output.
     */
    private Bob execute(Command command) throws IOException {
        switch (command) {
            case EXIT:
                return new Bob(OUTRO, this.list, true);
            case LIST:
                return new Bob(convertList(), this.list);
            default:
                return this;
        }
    }

    /**
     * Executes Bob's next action based on command and other user input.
     *
     * @param command User's command.
     * @param input   User's input other than command.
     * @return Updated Bob with the next output.
     * @throws BobListIndexOutOfBoundsException If task number input > number of task in list or <= 0
     *                                          for DONE and DELETE commands.
     * @throws BobEmptyDateException            If no date is provided for DEADLINE and EVENT commands.
     * @throws BobInvalidNumberException        If task number input is not a valid integer for LIST and DELETE commands.
     */
    private Bob execute(Command command, String input) throws BobListIndexOutOfBoundsException,
            BobEmptyDateException, BobInvalidNumberException, IOException {
        // for each case, treats return statement as break
        switch (command) {

            // If the command given by user is todo
            case TODO:
                String outputTodo = addTodo(input.substring(5));
                // Storage is updated as well since storage is reinitialized after file is overwritten.
                return new Bob(outputTodo, this.list);

            // If the command given by user is deadline, handles empty date exception
            case DEADLINE:
                String[] split1 = input.split("/");
                if (split1.length == 1) {
                    throw new BobEmptyDateException();
                }
                String description1 = split1[0].substring(9);
                String by1 = split1[1];
                String outputDeadline = addDeadline(description1, by1);
                // Storage is updated as well since storage is reinitialized after file is overwritten.
                return new Bob(outputDeadline, this.list);

            // If the command given by user is event, handles empty date exception
            case EVENT:
                String[] split2 = input.split("/");
                if (split2.length == 1) {
                    throw new BobEmptyDateException();
                }
                String description2 = split2[0].substring(6);
                String by2 = split2[1];
                String outputEvent = addEvent(description2, by2);
                // Storage is updated as well since storage is reinitialized after file is overwritten.
                return new Bob(outputEvent, this.list);

            // If the command given by user is done, handles invalid number and number out of range exceptions
            case DONE:
                try {
                    int taskNumber = Integer.parseInt(input.substring(4).replaceAll("\\s+", ""));
                    String out = markTaskDone(taskNumber);
                    // Storage is updated as well since storage is reinitialized after file is overwritten.
                    return new Bob(out, this.list);
                } catch (BobListIndexOutOfBoundsException e) {
                    throw e;
                } catch (NumberFormatException e) {
                    throw new BobInvalidNumberException();
                }

                // If the command given by user is delete, handles invalid number and number out of range exceptions
            case DELETE:
                try {
                    int taskNumber = Integer.parseInt(input.substring(6).replaceAll("\\s+", ""));
                    String out = deleteTask(taskNumber);
                    // Storage is updated as well since storage is reinitialized after file is overwritten.
                    return new Bob(out, this.list);
                } catch (BobListIndexOutOfBoundsException e) {
                    throw e;
                } catch (NumberFormatException e) {
                    throw new BobInvalidNumberException();
                }

            default:
                return this;
        }
    }

    /**
     * Bob's current output getter.
     *
     * @return String message to be printed to the user.
     */
    public String getOutput() {
        return DIVIDER + this.output + "\n" + DIVIDER;
    }

    /**
     * Checks if Bob has exited.
     *
     * @return Boolean value indicating whether Bob has exited.
     */
    public boolean checkHasExited() {
        return this.hasExited;
    }

    /**
     * Initializes Bob.
     *
     * @return Bob with INTRODUCTION as output.
     */
    public static Bob initializeBob() throws IOException {
        return new Bob();
    }


    // Handles user input which decides which command Bob executes, handles all exceptions

    /**
     * Handles user input which decides which command Bob executes.
     *
     * @param input User input.
     * @return Updated Bob.
     * @throws BobInvalidCommandException       If user input is not recognised.
     * @throws BobEmptyTaskException            If no task is specified after todo, deadline or event.
     * @throws BobListIndexOutOfBoundsException If BobListIndexOutOfBoundsException is caught after execute.
     * @throws BobEmptyDateException            If BobEmptyDateException is caught after execute.
     * @throws BobInvalidNumberException        If BobInvalidNumberException is caught after execute.
     */
    public Bob nextCommand(String input) throws BobInvalidCommandException, BobEmptyTaskException,
            BobListIndexOutOfBoundsException, BobEmptyDateException, BobInvalidNumberException, IOException {
        try {
            if (input.equals("bye")) {
                return execute(Command.EXIT);
            } else if (input.equals("list")) {
                return execute(Command.LIST);
            } else if (input.length() >= 4 && input.startsWith("done")) {
                return execute(Command.DONE, input);
            } else if (input.length() >= 6 && input.startsWith("delete")) {
                return execute(Command.DELETE, input);
            } else if (input.length() >= 4 && input.startsWith("todo")) {
                if (input.substring(4).replaceAll("\\s+", "").length() == 0) {
                    throw new BobEmptyTaskException();
                }
                return execute(Command.TODO, input);
            } else if (input.length() >= 5 && input.startsWith("event")) {
                if (input.substring(5).replaceAll("\\s+", "").length() == 0) {
                    throw new BobEmptyTaskException();
                }
                return execute(Command.EVENT, input);
            } else if (input.length() >= 8 && input.startsWith("deadline")) {
                if (input.substring(8).replaceAll("\\s+", "").length() == 0) {
                    throw new BobEmptyTaskException();
                }
                return execute(Command.DEADLINE, input);
                // If user's command is invalid/not recognisable by Bob
            } else {
                throw new BobInvalidCommandException();
            }
        } catch (BobListIndexOutOfBoundsException | BobEmptyDateException | BobInvalidNumberException | IOException e) {
            throw e;
        }

    }


    /**
     * The program initializes Bob and reads user inputs for Bob.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Initializing stage
        try {
            Bob chatbot = initializeBob();
            // Output for greeting
            System.out.println(chatbot.getOutput());

            Scanner sc = new Scanner(System.in);

            while (!chatbot.checkHasExited()) {
                try {
                    String userInput = sc.nextLine();
                    chatbot = chatbot.nextCommand(userInput);
                    System.out.println(chatbot.getOutput());
                } catch (BobInvalidCommandException e) {
                    System.out.println(e.toString());
                } catch (BobEmptyTaskException e) {
                    System.out.println(e.toString());
                } catch (BobListIndexOutOfBoundsException e) {
                    System.out.println(e.toString());
                } catch (BobEmptyDateException e) {
                    System.out.println(e.toString());
                } catch (BobInvalidNumberException e) {
                    System.out.println(e.toString());
                } catch (IOException e) {
                    System.out.println("Sorry, file not found.");
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
