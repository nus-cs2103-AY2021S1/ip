package duke;

import java.util.Scanner;

/**
 * Implements User interface
 */
public class Ui {
    private Storage store;
    private TaskList tasks;
    private Scanner reader;

    /**
     * Initialize personal assistant with a store for user input
     */
    public Ui() {
        store = new Storage();
        tasks = store.getTaskList();
        reader = new Scanner(System.in);
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

            Command command = Parser.parseLine(tasks, input);
            command.execute();

            // After every command, save to disk
            store.syncTasks();
            this.getUserCommands();

        } catch (CommandMissingArgumentException e) {
            System.out.println("Missing arguments!");
        } catch (CommandInvalidArgumentFormatException e) {
            System.out.println("Invalid arguments!");
        } catch (MissingTaskException e) {
            System.out.println("Missing task!");
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            this.getUserCommands();
        }
    }
}
