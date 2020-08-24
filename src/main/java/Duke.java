import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {

    private static final String DIVIDER = "___________________________________________________________________________";

    private static final String LOGO = "             _        ______   _____    ______   _____  \n" +
            "     /\\     | |      |  ____| |  __ \\  |  ____| |  __ \\ \n" +
            "    /  \\    | |      | |__    | |__) | | |__    | |  | |\n" +
            "   / /\\ \\   | |      |  __|   |  _  /  |  __|   | |  | |\n" +
            "  / ____ \\  | |____  | |      | | \\ \\  | |____  | |__| |\n" +
            " /_/    \\_\\ |______| |_|      |_|  \\_\\ |______| |_____/ \n";
    
    private static Storage storage;
    
    private static void printToConsole(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Path filePath = Paths.get("data","duke.txt");
        Storage storage;
        
        try {
            storage = Storage.loadStorage(filePath);
            taskList.loadDataFromStorage(filePath);
        }  catch (IOException e) {
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
                    printToConsole(taskList.convertTaskListToString());
                    break;
                case DATE:
                    printToConsole(taskList.taskListToDateFilteredString(argument));
                    break;
                case DONE:
                    printToConsole(taskList.markTaskAsDone(Integer.parseInt(inputList[1]), storage));
                    break;
                case DELETE:
                    printToConsole(taskList.deleteTask(Integer.parseInt(inputList[1]), storage));
                    break;
                case TODO:
                    ToDo newTodo = ToDo.createNewToDo(argument);
                    storage.writeLineToStorage(newTodo.generateStorageString());
                    printToConsole(taskList.addTaskToList(newTodo));
                    break;
                case EVENT:
                    Event newEvent = Event.createNewEvent(argument);
                    storage.writeLineToStorage(newEvent.generateStorageString());
                    printToConsole(taskList.addTaskToList(newEvent));
                    break;
                case DEADLINE:
                    Deadline newDeadline = Deadline.createNewDeadline(argument);
                    storage.writeLineToStorage(newDeadline.generateStorageString());
                    printToConsole(taskList.addTaskToList(newDeadline));
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