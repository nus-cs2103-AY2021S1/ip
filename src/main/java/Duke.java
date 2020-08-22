import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final List<Task> tasks = new ArrayList<>();

    private static void initialize() {
        File dir = new File("./data");
        dir.mkdir();
        File file = new File("./data/duke.txt");
        try {
            if (!file.createNewFile()) {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String task = sc.nextLine();
                    switch (task.charAt(1)) {
                    case 'T':
                        Todo todo = new Todo(task.substring(5));
                        tasks.add(todo);
                        break;
                    case 'D':
                        String[] splitDeadline = task.split(" \\(by: ", 2);
                        Deadline deadline = new Deadline(splitDeadline[0].substring(5),
                                splitDeadline[1].substring(0, splitDeadline[1].lastIndexOf(')')));
                        tasks.add(deadline);
                        break;
                    case 'E':
                        String[] splitEvent = task.split(" \\(at: ", 2);
                        Event event = new Event(splitEvent[0].substring(5),
                                splitEvent[1].substring(0, splitEvent[1].lastIndexOf(')')));
                        tasks.add(event);
                        break;
                    default:
                        break;
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveTasks() {
        try {
            FileWriter writer = new FileWriter("./data/duke.txt");
            for (Task task : tasks) {
                writer.write(task + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printResponse("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static void printResponse(String response) {
        System.out.println("____________________________________________________________");
        System.out.println(response);
        System.out.println("____________________________________________________________");
    }

    private static void printAddTask(Task task) {
        printResponse("Got it. I've added this task: \n\t" + task + "\n" + getNumberOfTasks());
    }

    private static void printDeleteTask(Task task) {
        printResponse("Noted. I've removed this task: \n\t" + task + "\n" + getNumberOfTasks());
    }

    private static void printDoneTask(Task task) {
        printResponse("Nice! I've marked this task as done:\n\t" + task);
    }

    private static void printList() {
        String numberedList = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            numberedList += "\n\t" + (i + 1) + "." + tasks.get(i);
        }
        printResponse(numberedList);
    }

    private static String getNumberOfTasks() {
        int numberOfTasks = tasks.size();
        if (numberOfTasks == 1) {
            return String.format("Now you have %d task in the list.", numberOfTasks);
        } else {
            return String.format("Now you have %d tasks in the list.", numberOfTasks);
        }
    }

    private static Command interpretCommand(String string) throws DukeException {
        try {
            return Command.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void handleUserInputs() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine().trim();
            String[] splitInput = userInput.split(" ", 2);
            try {
                Command command = interpretCommand(splitInput[0]);
                switch (command) {
                case LIST:
                    printList();
                    break;
                case BYE:
                    saveTasks();
                    printResponse("Bye. Hope to see you again soon!");
                    return;
                case DONE:
                    try {
                        int taskNumber = Integer.parseInt(splitInput[1]);
                        tasks.get(taskNumber - 1).markAsDone();
                        printDoneTask(tasks.get(taskNumber - 1));
                    } catch (NullPointerException | NumberFormatException
                            | IndexOutOfBoundsException ex) {
                        throw new DukeException("☹ OOPS!!! The task number is not valid.");
                    }
                    break;
                case DELETE:
                    try {
                        int taskNumber = Integer.parseInt(splitInput[1]);
                        Task removedTask = tasks.remove(taskNumber - 1);
                        printDeleteTask(removedTask);
                    } catch (NullPointerException | NumberFormatException
                            | IndexOutOfBoundsException ex) {
                        throw new DukeException("☹ OOPS!!! The task number is not valid.");
                    }
                    break;
                case TODO:
                    try {
                        tasks.add(new Todo(splitInput[1]));
                    } catch (IndexOutOfBoundsException ex) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    printAddTask(tasks.get(tasks.size() - 1));
                    break;
                case DEADLINE:
                    try {
                        String[] splitDescription = splitInput[1].split(" /by ", 2);
                        tasks.add(new Deadline(splitDescription[0], splitDescription[1]));
                    } catch (IndexOutOfBoundsException ex) {
                        throw new DukeException("☹ OOPS!!! The description or date of a deadline cannot be empty.");
                    }
                    printAddTask(tasks.get(tasks.size() - 1));
                    break;
                case EVENT:
                    try {
                        String[] splitDescription = splitInput[1].split(" /at ", 2);
                        tasks.add(new Event(splitDescription[0], splitDescription[1]));
                    } catch (IndexOutOfBoundsException ex) {
                        throw new DukeException("☹ OOPS!!! The description or date of an event cannot be empty.");
                    }
                    printAddTask(tasks.get(tasks.size() - 1));
                    break;
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        initialize();
        greet();
        handleUserInputs();
    }
}