import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;

public class Sparrow {
    private final static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        initialize();
        greet();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (handle(command)) {
            command = sc.nextLine();
        }
        sc.close();
    }

    public static void initialize() {
        // check if directory and file exist
        File f = new File("data/Sparrow.txt");
        if (f.exists()) {
            try {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String task = sc.nextLine();
                    stringToTask(task);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            File g = new File("data");
            if (!g.exists()) {
                g.mkdirs();
            }
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void stringToTask(String input) {
        String[] inputArr = input.split("\\s+\\|\\s+", 4);
        boolean isTaskDone = Integer.parseInt(inputArr[1]) == 1;
        switch (inputArr[0]) {
        case "T":
            Todo todo = new Todo(inputArr[2]);
            if (isTaskDone) {
                todo.markAsDone();
            }
            taskList.add(todo);
            break;
        case "D":
            Deadline deadline = new Deadline(inputArr[2], inputArr[3]);
            if (isTaskDone) {
                deadline.markAsDone();
            }
            taskList.add(deadline);
            break;
        case "E":
            Event event = new Event(inputArr[2], inputArr[3]);
            if (isTaskDone) {
                event.markAsDone();
            }
            taskList.add(event);
            break;
        default:
            System.out.println("No matching task found, shouldn't end up here");
        }
    }

    public static boolean handle(String commandLine) {
        String[] commandArr = commandLine.trim().split("\\s+", 2);
        String command = commandArr[0];
        try {
            switch (command) {
            case "bye":
                reply("Bye. Hope t' see ye again soon!");
                return false;
            case "list":
                displayList();
                break;
            case "done":
                try {
                    markAsDone(commandArr[1]);
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new MissingTaskNumberException("No task number passed to done command.", e);
                }
            case "todo":
                try {
                    addTask("todo", commandArr[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyTodoDescriptionException("No description provided for todo.", e);
                }
                break;
            case "deadline":
                try {
                    addTask("deadline", commandArr[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyDeadlineDescriptionException("No description provided for deadline.", e);
                }
                break;
            case "event":
                try {
                    addTask("event", commandArr[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyEventDescriptionException("No description provided for event.", e);
                }
                break;
            case "delete":
                try {
                    deleteTask(commandArr[1]);
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new MissingTaskNumberException("No task number passed to delete command.", e);
                }
            default:
                throw new UnknownCommandException(commandArr[0]);
            }
        } catch (MissingTaskNumberException e) {
            System.out.println(standardExceptionMessage() + "️ Please enter a task number after the \"done\"/\"delete\" command.");
        } catch (EmptyTodoDescriptionException e) {
            System.out.println(standardExceptionMessage() + "️ The description of a todo cannot be empty.");
        } catch (EmptyDeadlineDescriptionException e) {
            System.out.println(standardExceptionMessage() + "️ The description of a deadline cannot be empty.");
        } catch (EmptyEventDescriptionException e) {
            System.out.println(standardExceptionMessage() + "️ The description of an event cannot be empty.");
        } catch (UnknownCommandException e) {
            System.out.println(standardExceptionMessage() + "️ What be the meaning of this?");
        }
        return true;
    }

    public static void greet() {
        String welcome = "  _  _ _   ___ _                    \n" +
                " | || (_) |_ _( )_ __               \n" +
                " | __ | |  | ||/| '  \\              \n" +
                " |_||_|_| |___| |_|_|_|             \n" +
                " / __|_ __  __ _ _ _ _ _ _____ __ __\n" +
                " \\__ \\ '_ \\/ _` | '_| '_/ _ \\ V  V /\n" +
                " |___/ .__/\\__,_|_| |_| \\___/\\_/\\_/ \n" +
                "     |_|                            ";
        System.out.println(welcome);
        reply("How can I help ye?");
    }

    public static void reply(String message) {
        System.out.println("    ________________________________________");
        Scanner sc = new Scanner(message);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println("      " + line);
        }
        System.out.println("    ________________________________________");
        sc.close();
    }

    public static void addTask(String type, String details) {
        StringBuilder sb = new StringBuilder("Aye Aye Captain! I've added this task:\n  ");
        try {
            switch (type) {
            case "todo":
                Todo todo = new Todo(details);
                taskList.add(todo);
                sb.append(todo);
                break;
            case "deadline":
                try {
                    String[] taskDetails = details.trim().split("/by");
                    String description = taskDetails[0];

                    Deadline deadline = new Deadline(description, taskDetails[1].trim());
                    taskList.add(deadline);
                    sb.append(deadline);
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidDeadlineByException("/by not passed", e);
                }
            case "event":
                try {
                    String[] taskDetails = details.trim().split("/at");
                    String description = taskDetails[0];

                    Event event = new Event(description, taskDetails[1].trim());
                    taskList.add(event);
                    sb.append(event);
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidEventAtException("/at not passed", e);
                }

            }
            String summary = String.format("\nNow you have %d task(s) in the list.", taskList.size());
            sb.append(summary);
            reply(sb.toString());
        } catch (InvalidDeadlineByException e) {
            System.out.println(standardExceptionMessage() + "️ Please pass a /by to the deadline");
        } catch (InvalidEventAtException e) {
            System.out.println(standardExceptionMessage() + "️ Please pass a /at to the event");
        }
    }

    public static void displayList() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            String temp = String.format("%d. %s\n", i + 1, taskList.get(i));
            sb.append(temp);
        }
        reply(sb.toString());
    }

    public static void markAsDone(String taskNum) {
        try {
            int taskNumber = Integer.parseInt(taskNum);
            if (taskNumber <= taskList.size() && taskNumber > 0) {
                taskList.get(taskNumber - 1).markAsDone();
                reply("Jolly riddance! I've marked this task as done:\n" + taskList.get(taskNumber - 1));
            } else {
                System.out.println("Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task number.");
        }
    }

    public static String standardExceptionMessage() {
        return "ARR!\uD83C\uDFF4\u200D\u2620\uFE0F️ ";
    }

    public static void deleteTask(String taskNum) {
        try {
            int taskNumber = Integer.parseInt(taskNum);
            if (taskNumber <= taskList.size() && taskNumber > 0) {
                Task removedTask = taskList.remove(taskNumber - 1);
                reply("Jolly riddance! I've deleted this task:\n" + "  " + removedTask.toString() + String.format("\nNow you have %d task(s) in the list.", taskList.size()));
            } else {
                System.out.println("Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task number.");
        }
    }
}
