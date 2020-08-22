package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static List<Task> tasks;
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
            tasks = dataManager.load();

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
            tasks.add(todo);
            dataManager.save(tasks);
            printInWindow("Added: " + todo.toString());
            break;
        case EVENT:
            String[] eventParameters = parameters.split(" /at ");
            Event event = new Event(eventParameters[0], eventParameters[1]);
            tasks.add(event);
            dataManager.save(tasks);
            printInWindow("Added: " + event.toString());
            break;
        case DEADLINE:
            String[] deadlineParameters = parameters.split(" /by ");
            Deadline deadline = new Deadline(deadlineParameters[0], deadlineParameters[1]);
            tasks.add(deadline);
            dataManager.save(tasks);
            printInWindow("Added: " + deadline.toString());
            break;
        case DONE:
            int doneNumber = Integer.parseInt(parameters);
            Task task = tasks.get(doneNumber - 1);
            task.markAsDone(true);
            dataManager.save(tasks);
            printInWindow("Nice! I've marked the this as done.\n" + task.toString());
            break;
        case LIST:
            printInWindow(formatTaskListToString(tasks));
            break;
        case DELETE:
            int deleteNumber = Integer.parseInt(parameters);
            Task deleteTask = tasks.get(deleteNumber - 1);
            tasks.remove(deleteNumber - 1);
            dataManager.save(tasks);
            printInWindow("I've removed this task:\n" + deleteTask.toString());
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

    public static String formatTaskListToString(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(tasks.get(i).toString());
            if(i < tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
