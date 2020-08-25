import utility.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {

    /**
     * Used for printing messages to enhance user experience.
     */
    private Ui ui;

    /**
     * Controls the reading and writing of data to hard disk.
     */
    private Storage storage;

    /**
     * Data structure for managing tasks.
     */
    private TaskList tasks;

    /**
     * Responsible for reading user input and parsing it.
     */
    private Parser parser;

    /**
     * Creates Duke, a personal assistant bot.
     * @param filePath The directory path of the text file Duke reads and saves to.
     */
    Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Duke personal assistant bot.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        ui.showWelcome();
        while (true) {
            String input = sc.nextLine();
            ui.showLine();
            try {
                Choice c = parser.parseCommand(input);
                switch (c) {
                    case BYE:
                        System.out.println("Bye. Hope to see you again soon!");
                        storage.writeToFile(tasks.getTaskList());
                        System.exit(0); // End program
                    case DELETE:
                        tasks.deleteTask(input);
                        break;
                    case DONE:
                        tasks.completeTask(input);
                        break;
                    case LIST:
                        tasks.listAllTasks();
                        break;
                    case TODO:
                        tasks.addTodo(input);
                        break;
                    case EVENT:
                        tasks.addEvent(input);
                        break;
                    case DEADLINE:
                        tasks.addDeadline(input);
                        break;
                    case FIND:
                        tasks.findTasks(input);
                        break;
                    default:
                        System.out.println("Error in switch - Default case");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main program
     * @param args
     * @throws DukeException
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws DukeException, FileNotFoundException {
        new Duke("data.txt").run();
    }
}