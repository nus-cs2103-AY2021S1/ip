import java.io.FileNotFoundException;

import utility.Choice;
import utility.Parser;
import utility.Storage;
import utility.TaskList;
import utility.Ui;

public class Duke {

    /**
     * Used for printing messages to enhance user experience.
     */
    private final Ui ui;

    /**
     * Controls the reading and writing of data to hard disk.
     */
    private final Storage storage;

    /**
     * Data structure for managing tasks.
     */
    private TaskList tasks;

    /**
     * Responsible for reading user input and parsing it.
     */
    private final Parser parser;

    /**
     * Creates Duke, a personal assistant bot.
     */
    Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("data.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            tasks = new TaskList();
        }
    }

    /**
     * Returns output message to the user.
     */
    public String getResponse(String input) {
        ui.showLine();
        try {
            Choice c = parser.parseCommand(input);
            System.out.println(c);
            switch (c) {
            case BYE:
                System.out.println("Bye. Hope to see you again soon!");
                storage.writeToFile(tasks.getTaskList());
                System.exit(0); // End program
                break;
            case DELETE:
                return tasks.deleteTask(input);
            case DONE:
                return tasks.completeTask(input);
            case LIST:
                return tasks.listAllTasks();
            case TODO:
                return tasks.addTodo(input);
            case EVENT:
                return tasks.addEvent(input);
            case DEADLINE:
                System.out.println("HI");
                return tasks.addDeadline(input);
            case FIND:
                return tasks.findTasks(input);
            case HELP:
                return ui.showHelp();
            default:
                System.out.println("Error in switch - Default case");
                break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "Oops, I didn't catch what you said: " + input;
    }
}
