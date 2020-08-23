import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    private static final Path filePath = Paths.get(".", "data", "duke.txt");
    static final List<Task> tasks = new ArrayList<>();

    final static String DIVIDE = "____________________________________________________________\n";
    final static String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?\n";
    final static String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";
    final static String LIST_MESSAGE = "Here are the tasks in your list:\n";
    final static String DONE_MESSAGE = "Nice! I've marked this task as done:\n";
    final static String TASK_ADDED_MESSAGE = "Got it. I've added this task:\n";
    final static String DELETE_MESSAGE = "Noted. I've removed this task:\n";


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        say(WELCOME_MESSAGE);

        String input;
        while(sc.hasNext()){
            if ((input = sc.nextLine()).equals("bye")) {
                say(EXIT_MESSAGE);
                break;
            } else {
                try {
                    handleCommand(input);
                    save();
                } catch (DukeException e) {
                    say(e.getMessage() + "\n");
                }
            }
        }

        sc.close();

    }

    private static void save() throws DukeException {
        try {
            // create directory if directory doesn't exist
            Path parentPath = filePath.getParent();
            Files.createDirectories(parentPath);

            // create file if file doesn't exist
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            String currTasks = formatTasks();
            Files.writeString(filePath, currTasks, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            throw new DukeException("Something went wrong when trying to save your tasks!\n");
        }
    }

    private static String formatTasks() {
        StringBuilder toSave = new StringBuilder();
        for (Task t : Duke.tasks) {
            String out = t.getSaveFormat() + '\n';
            toSave.append(out);
        }
        return toSave.toString();
    }

    public static void say(String msg) {
        System.out.println(DIVIDE + msg + DIVIDE);
    }

    public static void handleCommand(String input) throws DukeException{
        String[] commandDetail = input.split(" ", 2);

        if (commandDetail.length == 0) {
            throw new DukeException("Be sure to follow the exact format of the commands!");
        }

        Commands command;
        try {
            command = Commands.valueOf(commandDetail[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("Sorry! I don't recognize that command!");
        }

        String detail;

        switch (command) {
            case LIST:
                handleList();
                break;

            case DONE:
                if (commandDetail.length < 2) {
                    throw new DukeException("Please key in a task to be marked as done!");
                }
                detail = commandDetail[1];
                handleDone(detail);
                break;

            case DELETE:
                if (commandDetail.length < 2) {
                    throw new DukeException("Please key in a task to be marked as done!");
                }
                detail = commandDetail[1];
                handleDelete(detail);
                break;

            case TODO:
                if (commandDetail.length < 2) {
                    throw new DukeException("Please input a task to add!");
                }
                detail = commandDetail[1];
                handleTodo(detail);
                break;

            case DEADLINE:
                if (commandDetail.length < 2) {
                    throw new DukeException("Please input a task to add!");
                }
                detail = commandDetail[1];
                handleDeadline(detail);
                break;

            case EVENT:
                if (commandDetail.length < 2) {
                    throw new DukeException("Please input a task to add!");
                }
                detail = commandDetail[1];
                handleEvent(detail);
                break;
        }
    }

    public static void handleList() {
        StringBuilder out = new StringBuilder(LIST_MESSAGE);
        for (int i = 0; i < tasks.size(); i++) {
            String nextLine = (i + 1) + "." + tasks.get(i) + "\n";
            out.append(nextLine);
        }
        say(out.toString());
    }

    public static void handleDone(String detail) throws DukeException {
        try {
            int num = Integer.parseInt(detail);
            Task curr = tasks.get(num - 1);
            curr.markAsDone();
            say(DONE_MESSAGE + curr + "\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please key in the number of an existing task to be marked as done!");
        }
    }

    public static void handleDelete(String detail) throws DukeException {
        try {
            int num = Integer.parseInt(detail);
            Task curr = tasks.get(num - 1);
            tasks.remove(num - 1);
            String numTasks = "Now you have " + tasks.size() + " tasks in the list.\n";
            say(DELETE_MESSAGE + curr + "\n" + numTasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please key in the number of an existing task to be removed!");
        }
    }

    public static void handleTodo(String detail) {
        Task task = new Todo(detail);
        tasks.add(task);
        String numTasks = "Now you have " + tasks.size() + " tasks in the list.\n";
        String out = TASK_ADDED_MESSAGE + task +
                "\n" + numTasks;
        say(out);
    }

    public static void handleDeadline(String detail) throws DukeException {
        try {
            String[] split = detail.split(" /by ", 2);
            Task task = new Deadline(split[0], split[1]);
            tasks.add(task);
            String numTasks = "Now you have " + tasks.size() + " tasks in the list.\n";
            String out = TASK_ADDED_MESSAGE + task +
                    "\n" + numTasks;
            say(out);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a deadline to complete the task by or follow the exact command format!");
        }
    }

    public static void handleEvent(String detail) throws DukeException {
        try {
            String[] split = detail.split(" /at ", 2);
            Task task = new Event(split[0], split[1]);
            tasks.add(task);
            String numTasks = "Now you have " + tasks.size() + " tasks in the list.\n";
            String out = TASK_ADDED_MESSAGE + task +
                    "\n" + numTasks;
            say(out);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter the time at which the event will take place or follow the exact command format!");
        }
    }
}
