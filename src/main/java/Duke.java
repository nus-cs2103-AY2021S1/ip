package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo =
                "//\\\n" +
                "V  \\\n" +
                " \\  \\_\n" +
                "  \\,'.`-.\n" +
                "   |\\ `. `.       \n" +
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

        List<Task> tasks = new ArrayList<>();

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] splitInput = input.split(" ", 2);
            String command = splitInput[0];
            String parameters = splitInput.length > 1 ? splitInput[1] : null;
            switch(command) {
            case "todo":
                ToDo todo = new ToDo(parameters);
                tasks.add(todo);
                printInWindow("Added: " + todo.toString());
                break;
            case "event":
                String[] eventParameters = parameters.split(" /at ");
                Event event = new Event(eventParameters[0], eventParameters[1]);
                tasks.add(event);
                printInWindow("Added: " + event.toString());
                break;
            case "deadline":
                String[] deadlineParameters = parameters.split(" /by ");
                Deadline deadline = new Deadline(deadlineParameters[0], deadlineParameters[1]);
                tasks.add(deadline);
                printInWindow("Added: " + deadline.toString());
                break;
            case "done":
                int taskNumber = Integer.parseInt(parameters);
                Task task = tasks.get(taskNumber - 1);
                task.markAsDone(true);
                printInWindow(
                        "Nice! I've marked the this as done." +
                                task.toString()
                );
                break;
            case "list":
                printInWindow(formatTaskListToString(tasks));
                break;
            case "bye":
                printInWindow("Bye. Hope to see you again soon!");
                return;
            default:
                Task newTask = new Task(input);
                tasks.add(newTask);
                printInWindow("Added: " + input);
            }
        }
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
