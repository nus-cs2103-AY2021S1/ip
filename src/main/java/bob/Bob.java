package bob;

import java.io.IOException;

import bob.commands.Command;
import bob.common.MsgGenerator;
import bob.data.task.Tasklist;
import bob.exceptions.BobException;
import bob.parser.Parser;
import bob.storage.Storage;

/**
 * Represents the task-managing ChatBot.
 */
public class Bob {
    private final Storage storage;
    private final Tasklist tasks;
    private boolean hasExited;
    /**
     * Creates a Bob.
     *
     * @throws IOException If saved file can't be loaded.
     */

    public Bob() throws IOException, BobException {
        boolean ifPathDirIsTest = System.getProperty("user.dir").endsWith("text-ui-test");
        boolean ifPathDirIsIp = System.getProperty("user.dir").endsWith("ip");

        String filePath = ifPathDirIsTest
                ? "test.txt"
                : ifPathDirIsIp
                ? "data/bob.txt"
                // Creates a save file on the user's home directory if user is not in ip directory
                : System.getProperty("user.home") + "/bob.txt";

        Tasklist tempTasks = null;
        this.storage = new Storage(filePath);
        try {
            tempTasks = new Tasklist(storage);
        } catch (BobException | IOException e) {
            tempTasks = new Tasklist();
            throw e;
        } finally {
            this.tasks = tempTasks;
            this.hasExited = false;
        }
    }

    /**
     * Executes an action based on user input
     * @param userInput User input.
     * @return Response message to user.
     */

    public String nextAction(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            String replyMessage = c.execute(tasks, storage);
            this.hasExited = c.isExited();
            return replyMessage;
        } catch (IOException e) {
            return MsgGenerator.generateUpdatingError();
        } catch (BobException e) {
            return MsgGenerator.generateError(e);
        }
    }

    public boolean checkExited() {
        return this.hasExited;
    }
}
