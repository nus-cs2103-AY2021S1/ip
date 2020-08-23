// Package dependencies
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the task-managing ChatBot.
 * @author Lim Zi Yang
 */
public class Bob {
    private final String output;
    private final ArrayList<Task> list;
    private final boolean hasExited;

    /** Introduction message */
    public static final String INTRO =
            "/*----------------- Welcome to BOB -----------------*/\n"
            + "                    █▀▀▄ █▀▀█ █▀▀▄\n"
            + "                    █▀▀▄ █  █ █▀▀▄\n"
            + "                    ▀▀▀  ▀▀▀▀ ▀▀▀\n"
            + "            Also known as BERY ORDINARY BOT\n"
            + "Hi, my name is BOB.\n"
            + "What can I do for you?";

    /** Exit message */
    public static final String OUTRO = "See you soon, maybe?";
    public static final String DIVIDER =  "========================================================\n";

    /**
     * Creates an active Bob.
     * @param output Current message to print to user.
     * @param list List of tasks.
     */
    private Bob (String output, ArrayList<Task> list) {
        this.output = output;
        this.list = list;
        this.hasExited = false;
    }

    /**
     * Creates a chatbot.
     * @param output Current message to print to user.
     * @param list List of tasks.
     * @param hasExited Whether Bob has exited.
     */
    private Bob (String output, ArrayList<Task> list, boolean hasExited) {
        this.output = output;
        this.list = list;
        this.hasExited = hasExited;
    }

    /**
     * Formats date to be parsed.
     * @param date Inputted date.
     * @return Formatted date.
     * @throws BobInvalidDateAndTimeException If the inputted date and time has invalid format.
     */

    private String formatDate(String date) throws BobInvalidDateAndTimeException {
        String[] split = date.split("/");
        // If length of date is not same as "YYYY/MM/DD" and components are not separated by "/"
        if (date.length() != 10 || split.length != 3) {
            throw new BobInvalidDateAndTimeException();
        }
        String formattedDate = split[0] + "-" + split[1] + "-" + split[2];
        return formattedDate;
    }

    /**
     * Formats time to be parsed.
     * @param time Inputted time.
     * @return Formatted time.
     * @throws BobInvalidDateAndTimeException If the inputted date and time has invalid format.
     */
    private String formatTime(String time) throws BobInvalidDateAndTimeException {
        // If length of time is not same as "HHMM"
        if (time.length() != 4) {
            throw new BobInvalidDateAndTimeException();
        }

        return time.substring(0,2) + ":" + time.substring(2);
    }

    /**
     * Returns message after adding task.
     * @param task Task that was added to list.
     * @return String message.
     */
    private String returnAddMessage(Task task) {
        String taskWord = list.size() <= 1
                ? "task"
                : "tasks";
        return "Yes boss, I have added this task to your list:\n" + "  " + task + "\n"
                + "Currently you have " + this.list.size() + " " + taskWord + " in your list.";
    }

    /**
     * Adds a task to the list.
     * @param description Description of the task.
     * @return String message regarding adding of task.
     */
    private String addTodo(String description) {
        Task task = new Task(description);
        this.list.add(task);
        return returnAddMessage(task);
    }

    /**
     * Adds a deadline to the list.
     * @param description Description of the deadline.
     * @param dateAndTime Date and time of the deadline.
     * @return String message regarding adding of deadline.
     * @throws BobInvalidDateAndTimeException If format of date and time is invalid.
     */
    private String addDeadline(String description, String dateAndTime) throws BobInvalidDateAndTimeException {
        // Checks if there is a space between "/by" and "date and time"
            String temp = dateAndTime.startsWith(" ")
                    ? dateAndTime.substring(1)
                    : dateAndTime;
            String[] dateAndTimeSplit = temp.split(" ");

            // If format of date and time is invalid (in this case, not separated by one space)
            if (dateAndTimeSplit.length != 2) {
                throw new BobInvalidDateAndTimeException();
            }

            String date = formatDate(dateAndTimeSplit[0]);
            String time = formatTime(dateAndTimeSplit[1]);
            Deadline deadline = new Deadline(description, date, time);
            this.list.add(deadline);
            return returnAddMessage(deadline);
    }

    /**
     * Adds an event to the list.
     * @param description Description of the event.
     * @param dateAndTime Date and time of the event.
     * @return String message regarding adding of event.
     * @throws BobInvalidDateAndTimeException If format of date and time is invalid.
     */
    private String addEvent(String description, String dateAndTime) throws BobInvalidDateAndTimeException {
        // Checks if there is a space between "/by" and "date and time"
        String temp = dateAndTime.startsWith(" ")
                ? dateAndTime.substring(1)
                : dateAndTime;
        String[] dateAndTimeSplit = temp.split(" ");

        // If format of date and time is invalid (in this case, not separated by one space)
        if (dateAndTimeSplit.length != 2) {
            throw new BobInvalidDateAndTimeException();
        }

        String date = formatDate(dateAndTimeSplit[0]);
        String time = formatTime(dateAndTimeSplit[1]);
        Event event = new Event(description, date, time);
        this.list.add(event);
        return returnAddMessage(event);
    }

