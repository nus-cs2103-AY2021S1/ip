import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {

    private static final String DIVIDER = "___________________________________________________________________________";

    private static final String LOGO = "             _        ______   _____    ______   _____  \n" +
            "     /\\     | |      |  ____| |  __ \\  |  ____| |  __ \\ \n" +
            "    /  \\    | |      | |__    | |__) | | |__    | |  | |\n" +
            "   / /\\ \\   | |      |  __|   |  _  /  |  __|   | |  | |\n" +
            "  / ____ \\  | |____  | |      | | \\ \\  | |____  | |__| |\n" +
            " /_/    \\_\\ |______| |_|      |_|  \\_\\ |______| |_____/ \n";

    private static void printToConsole(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }

    private static String convertTaskListToString(List<Task> tasks) {

        if (tasks.isEmpty()) {
            return "You have no tasks!";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i+1));
            sb.append(".");
            sb.append(tasks.get(i));
            sb.append('\n');
        }

        // remove last newline character
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    private static String markTaskAsDone(List<Task> tasks, int taskID) throws DukeException {
        if (taskID < 1 || taskID > tasks.size()) {
            throw new DukeException("Task ID is invalid!");
        }
        Task task = tasks.get(taskID - 1);
        task.competeTask();
        return String.format("Nice! I've marked this task as done.\n%s", task.toString());
    }

    private static String deleteTask(List<Task> tasks, int taskID) throws DukeException {
        if (taskID < 1 || taskID > tasks.size()) {
            throw new DukeException("Task ID is invalid!");
        }
        Task task = tasks.get(taskID - 1);
        tasks.remove(taskID - 1);
        return String.format("Nice! I've marked this task as done.\n%s\nNow you have %d tasks in the list", 
                task.toString(), tasks.size());
    }

    private static String addTaskToList(List<Task> tasks, Task task) {
        tasks.add(task);
        return String.format("Got it. I've added this task: \n%s\nNow you have %d tasks in the list",
                task.toString(), tasks.size());
    }
    
    private static String taskListToDateFilteredString(List<Task> tasks, String dateString) throws DukeException {
        LocalDate date;
        
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new DukeException("DateTime format is invalid.");
        }
        
        List<Task> temp = tasks.stream()
                .filter(task -> task.isOnSameDay(date))
                .collect(Collectors.toList());
        
        return convertTaskListToString(temp);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        System.out.println(LOGO);
        printToConsole("Hi I'm Alfred! How can I help you today?");

        while (sc.hasNextLine()) {
            String[] inputList = sc.nextLine().split(" ", 2);
            String argument = inputList.length > 1 ? inputList[1] : "";
            Command command;
            
            try {
                command = Command.valueOf(inputList[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                command = Command.INVALID;
            }

            try {
                switch (command) {
                case BYE:
                    printToConsole("Goodbye!");
                    return;
                case LIST:
                    printToConsole(convertTaskListToString(tasks));
                    break;
                case DATE:
                    printToConsole(taskListToDateFilteredString(tasks, argument));
                    break;
                case DONE:
                    printToConsole(markTaskAsDone(tasks, Integer.parseInt(inputList[1])));
                    break;
                case DELETE:
                    printToConsole(deleteTask(tasks, Integer.parseInt(inputList[1])));
                    break;
                case TODO:
                    ToDo newTodo = ToDo.createNewToDo(argument);
                    printToConsole(addTaskToList(tasks, newTodo));
                    break;
                case EVENT:
                    Event newEvent = Event.createNewEvent(argument);
                    printToConsole(addTaskToList(tasks, newEvent));
                    break;
                case DEADLINE:
                    Deadline newDeadline = Deadline.createNewDeadline(argument);
                    printToConsole(addTaskToList(tasks, newDeadline));
                    break;
                case INVALID:
                    throw new DukeException("Invalid Command.");
                }
            } catch (DukeException e) {
                printToConsole(e.getMessage());
            }
        }

        sc.close();
    }
}