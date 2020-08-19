import exception.DukeException;
import exception.NoDescriptionException;
import exception.UnknownCommandException;

import java.util.ArrayList;

public class Duke {
    public static boolean exitLoop = false;
    public static ArrayList<Task> tasks = new ArrayList<>();

    private static void greet() {
        UIPrint.drawLine(UIPrint.star, 50);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        UIPrint.drawLine(UIPrint.star, 50);
    }

    private static void echo(String str) {
        UIPrint.drawLine(UIPrint.star, 50);
        System.out.println(str);
        UIPrint.drawLine(UIPrint.star, 50);
    }

    public static void reportNewTask(Task task) {
        UIPrint.drawLine(UIPrint.star, 50);

        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

        UIPrint.drawLine(UIPrint.star, 50);
    }

    private static boolean checkCommand(String str) {
        String[] inputParts = str.split(" ", 2);
        String possibleCommand = inputParts[0];
        String rest = inputParts.length == 2 ? inputParts[1] : "";
        Command command = null;

        try {
            command = DukeCommandSet.getInstance().getCommand(possibleCommand);
        } catch (UnknownCommandException exception) {
            System.out.println(exception.getMessage());
        }

        if (command != null) {
            try {
                command.execute(rest);
            } catch (NoDescriptionException exception) {
                System.out.println(exception.getMessage());
            }

            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        while (!exitLoop) {
            String inputLine = UserInput.getOneLine();

            boolean hasCommand = checkCommand(inputLine);

            if (!hasCommand) {
                // do nothing
            }
        }
    }
}
