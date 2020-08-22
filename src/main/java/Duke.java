package main.java;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static TaskList taskList;
    static DataManager dataManager = new DataManager();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo =
                " _\n" +
                        "//\\\n" +
                        "V  \\\n" +
                        " \\  \\_\n" +
                        "  \\,'.`-.\n" +
                        "   |\\ `. `.\n" +
                        "   ( \\  `. `-.                        _,.-:\\\n" +
                        "    \\ \\   `.  `-._             __..--' ,-';/\n" +
                        "     \\ `.   `-.   `-..___..---'   _.--' ,'/\n" +
                        "      `. `.    `-._        __..--'    ,' /\n" +
                        "        `. `-_     ``--..''       _.-' ,'\n" +
                        "          `-_ `-.___        __,--'   ,'\n" +
                        "             `-.__  `----\"\"\"    __.-'\n" +
                        "                   `--..____..--'";

        System.out.println(logo);
        printInWindow("Hello, I'm a banana.\nWhat can I do for you?");

        try {
            taskList = dataManager.load();

            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] splitInput = input.split(" ", 2);

                CommandType command = CommandType.enumFromString(splitInput[0]);
                String parameters = splitInput.length > 1 ? splitInput[1] : null;
                int result = processCommand(command, parameters);
                if (result == 0) {
                    return;
                }
            }
        } catch (DukeException exception) {
            printInWindow(exception.getMessage());
        }
    }

    public static int processCommand(CommandType command, String parameters) throws DukeException{
        switch(command) {
        case TODO:
            ToDo todo = new ToDo(parameters);
            taskList.addTask(todo);
            dataManager.save(taskList);
            printInWindow("Added: " + todo.toString());
            break;
        case EVENT:
            String[] eventParameters = parameters.split(" /at ");
            Event event = new Event(eventParameters[0], LocalDate.parse(eventParameters[1]));
            taskList.addTask(event);
            dataManager.save(taskList);
            printInWindow("Added: " + event.toString());
            break;
        case DEADLINE:
            String[] deadlineParameters = parameters.split(" /by ");
            Deadline deadline = new Deadline(deadlineParameters[0], LocalDate.parse(deadlineParameters[1]));
            taskList.addTask(deadline);
            dataManager.save(taskList);
            printInWindow("Added: " + deadline.toString());
            break;
        case DONE:
            int doneNumber = Integer.parseInt(parameters);
            taskList.updateDone(doneNumber);
            dataManager.save(taskList);
            printInWindow("Nice! I've marked the this as done.\n" + taskList.getTask(doneNumber).toString());
            break;
        case LIST:
            printInWindow(taskList.toString());
            break;
        case DELETE:
            int deleteNumber = Integer.parseInt(parameters);
            printInWindow("I've removed this task:\n" + taskList.getTask(deleteNumber).toString());
            taskList.deleteTask(deleteNumber);
            dataManager.save(taskList);
            break;
        case BYE:
            printInWindow("Bye. Hope to see you again soon!");
            return 0;
        case UNDEFINED:
            throw new DukeException("I don't know what that means!");
        default:
            throw new DukeException("This command has not yet been implemented!");
        }
        return 1;
    }

    public static void printInWindow(String text) {
        String divider = "---------------------------------------------";
        System.out.println(divider);
        System.out.println(text);
        System.out.println(divider);
    }
}
