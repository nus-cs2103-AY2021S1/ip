import java.io.IOException;
import java.util.Scanner;

/** Duke is a chatbot that allows users to send input to perform tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /** Creates a new Duke chatbot and load tasks from storage.
     * @param filePath path to the storage file to load tasks from
     * */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            // Load task list from saved file
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            // Create task list
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /** Runs main conversation loop with Duke chatbot.
     * */
    public void run() {
        // Introduction messages
        System.out.println("Hello! I'm Duke! I'm a chatbot-based To-Do list manager.");
        System.out.println("My available commands are: todo, deadline, event, done, list, delete, bye");
        System.out.println("What can I do for you today? :)");

        // Main conversation loop
        Scanner sc = new Scanner(System.in);
        boolean isSpeaking = true;
        while (isSpeaking) {

            // Process user input
            String userInput = sc.nextLine();
            String[] userTokens = userInput.split(" ");
            String userCommand = userTokens[0];

            // Validate command
            try {
                Parser.validateCommand(userCommand);
            } catch (DukeException e) {
                System.out.println("Sorry, that looks like an invalid command! " + e.getMessage());
            }

            switch(userCommand) {
                // Exit the program
                case "bye":
                    isSpeaking = false;
                    System.out.println("Bye! Hope to see you again soon!");
                    break;

                // List the tasks available in taskList
                case "list":
                    taskList.listTasks();
                    break;

                // Create a to-do task
                case "todo":
                    try {
                        taskList.addTask(Parser.parseTodo(userInput));
                        storage.save(taskList);
                    } catch (DukeException | IOException e) {
                        System.out.println("Sorry, I can't add that todo! " + e.getMessage());
                    }
                    break;


                // Create a deadline task (contains "/by")
                case "deadline":
                    try {
                        taskList.addTask(Parser.parseDeadline(userInput));
                        storage.save(taskList);
                    } catch (DukeException | IOException e) {
                        System.out.println("Sorry, I can't add that deadline! " + e.getMessage());
                    }
                    break;

                // Create a event task (contains "/at")
                case "event":
                    try {
                        taskList.addTask(Parser.parseEvent(userInput));
                        storage.save(taskList);
                    } catch (DukeException | IOException e) {
                        System.out.println("Sorry, I can't add that event! " + e.getMessage());
                    }
                    break;

                // Mark the identified task as done
                case "done":
                    try {
                        taskList.setDone(Parser.parseDone(userInput));
                        storage.save(taskList);
                    } catch (DukeException | IOException e) {
                        System.out.println("Sorry, I can't mark that as done! " + e.getMessage());
                    }
                    break;

                // Delete a task
                case "delete":
                    try {
                        taskList.deleteTask(Parser.parseDelete(userInput));
                        storage.save(taskList);
                    } catch (DukeException | IOException e) {
                        System.out.println("Sorry, I can't delete that task! " + e.getMessage());
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }
}
