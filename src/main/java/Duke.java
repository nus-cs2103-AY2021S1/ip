import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

/**
 * Duke is a personal chatbot with the following functionalities:
 * (i) Adding and removing of Tasks to a list
 * (ii) Checking Tasks as completed
 * (iii) Viewing current task list
 *
 * Duke stores the task list in a txt file which is first created
 * when the user does not have such a file.
 * @author Andy Wu
 */

public class Duke {

    /** Flag which acts as a switch for the program. */
    private boolean isRunning;

    /** List which stores the Tasks. */
    private final List<Task> list;

    /** File path to the list */
    private final String FILE_PATH = System.getProperty("user.dir") + "/data/duke.txt";

    /**
     * Constructor for initializing the bot.
     * Creates a new empty list and sends a greetings message.
     */
    public Duke() {
        this.isRunning = true;
        this.list = new ArrayList<>();
        sendMessage("Hello! Tebby lives to serve :)");
    }

    /**
     *  Flags the bot as no longer running and sends an exit message.
     */
    public void exit() {
        isRunning = false;
        sendMessage("Bye! Tebby logging off...");
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
    private String getTaskReportMessage() {
        int size = list.size();
        String task = size == 1 ? "task" : "tasks";
        String num = size == 0 ? "no" : String.valueOf(size);
        return "Now you have " + num + " " + task + " in the list!";
    }

    /**
     * Creates a Task and adds to the list. Tasks have the following subtypes:
     *     1) todo - a task with a description
     *     2) event - a task with a description and occurrence time
     *     3) deadline - a task with a description and deadline time
     * Task creation fails if the user input is of an incorrect format.
     * @param type the type of task as extracted from user input.
     * @param description the description of task as extracted from user input.
     * @throws DukeException when the Task cannot be created due to input errors.
     */
    public void addToList(String type, String description) throws DukeException {
        try {
            Task task = createTask(type, description);

            list.add(task);
            updateFile(list);

            String msg = "Okay I've added:\n    " + task + "\n";
            msg += getTaskReportMessage();
            sendMessage(msg);
        } catch (DukeException de) {
            throw de;
        }
    }

    /**
     * Factory method to create valid Task objects.
     * @param type the subtype of Task
     * @param description the description of the task
     * @return new Task object
     * @throws DukeException when the Task cannot be created due to input errors.
     */
    private Task createTask(String type, String description) throws DukeException {
        Task task = new Task("", "");
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
        }
        return task;
    }

    /**
     * Marks the Task of the given task number as completed.
     * @param si the task number as extracted from user input.
     * @throws DukeException when the input task number is invalid.
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
                updateFile(list);

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
     * @throws DukeException when the input task number is invalid.
     */
    public void deleteTask(String si) throws DukeException {
        try {
            int i = Integer.parseInt(si);
            int index = i - 1;
            Task task = list.get(index);

            list.remove(task);
            updateFile(list);

            String msg = "Okay I've deleted:\n    " + task + "\n";
            msg += getTaskReportMessage();
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
     * Formats the raw input of user.
     * @param input the input of the user.
     * @return the formatted data structure.
     */
    private String[] inputFormat(String input) {
        return input.split(" ");
    }

    /**
     * Reads and parses the file duke.txt to create the tasks
     * to be added to the task list. The values of each task
     * are comma-separated in the format:
     *
     *     Type,Description,Additional Description,isDone
     *
     * where the additional description applies to tasks with
     * time such as deadline or event.
     * Example:
     *     E,dinner,7pm,0
     *     ==> [E][x] dinner (at: 7pm)
     *
     * If the file path does not exist, the program will attempt
     * to create the required directories and the duke.txt file.
     */
    public void loadFromFile() {
        File f = new File(FILE_PATH);
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String[] t = sc.nextLine().split(",");

                switch (t[0]) {
                case "T":
                    list.add(new Todo(t[2], t[1].equals("1")));
                    break;
                case "D":
                    try {
                        list.add(new Deadline(t[2], t[3], t[1].equals("1")));
                    } catch (DukeException e) {
                        sendMessage(e.getMessage());
                    }
                    break;
                case "E":
                    try {
                        list.add(new Event(t[2], t[3], t[1].equals("1")));
                    } catch (DukeException e) {
                        sendMessage(e.getMessage());
                    }
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException fe) {
            try {
                File newF = new File(System.getProperty("user.dir") + "/data");
                if (!newF.exists()) {
                    newF.mkdirs();
                }
                f.createNewFile();
                String msg = "Your new list file has been created in\n";
                msg += "    " + FILE_PATH + "\n";
                msg += "Don't worry, things has been set up properly :)";
                sendMessage(msg);
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }
        }
    }

    public void updateFile(List<Task> list) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task t: list) {
                String toWrite = String.format("%s,%s", t.getType(), (t.getDone() ? "1" : "0"));
                String desc = t.getDescription();
                if ("DE".contains(t.getType())) {
                    String[] descSplit = desc.split(" / ");
                    toWrite += "," + descSplit[descSplit.length - 2];
                    toWrite += "," + descSplit[descSplit.length - 1];
                } else {
                    toWrite += "," + desc;
                }
                fw.write(toWrite + "\n");
            }
            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
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
            String[] inputFormatted = inputFormat(input);
            String command = inputFormatted[0];

            switch (command.toLowerCase()) {
            case "bye":
                exit();
                break;
            case "list":
                displayList();
                break;
            case "done":
                try {
                    String si = inputFormatted[1];
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
                    String[] descArr = Arrays.copyOfRange(inputFormatted,
                            1, inputFormatted.length);
                    String desc = String.join(" ", descArr);
                    addToList(command, desc);
                    break;
                } catch (DukeException e) {
                    sendMessage(e.getMessage());
                    break;
                }
            case "delete":
                try {
                    String si = inputFormatted[1];
                    deleteTask(si);
                    break;
                } catch (DukeException e) {
                    sendMessage(e.getMessage());
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    sendMessage(
                            new DukeException("Give me a task number to delete!")
                                    .getMessage());
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
        String lvl = "7";
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

        Duke duke = new Duke();
        duke.loadFromFile();
        duke.run();
    }
}