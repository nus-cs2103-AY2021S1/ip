import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the Duke Chatbot.
 * Allows users to store a list of Tasks, consisting of three types,
 * namely Todos, Events and Deadlines. Users input, change and delete
 * such items via text commands to the bot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Builds an instance of the Duke Chatbot.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the chatbot instance.
     * Chatbot will begin listening for commands with this method.
     */
    public void run() {
        try {
            ui.intro();
            Scanner input = new Scanner(System.in);

            // Uses user input text and acts accordingly.
            loop:
            while (input.hasNextLine()) {
                String line = input.nextLine();
                Command command = ui.parseCommand(line);
                ui.lineBreak();

                switch (command.name) {
                case "bye":
                    System.out.println("That's it? That's a shame. Well, see you later then.");
                    ui.lineBreak();
                    storage.saveTasks();
                    break loop;
                case "list":
                    tasks.printList();
                    break;
                case "done":
                    tasks.taskDone(command.index);
                    break;
                case "delete":
                    tasks.removeFromList(command.index);
                    break;
                case "error":
                    System.out.println(command.errorMessage);
                    break;
                default:
                    tasks.addToList(command.task);
                }
                ui.lineBreak();
            }

            input.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
