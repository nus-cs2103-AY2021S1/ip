package junimo;

import junimo.backend.Storage;
import junimo.interaction.Parser;
import junimo.interaction.Greeting;
import junimo.task.TaskList;
import junimo.ui.Response;

import java.io.FileNotFoundException;

/**
 * The junimo.Junimo Class handles all the internal (non-GUI) logic of the application.
 *
 * The program is a CLI Application that allows users to:
 * <ul>
 *     <li>Add new tasks (Deadline, Event or Todo)</li>
 *     <li>View a list of their tasks</li>
 *     <li>Mark tasks as done</li>
 *     <li>Delete tasks</li>
 *     <li>Save tasks in the hard disk automatically</li>
 * </ul>
 *
 * @author Damith C. Rajapakse
 * @author Jeffry Lum
 * @author Jiachen
 * @author Jeanne Toh
 */
public class Junimo {
    private Storage storage;

    private TaskList taskList;
    private Parser parser;
    
    public Junimo(String filePath) {
        try {
            storage = new Storage(filePath);
            taskList = new TaskList();
            parser = new Parser(taskList, storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not created. " + e);
        }
    }

    public String getWelcome() {
        return Greeting.welcome();
    }

    public Response parseInputCommand(String inputCommand) {
        return parser.parseInputCommand(inputCommand);
    }

    public void save() {
        storage.save(taskList);
    }
}