    /**
     * Converts the list as a readable String.
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
            output += taskNumber  + ". " + list.get(i) + "\n";
        }

        return  list.size() == 0
                ? "You currently have no tasks."
                : taskCompleted == list.size()
                ? "Wow congrats, you finished all your tasks.\n" + output
                : "You have " + (list.size() - taskCompleted) + " unfinished tasks.\n" + output;
    }

    /**
     * Marks task in a list as done.
     * @param taskNum Task number.
     * @return String message regarding marking of task.
     * @throws BobListIndexOutOfBoundsException If taskNum is > list.size() or <= 0.
     */
    private String markTaskDone(int taskNum) throws BobListIndexOutOfBoundsException {
        if (taskNum > list.size() || taskNum <= 0) {
            throw new BobListIndexOutOfBoundsException(list.size(), taskNum, "mark");
        }
        int index = taskNum - 1;
        Task task = list.get(index).markDone();
        list.set(index, task);
        return "I have marked the task as done, good job.\n" + task + "\n";
    }

    /**
     * Deletes task from the list.
     * @param taskNum Task number.
     * @return String message regarding deleting of task.
     * @throws BobListIndexOutOfBoundsException If taskNum is > list.size() or <= 0.
     */
    private String deleteTask(int taskNum) throws BobListIndexOutOfBoundsException {
        if (taskNum > list.size() || taskNum <= 0) {
            throw new BobListIndexOutOfBoundsException(list.size(), taskNum, "delete");
        }
        int index = taskNum - 1;
        Task task = list.get(index);
        list.remove(index);
        return "I have deleted the task.\n" + task + "\n";
    }

    /**
     * Executes Bob's next action based on command.
     * @param command User's command.
     * @return Updated Bob with the next output.
     */
    private Bob execute(Command command) {
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
     * @param command User's command.
     * @param input User's input other than command.
     * @return Updated Bob with the next output.
     * @throws BobListIndexOutOfBoundsException If task number input > number of task in list or <= 0
     * for DONE and DELETE commands.
     * @throws BobInvalidDateAndTimeException If no/invalid date is provided for DEADLINE and EVENT commands.
     * @throws BobInvalidNumberException If task number input is not a valid integer for LIST and DELETE commands.
     */
    private Bob execute(Command command, String input) throws BobListIndexOutOfBoundsException,
            BobInvalidDateAndTimeException, BobInvalidNumberException  {
        // for each case, treats return statement as break
        switch (command) {

        // If the command given by user is todo
        case TODO:
            String outputTodo = addTodo(input.substring(5));
            return new Bob(outputTodo, this.list);

        // If the command given by user is deadline, handles empty date exception
        case DEADLINE:
            String[] splitD = input.split("/by");
            if (splitD.length == 1) {
                throw new BobInvalidDateAndTimeException();
            }
            String descriptionD = splitD[0].substring(9);
            String dateAndTimeD = splitD[1];
            String outputDeadline = addDeadline(descriptionD, dateAndTimeD);
            return new Bob(outputDeadline, this.list);

        // If the command given by user is event, handles empty date exception
        case EVENT:
            String[] splitE = input.split("/at");
            if (splitE.length == 1) {
                throw new BobInvalidDateAndTimeException();
            }
            String descriptionE = splitE[0].substring(6);
            String dateAndTimeE = splitE[1];
            String outputEvent = addEvent(descriptionE, dateAndTimeE);
            return new Bob(outputEvent, this.list);

        // If the command given by user is done, handles invalid number and number out of range exceptions
        case DONE:
            try {
                int taskNumber = Integer.parseInt(input.substring(4).replaceAll("\\s+", ""));
                String out = markTaskDone(taskNumber);
                return new Bob(out, this.list);
            } catch (BobListIndexOutOfBoundsException e){
                throw e;
            } catch (NumberFormatException e) {
                throw new BobInvalidNumberException();
            }
        // If the command given by user is delete, handles invalid number and number out of range exceptions
        case DELETE:
            try {
                int taskNumber = Integer.parseInt(input.substring(6).replaceAll("\\s+", ""));
                String out = deleteTask(taskNumber);
                return new Bob(out, this.list);
            } catch (BobListIndexOutOfBoundsException e){
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
     * @return String message to be printed to the user.
     */
    public String getOutput() {
        return DIVIDER + this.output + "\n" + DIVIDER;
    }

    /**
     * Checks if Bob has exited.
     * @return Boolean value indicating whether Bob has exited.
     */
    public boolean checkHasExited() {
        return this.hasExited;
    }

    /**
     * Initializes Bob.
     * @return Bob with INTRODUCTION as output.
     */
    public static Bob initializeBob() {
        return new Bob(INTRO, new ArrayList<>());
    }



    // Handles user input which decides which command Bob executes, handles all exceptions

    /**
     * Handles user input which decides which command Bob executes.
     * @param input User input.
     * @return Updated Bob.
     * @throws BobInvalidCommandException If user input is not recognised.
     * @throws BobEmptyTaskException If no task is specified after todo, deadline or event.
     * @throws BobListIndexOutOfBoundsException If BobListIndexOutOfBoundsException is caught after execute.
     * @throws BobInvalidDateAndTimeException If BobInvalidDateAndTimeException is caught after execute.
     * @throws BobInvalidNumberException If BobInvalidNumberException is caught after execute.
     */
    public Bob nextCommand(String input) throws BobInvalidCommandException, BobEmptyTaskException,
            BobListIndexOutOfBoundsException, BobInvalidDateAndTimeException, BobInvalidNumberException {
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
        } catch (BobListIndexOutOfBoundsException | BobInvalidDateAndTimeException | BobInvalidNumberException e) {
            throw e;
        }

    }


    /**
     * The program initializes Bob and reads user inputs for Bob.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Initializing stage
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
            } catch (BobInvalidDateAndTimeException e) {
                System.out.println(e.toString());
            } catch (BobInvalidNumberException e) {
                System.out.println(e.toString());
            }
        }
        sc.close();
    }
}
