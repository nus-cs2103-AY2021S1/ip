import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String line = "----------------------";

    // for the list
    private static final List<Task> tasks = new ArrayList<>(); // not a fixed size anymore
    private static boolean dukeOn = true; // flag to indicate duke is ready to receive any query
    private static final DukeStorage storage = new DukeStorage("data/duke.txt");

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        greet(logo);

        // try to open the duke file
        try {
            storage.reloadStorage(tasks);
        } catch (FileNotFoundException ex) {
            System.out.println("Duke data do not exist!");
        } catch (DukeException ex) {
            System.out.println("Its a duke exception!");
        }

        Scanner sc = new Scanner(System.in);
        while (dukeOn) {
            String input = sc.nextLine();
            String[] splittedWords = input.split("\\s", 2); // splits string based on whitespace
            String command = splittedWords[0];
            String afterCommand = null;
            if (splittedWords.length > 1) {
                afterCommand = splittedWords[1];
            }

            try {
                askDuke(command, afterCommand);
            } catch (DukeException ex) {
                format(ex.toString());
            }
        }
        sc.close();
    }

    // main driver function for duke to tackle commands
    private static void askDuke(String command, String afterCommand) throws DukeException {
        Command commandType;
        try {
            commandType = Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException ex) {
            commandType = Command.UNIDENTIFIED;
        }

        // handle all different commands using switch and enum instead; organised the methods
        // to make the code look neater
        switch (commandType) {
            case BYE:
                exit();
                break;
            case LIST:
                displayList();
                break;
            case DONE:
                recordDoneTask(afterCommand);
                break;
            case TODO:
                recordToDoTask(afterCommand);
                break;
            case DEADLINE:
                recordDeadlineTask(afterCommand);
                break;
            case EVENT:
                recordEventTask(afterCommand);
                break;
            case DELETE:
                deleteTask(afterCommand);
                break;
            case UNIDENTIFIED:
                // if a bad command is thrown at Duke
                throw new DukeException("Please key in a command I understand!");
        }
    }

    private static void greet(String logo) {
       format("Hello! I'm\n" + logo + "\n" +
                "What can I do for you?");
    }

    private static void format(String input) {
        System.out.println(line + "\n" + input + "\n" + line);
    }

    private static void exit() {
        dukeOn = false;
        try {
            storage.saveStorage(tasks);
        } catch (IOException ex) {
            System.out.println("Error in saving!");
            ex.printStackTrace();
        }
        format("Bye. Hope to see you again soon!");
    }

    private static void addOnToList(Task task) {
        tasks.add(task);
        format("Got it. I've added this task:\n" + task +  "\n"
            + "Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void displayList() {
        StringBuilder sb = new StringBuilder();
        if (tasks.size() == 0) {
            sb.append("No tasks!");
            format(sb.toString());
            return;
        }
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1 + "." + tasks.get(i) + "\n");
        }
        format(sb.toString());
    }

    private static void recordDoneTask(String afterCommand) throws DukeException {
        // needs a valid number
        int taskNo = Integer.parseInt(afterCommand) - 1;
        if (taskNo >= tasks.size() || taskNo < 0) {
            throw new DukeException("Please enter a valid task no!");
        }
        tasks.get(taskNo).markAsCompleted();
        displayCompletedTask(tasks.get(taskNo));
    }

    private static void displayCompletedTask(Task task) {
        format("Nice! I've marked this task as done:\n" + task);
    }

    private static void recordToDoTask(String afterCommand) throws DukeException {
        // needs an after command
        if (afterCommand == null) {
            throw new DukeException("Please do not leave the todo description empty!");
        }
        addOnToList(new ToDo(afterCommand));
    }

    private static void recordDeadlineTask(String afterCommand) throws DukeException {
        // needs an after command
        if (afterCommand == null) {
            throw new DukeException("Please do not leave the deadline description empty!");
        }
        // first chunk is the deadline details, second chunk is by when
        String[] splittedDeadline = afterCommand.split("/");

        // teach the user the format for the deadline
        if (splittedDeadline.length == 1) {
            throw new DukeException("Format of deadline recording: deadline keyword" +
                    ", deadline instructions, forward slash, by keyword with a colon, specific date/time)"
                    + "\n e.g. deadline return book /by Sunday");
        }

        String details = splittedDeadline[0].trim();
        String by = splittedDeadline[1].split("by", 2)[1];
        addOnToList(new Deadline(details, by));
    }

    private static void recordEventTask(String afterCommand) throws DukeException {
        // needs an after command
        if (afterCommand == null) {
            throw new DukeException("Please do not leave the event description empty!");
        }
        // first chunk is the event details, second chunk is at where
        String[] splittedEvent = afterCommand.split(("/"));

        // teach the user the format for the deadline
        if (splittedEvent.length == 1) {
            throw new DukeException("Format of event recording: event keyword" +
                    ", event instructions, forward slash, at keyword with a colon, start/end time)"
                    + "\n e.g. project meeting /at Mon 2-4pm");
        }

        String details = splittedEvent[0].trim();
        String at = splittedEvent[1].split("at", 2)[1];
        addOnToList(new Event(details, at));
    }

    private static void deleteTask(String afterCommand) throws DukeException {
        // needs a valid number
        int taskNo = Integer.parseInt(afterCommand) - 1;
        if (taskNo >= tasks.size() || taskNo < 0) {
            throw new DukeException("Please enter a valid task no!");
        }
        displayDeletedTask(taskNo);
    }

    private static void displayDeletedTask(int index) {
        format("Noted. I've removed this task:\n" + tasks.get(index) + "\n"
                + "Now you have " + (tasks.size() - 1) + " tasks in the list.");
        tasks.remove(index);
    }
}
