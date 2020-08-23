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
    static String farewellMsg = "Bye. Hope to see you again soon!";
    static String demarcation = Ui.indentation + "-------------------------------------------------------------------";

    TaskList tasks;
    Storage storage;

    Ui(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    private String indentWord(String word) {
        return Ui.indentation + word;
    }

    private void greetings() {
        System.out.println("Hello from\n" + Ui.logo);
        System.out.println(Ui.prompt);
    }

    private void printTask(Task task, Action action) {
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
        System.out.println(indentWord(String.format("Now you have %s tasks in the list.", tasks.getSize())));
    }

    private void printTasks(ArrayList<String> tasksDescription) {
        int counter = 1;
        System.out.println("Here are the tasks in your list:");
        for (String description : tasksDescription) {
            System.out.print(indentWord(counter + ". "));
            System.out.println(description);
            counter++;
        }
    }

    public void getInput() {
        greetings();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            Parser parser = new Parser(command);

            String commandType = parser.getCommandType();

            System.out.println(Ui.demarcation);
            // Dispatch respective handlers depending on command
            switch (commandType) {
            case ("bye"):
                System.out.println(indentWord(farewellMsg));
                return;
            case ("list"):
                printTasks(tasks.getTasksDescription());
                break;
            case ("delete"):
                // Print response from Duke after deleting task
                printTask(tasks.deleteTask(parser.getTaskToModify()), Action.DELETE);
                break;
            case ("done"):
                printTask(tasks.markDone(parser.getTaskToModify()), Action.DONE);
                break;
            default:
                try {
                    printTask(tasks.addTask(parser.getNewTask()), Action.ADD);
                } catch (DukeException e) {
                    System.out.println(indentWord(e.getMessage()));
                }
                break;
            }
            System.out.println(Ui.demarcation);
            storage.saveToFile(tasks.getTasksInfo());
        }
    }
}
