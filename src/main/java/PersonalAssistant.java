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
        System.out.println("\nEnter your command or \"bye\" to exit: ");

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
                StringBuilder taskName = new StringBuilder();
                for (int i = 1; i < cmdTokens.length; i++) {
                    String token = cmdTokens[i];
                    taskName.append(" " + token);
                }
                Task task = new TodoTask(false, taskName.toString());

                // Store the task
                addTask(task);

                // Get the next command
                this.getUserCommands();
                break;
            }

            case "deadline": {
                // Otherwise it is a task
                StringBuilder taskName = new StringBuilder();
                // Collate the first segment (taskName)
                int i = 1;
                for (; i < cmdTokens.length; i++) {
                    String token = cmdTokens[i];
                    if (token.equals("/by")) {
                        i++;
                        break;
                    }
                    taskName.append(" " + token);
                }

                // Collate the second segment (taskTime)
                StringBuilder taskTime = new StringBuilder();
                for (; i < cmdTokens.length; i++) {
                    String token = cmdTokens[i];
                    taskTime.append(" " + token);
                }

                Task task = new DeadlineTask(
                        false,
                        taskName.toString(),
                        taskTime.toString()
                );

                // Store the task
                addTask(task);

                // Get the next command
                this.getUserCommands();
                break;
            }

            case "event": {
                // Otherwise it is a task
                StringBuilder taskName = new StringBuilder();
                // Collate the first segment (taskName)
                int i = 1;
                for (; i < cmdTokens.length; i++) {
                    String token = cmdTokens[i];
                    if (token.equals("/at")) {
                        i++;
                        break;
                    }
                    taskName.append(" " + token);
                }

                // Collate the second segment (taskTime)
                StringBuilder taskTime = new StringBuilder();
                for (; i < cmdTokens.length; i++) {
                    String token = cmdTokens[i];
                    taskTime.append(" " + token);
                }

                Task task = new DeadlineTask(
                        false,
                        taskName.toString(),
                        taskTime.toString()
                );

                // Store the task
                addTask(task);

                // Get the next command
                this.getUserCommands();
                break;
            }

            default:
                System.out.println("Invalid command");
                this.getUserCommands();
        }
    }

    public void addTask(Task task) {
        store.add(task);
        String message = String.format("Added: %s", task.toString());
        System.out.println(message);
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
