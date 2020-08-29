import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    static String indentation = "    ";
    static String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    static String prompt = "What can I do for you?";
    static String demarcation = Ui.indentation + "-------------------------------------------------------------------";

    TaskList tasks;
    Storage storage;

    Ui(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    private static String indentWord(String word) {
        return Ui.indentation + word;
    }

    private void greetings() {
        System.out.println("Hello from\n" + Ui.logo);
        System.out.println(Ui.prompt);
    }

    public static void simplePrint(String message) {
        System.out.println(indentWord(message));
    }

    public static void printTask(Task task, Action action, int size) {
        String message = "";
        switch (action) {
        case ADD:
            message = "Got it. I've added this task:";
            break;
        case DONE:
            message = "Nice! I've marked this task as done:";
            break;
        case DELETE:
            message = "Noted. I've removed this task:";
            break;
        default:
            break;
        }
        System.out.println(indentWord(message));
        System.out.println(indentWord(task.toString()));
        System.out.println(indentWord(String.format("Now you have %s tasks in the list.", size)));
    }

    public static void printTasks(ArrayList<String> tasksDescription, Action action) {
        String prompt;
        switch (action) {
        case FIND:
            prompt = "Here are the matching tasks in your list:";
            break;
        case LIST:
            prompt = "Here are the tasks in your list:";
            break;
        default:
            prompt = "";
            break;
        }
        int counter = 1;
        System.out.println(indentWord(prompt));
        for (String description : tasksDescription) {
            System.out.print(indentWord(counter + ". "));
            System.out.println(description);
            counter++;
        }
    }

    /**
     * Runs a scanner to parse user input and print response.
     */

    public void getInput() throws DukeException {
        greetings();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            Command command = Parser.getCommand(input);

            System.out.println(Ui.demarcation);
            // Dispatch respective handlers depending on command
            boolean exit = command.execute(tasks);

            System.out.println(Ui.demarcation);

            if (exit) {
                return;
            } else {
                storage.saveToFile(tasks.getTasksInfo());
            }
        }
    }
}
