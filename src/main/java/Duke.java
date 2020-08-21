import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        Path filePath = Paths.get("data","duke.txt");
        
        Storage storage;
        
        try {
            storage = Storage.loadStorage(filePath);
            
            FileReader reader = new FileReader(filePath.toString());
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                
                String[] entry = line.split(" \\| ");
                
                if (entry.length == 3) {
                    String taskType = entry[0];
                    if (!entry[1].toUpperCase().equals("TRUE") && !entry[1].toUpperCase().equals("FALSE")) {
                        throw new DukeException("One or more task statuses are not stored correctly");
                    }
                    boolean taskIsDone = Boolean.parseBoolean(entry[1]);
                    String taskArgument = entry[2];
                    
                    Task newTask;

                    switch (taskType) {
                    case "TODO":
                        newTask = ToDo.createNewToDo(taskArgument);
                        break;
                    case "EVENT":
                        newTask = Event.createNewEvent(taskArgument);
                        break;
                    case "DEADLINE":
                        newTask = Deadline.createNewDeadline(taskArgument);
                        break;
                    default:
                        throw new DukeException("One or more task types are not stored correctly");
                    }
                    
                    if (taskIsDone) {
                        newTask.competeTask();
                    }
                    
                    tasks.add(newTask);
                    
                } else {
                    throw new DukeException("One or more entries have an invalid number of arguments");
                }
            }

            reader.close();
            
        } catch (IOException e) {
            printToConsole("File System Error");
            return;
        } catch (DukeException e) {
            printToConsole("Stored file format is invalid\n" + e.getMessage());
            return;
        }
        
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
                case DONE:
                    printToConsole(markTaskAsDone(tasks, Integer.parseInt(inputList[1])));
                    break;
                case DELETE:
                    printToConsole(deleteTask(tasks, Integer.parseInt(inputList[1])));
                    break;
                case TODO:
                    ToDo newTodo = ToDo.createNewToDo(argument);
                    storage.writeLineToStorage(newTodo.generateStorageString());
                    printToConsole(addTaskToList(tasks, newTodo));
                    break;
                case EVENT:
                    Event newEvent = Event.createNewEvent(argument);
                    storage.writeLineToStorage(newEvent.generateStorageString());
                    printToConsole(addTaskToList(tasks, newEvent));
                    break;
                case DEADLINE:
                    Deadline newDeadline = Deadline.createNewDeadline(argument);
                    storage.writeLineToStorage(newDeadline.generateStorageString());
                    printToConsole(addTaskToList(tasks, newDeadline));
                    break;
                case INVALID:
                    throw new DukeException("Invalid Command.");
                }
            } catch (DukeException e) {
                printToConsole(e.getMessage());
            } catch (IOException e) {
                printToConsole("Error: Task could not be saved.");
            }
        }

        sc.close();
    }
}