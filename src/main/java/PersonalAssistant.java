import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PersonalAssistant {
    private ArrayList<Task> store;

    /**
     * Initialize personal assistant with a store for user input
     */
    public PersonalAssistant() {
        store = new ArrayList<>();
    }

    public void run() {
        System.out.println("> What can I do for you?");
        getUserCommands();
    }

    /**
     * Gets user input from STDIN, executes it
     */
    public void getUserCommands() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your command or \"bye\" to exit: ");

        // Tokenize the input
        String[] cmdTokens = reader.nextLine().split(" ");

        // Handle inputs
        this.execute(cmdTokens);
    }

    /**
     * Handle commands from users
     * @param cmdTokens
     * The command tokens from users
     */
    public void execute(String[] cmdTokens) {
        String cmd = cmdTokens[0];
        switch (cmd) {
            case "bye":
                System.out.println("Goodbye!");
                break;
            case "list":
                this.list();
                getUserCommands();
                break;

            case "done":
                // Handle incorrect argument lengths
                if (cmdTokens.length != 2) {
                    String errStatement = String.format("%s has invalid number of arguments", cmd);
                    System.out.println(errStatement);
                }

                try {
                    // The ACTUAL logic of the command
                    Integer taskNumber = Integer.parseInt(cmdTokens[1]);
                    // Set the task to done
                    Task task = store.get(taskNumber - 1);
                    task.done();
                    System.out.println(task);
                } catch (Exception e) {
                    // Handle parsing errors
                    String errStatement = String.format("%s should be supplied an integer as an argument", cmd);
                    System.out.println(errStatement);
                }

                // Get the next command
                this.getUserCommands();
                break;

            default:
                // Otherwise it is a task
                String taskName = String.join(" ", cmdTokens);
                Task task = new Task(false, taskName);

                // Store the task
                addTask(task);

                // Get the next command
                this.getUserCommands();
        }
    }

    public void addTask(Task task) {
        store.add(task);
        String message = String.format("Added: %s", task.getName());
        System.out.println(message);
        System.out.println("\n");
    }

    public void list() {
        for (int i = 0; i < store.size(); i++) {
            String listText = String.format("%d. %s", i + 1, store.get(i));
            System.out.println(listText);
        }
    }
}
