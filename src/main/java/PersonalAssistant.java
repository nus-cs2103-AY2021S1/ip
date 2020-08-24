import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PersonalAssistant {
    private ArrayList<Task> store;
    private Scanner reader;

    /**
     * Initialize personal assistant with a store for user input
     */
    public PersonalAssistant() {
        store = new ArrayList<>();
        reader = new Scanner(System.in);

        try {
            // Initializes storage folder & file
            File storageFolder = new File("./data");
            storageFolder.mkdir(); // creates folder if it doesn't exist

            File storageFile = new File("./data/duke.txt");
            storageFile.createNewFile(); // creates file if it doesn't exist
        } catch (Exception e) {
            System.out.println("Failed to initialize storage");
            System.exit(1);
        }
    }

    public void run() {
        System.out.println("> What can I do for you?");
        getUserCommands();
    }

    /**
     * Gets user input from STDIN, executes it
     */
    public void getUserCommands() {
        try {
            System.out.println("\nEnter your command or \"bye\" to exit: ");

            // Tokenize the input
            String input = reader.nextLine();
            String[] cmdTokens = TokenParser.tokenize(input);

            // Handle inputs
            this.execute(cmdTokens);

        } catch (CommandMissingArgumentException e) {
            System.out.println("Missing arguments!");
        } catch (CommandInvalidArgumentFormatException e) {
            System.out.println("Invalid arguments!");
        } catch (MissingTaskException e) {
            System.out.println("Missing task!");
        } catch (Exception e) {
            throw e;
        } finally {
            this.getUserCommands();
        }
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
                System.exit(0);
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
                if (cmdTokens.length <= 1) {
                    throw new CommandMissingArgumentException();
                }

                Integer taskIndex = TokenParser.parseInt(cmdTokens[1]) - 1;

                // If the task doesn't exist (It's index is missing)
                if (taskIndex < 0 || taskIndex >= store.size()) {
                    throw new MissingTaskException();
                }
                completeTask(taskIndex);

                // Get the next command
                this.getUserCommands();
                break;
            }

            case "delete": {
                // Handle incorrect argument lengths
                if (cmdTokens.length <= 1) {
                    throw new CommandMissingArgumentException();
                }

                Integer taskIndex = TokenParser.parseInt(cmdTokens[1]) - 1;

                // If the task doesn't exist (It's index is missing)
                if (taskIndex < 0 || taskIndex >= store.size()) {
                    throw new MissingTaskException();
                }
                deleteTask(taskIndex);

                // Get the next command
                this.getUserCommands();
                break;
            }


            /**
             * TASK COMMANDS
             */
            case "todo": {
                // Handle missing inputs
                if (cmdTokens.length <= 1) {
                    throw new CommandMissingArgumentException();
                }

                String taskName = cmdTokens[1];

                if (taskName.equals("")) {
                    throw new CommandMissingArgumentException();
                }

                // Create the task
                Task task = new TodoTask(false, taskName);

                // Store the task
                addTask(task);

                // Get the next command
                this.getUserCommands();
                break;
            }

            case "deadline": {
                // Handle missing inputs
                if (cmdTokens.length <= 1) {
                    throw new CommandMissingArgumentException();
                }

                // Arguments
                String[] arguments = cmdTokens[1].split("/by", 2);
                if (arguments.length <= 1
                        || arguments[0].equals("")
                        || arguments[1].equals("")) {
                    throw new CommandMissingArgumentException();
                }

                // Otherwise create the task
                Task task = new DeadlineTask(
                        false,
                        arguments[0],
                        arguments[1]
                );

                // Store the task
                addTask(task);

                // Get the next command
                this.getUserCommands();
                break;
            }

            case "event": {
                // Handle missing inputs
                if (cmdTokens.length <= 1) {
                    throw new CommandMissingArgumentException();
                }

                // Arguments
                String[] arguments = cmdTokens[1].split("/at", 2);
                if (arguments.length <= 1
                        || arguments[0].equals("")
                        || arguments[1].equals("")) {
                    throw new CommandMissingArgumentException();
                }

                // Otherwise create the task
                Task task = new EventTask(
                        false,
                        arguments[0],
                        arguments[1]
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

    public void completeTask(Integer taskIndex) {
        // Set the task to done
        Task task = store.get(taskIndex);
        task.done();
        System.out.println("Task marked as complete:");
        System.out.println(task);
    }

    public void deleteTask(int taskIndex) {
        // Set the task to done
        Task task = store.remove(taskIndex);
        System.out.println("Task deleted:");
        System.out.println(task);
    }

    public void list() {
        for (int i = 0; i < store.size(); i++) {
            String listText = String.format("%d. %s", i + 1, store.get(i));
            System.out.println(listText);
        }
    }
}
