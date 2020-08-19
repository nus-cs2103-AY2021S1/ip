import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Duke is a personal chatbot with the following functionalities:
 * (i) Adding and removing of Tasks to a list
 * (ii) Checking Tasks as completed
 * (iii) Viewing current task list
 *
 * @author Andy Wu
 */

public class Duke {

    /**
     * The implementation and main driver of the chatbot.
     */

    /** The name of the bot */
    private final String name;

    /** Flag which functions as a switch for the program. */
    private boolean isRunning;

    /** List which stores the Tasks. */
    private final List<Task> list;

    /**
     * Constructor for initializing the bot.
     * Creates a new empty list and sends a greetings message.
     * @param name the string name of the bot.
     */
    public Duke(String name) {
        this.name = name;
        this.isRunning = true;
        this.list = new ArrayList<>();
        sendMessage("Hello! " + this.name + " lives to serve :)");
    }

    /**
     *  Flags the bot as no longer running and sends an exit message.
     */
    public void exit() {
        isRunning = false;
        sendMessage("Bye :(");
    }

    /**
     * Formats all messages with the following:
     * 1) Enclose messages with two lines
     * 2) Indents messages
     * and prints the formatted output.
     * @param message the raw string to be formatted.
     */
    public void sendMessage(String message) {
        // Pads all messages with 4-spaces indentation
        String line = "    ________________________________________________________";
        StringBuilder sb = new StringBuilder(message);
        int offset = 0;
        while (true) {
            int nextIndex = sb.indexOf("\n", offset);
            if (nextIndex != -1) {
                sb.insert(nextIndex + 1, "    ");
                offset = nextIndex + 5;
            } else {
                break;
            }
        }
        System.out.println(line + "\n    " + sb + "\n" + line);
    }

    /**
     * Private method to generate the message for informing number of Tasks in list.
     * @return the String message.
     */
    private String getNumberOfTasks() {
        int size = list.size();
        String task = size == 1 ? "task" : "tasks";
        String num = size == 0 ? "no" : String.valueOf(size);
        return "Now you have " + num + " " + task + " in the list!";
    }

    /**
     * Creates a Task and adds to the list. Tasks have the following subtypes:
     * 1) todo - a task with a description
     * 2) event - a task with a description and occurrence time
     * 3) deadline - a task with a deadline time
     * Task creation fails if the user input is of an incorrect format.
     * @param type the type of task as extracted from user input.
     * @param description the description of task as extracted from user input.
     * @throws DukeException
     */
    public void addToList(String type, String description) throws DukeException {
        Task task;
        String[] split;
        String desc;
        description = description.trim();
        if (description.isEmpty()) {
            throw new DukeException("Description cannot be empty!");
        }
        switch (type) {
            case "todo":
                task = new Todo(description);
                break;
            case "deadline":
                try {
                    split = description.split(" /by ");
                    desc = split[0];
                    String by = split[1];
                    task = new Deadline(desc, by);
                    break;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Invalid " + type + " description!");
                }
            case "event":
                try {
                    split = description.split(" /at ");
                    desc = split[0];
                    String at = split[1];
                    task = new Event(desc, at);
                    break;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Invalid " + type + " description!");
                }
            default:
                task = new Task(description);
                break;
        }
        list.add(task);
        String msg = "Okay I've added:\n    " + task + "\n";
        msg += getNumberOfTasks();
        sendMessage(msg);
    }

    /**
     * Marks the Task of the given task number as completed.
     * @param si the task number as extracted from user input.
     * @throws DukeException
     */
    public void markTaskDone(String si) throws DukeException {
        try {
            int i = Integer.parseInt(si);
            int index = i - 1;
            Task t = list.get(index);
            if (t.getDone()) {
                throw new DukeException("That task is already done!");
            } else {
                t.markAsDone();
                sendMessage("Okay I've marked this task as done:\n    " + t);
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry, I don't know what that means :(");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    /**
     * Removes the Task of the given task number from the list.
     * @param si the task number as extracted from user input.
     * @throws DukeException
     */
    public void deleteTask(String si) throws DukeException {
        try {
            int i = Integer.parseInt(si);
            int index = i - 1;
            Task task = list.get(index);
            list.remove(task);
            String msg = "Okay I've deleted:\n    " + task + "\n";
            msg += getNumberOfTasks();
            sendMessage(msg);
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry, I don't know what that means :(");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    /**
     * Sends the user a formatted message of the tasks in the list.
     */
    public void displayList() {
        if (list.isEmpty()) {
            sendMessage("Your list is empty!");
        } else {
            int num = 1;
            StringBuilder msg = new StringBuilder((list.size() > 1)
                    ? "Here are the tasks in your list:\n"
                    : "Here's your one and only task:\n");
            for (Task task: list) {
                msg.append(String.format("%d. %s", num, task));
                if (num < list.size()) {
                    msg.append("\n");
                }
                num++;
            }
            sendMessage(msg.toString());
        }
    }

    /**
     * The main algorithm of the bot which runs indefinitely as long as
     * the running flag is true. The bot takes in user input, processes
     * it and detects command/keywords.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        while (isRunning) {
            String input = sc.nextLine();
            String[] inputSplit = input.split(" ");
            String command = inputSplit[0];

            switch(command.toLowerCase()) {
                case "bye":
                    exit();
                    break;
                case "list":
                    displayList();
                    break;
                case "done":
                    try {
                        String si = inputSplit[1];
                        markTaskDone(si);
                        break;
                    } catch (DukeException e) {
                        sendMessage(e.getMessage());
                        break;
                    }
                case "todo":
                case "deadline":
                case "event":
                    try {
                        String[] descArr = Arrays.copyOfRange(inputSplit,
                                1, inputSplit.length);
                        String desc = String.join(" ", descArr);
                        addToList(command, desc);
                        break;
                    } catch (DukeException e) {
                        sendMessage(e.getMessage());
                        break;
                    }
                case "delete":
                    try {
                        String si = inputSplit[1];
                        deleteTask(si);
                        break;
                    } catch (DukeException e) {
                        sendMessage(e.getMessage());
                        break;
                    }
                default:
                    sendMessage(
                            new DukeException("Sorry, I don't know what that means :(")
                            .getMessage());
                    break;
            }
        }
        sc.close();
    }

    /**
     * Prints a display of the bot's logo, level, and name,
     * before instantiating and running the bot.
     * @param args optional and will be treated as the first user input.
     */
    public static void main(String[] args) {
        String lvl = "6";
        String logo = "                                                 _     _\n"
                + " _______  _______  _______  _______  __   __    (c).-.(c)\n"
                + "|       ||       ||  _    ||  _    ||  | |  |    / ._. \\ \n"
                + "|_     _||    ___|| |_|   || |_|   ||  |_|  |  __\\( Y )/__\n"
                + "  |   |  |   |___ |       ||       ||       | (_.-/'-'\\-._)\n"
                + "  |   |  |    ___||  _   | |  _   | |_     _|    || " + lvl + " || \n"
                + "  |   |  |   |___ | |_|   || |_|   |  |   |    _.' `-' '._\n"
                + "  |___|  |_______||_______||_______|  |___|   (.-./`-'\\.-.)\n"
                + "                                               `-'     `-'\n"
                + "Level: " + lvl;
        System.out.println(logo);

        Duke duke = new Duke("Tebby");
        duke.run();
    }
}