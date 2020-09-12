package shiro;

import shiro.command.Command;
import shiro.command.CommandResult;
import shiro.exception.ShiroException;
import shiro.message.Message;
import shiro.storage.Storage;
import shiro.task.TaskList;
import shiro.parser.Parser;

/**
 * shiro is a personal chatbot cum task manager with the following functionalities:
 *     1. add and remove tasks from the list
 *     2. clear the entire list's tasks
 *     3. mark a task as done
 *     4. search for tasks using a query string
 *     5. search for tasks that occur on a given date
 *     6. display all of the existing tasks in the list
 * this class is the main driver for the chatbot
 */

public class Shiro {

    private Storage storage;
    private TaskList tasks;

    static final String FILE_PATH = "data/tasks.txt";

    /**
     * class constructor
     * @param filePath a string representing the destination file path where the list of tasks is stored
     */
    public Shiro(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ShiroException e) {
            System.out.println(Message.loadingErrorMessage());
            tasks = new TaskList();
        }
    }

    /**
     * returns a string corresponding to the response based on the command given
     * @param command the command given by the user
     * @return the appropriate response based on the given command
     */
    public CommandResult getResponse(String command) throws ShiroException {
        Command c = Parser.parse(command);
        try {
            return c.execute(tasks, storage);
        } catch (ShiroException e) {
            return c.execute(tasks, storage);
        }
    }
}
