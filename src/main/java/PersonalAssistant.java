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
            /**
             * EXIT
             */
            case "bye": {
                System.out.println("Goodbye!");
                break;
            }

            /**
             * LIST TASKS
             */
            case "list": {
                this.list();
                getUserCommands();
                break;
            }

            /**
             * UPDATE TASKS
             */
            case "done": {
                // Handle incorrect argument lengths
                if (cmdTokens.length != 2) {
                    String errStatement = String.format("%s has invalid number of arguments", cmd);
                    System.out.println(errStatement);
                }

                try {
                    /**
                     * ACTUAL COMMAND LOGIC HERE
                     */
                    Integer taskIndex = Integer.parseInt(cmdTokens[1]) - 1;
                    completeTask(taskIndex);
                } catch (Exception e) {
                    // Handle parsing errors
                    String errStatement = String.format("%s should be supplied an integer as an argument", cmd);
                    System.out.println(errStatement);
                }

                // Get the next command
                this.getUserCommands();
                break;
            }

            /**
             * TASK COMMANDS
             */
            case "todo": {
                // Otherwise it is a task
                String taskName = String.join(" ", cmdTokens);
                Task task = new Task(false, taskName, TaskTypes.TODO);

                // Store the task
                addTask(task);

                // Get the next command
                this.getUserCommands();
                break;
            }

            case "deadline": {
                // Otherwise it is a task
                String taskName = String.join(" ", cmdTokens);
                Task task = new Task(false, taskName, TaskTypes.DEADLINE);
                // Store the task
                addTask(task);

                // Get the next command
                this.getUserCommands();
                break;
            }

            case "event": {
                // Otherwise it is a task
                String taskName = String.join(" ", cmdTokens);
                Task task = new Task(false, taskName, TaskTypes.EVENT);

                // Store the task
                addTask(task);

                // Get the next command
                this.getUserCommands();
                break;
            }

            default:
                System.out.println("Invalid command");
        }
    }

    public void addTask(Task task) {
        store.add(task);
        String message = String.format("Added: %s", task.getName());
        System.out.println(message);
        System.out.println("\n");
    }

    public void completeTask(Integer taskNumber) {
        // Set the task to done
        Task task = store.get(taskNumber);
        task.done();
        System.out.println("Task marked as complete:");
        System.out.println(task);
    }

    public void list() {
        for (int i = 0; i < store.size(); i++) {
            String listText = String.format("%d. %s", i + 1, store.get(i));
            System.out.println(listText);
        }
    }
}
