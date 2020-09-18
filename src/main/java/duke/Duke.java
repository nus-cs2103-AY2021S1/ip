package duke;

import duke.command.Command;
import duke.exception.DukeException;

import java.io.IOException;

/**
 * Represents Duke
 */
public class Duke {
    private Storage storage;
    private TaskList tasklist;
    private boolean isExited;

    /**
     * Creates Duke
     *
     * @throws IOException
     */
    public Duke() throws DukeException, IOException {
        boolean isPathDirAtTest = System.getProperty("user.dir").endsWith("text-ui-test");
        boolean isPathDirAtIp = System.getProperty("user.dir").endsWith("ip");

        String filePath = isPathDirAtTest
                ? "test.txt"
                : isPathDirAtIp
                ? "data/duke.txt"
                // Creates file in user's home directory
                : System.getProperty("user.home") + "/duke.txt";

        this.storage = new Storage(filePath);
        TaskList tempTaskList = null;
        try {
            tempTaskList = new TaskList(storage);
        } catch (DukeException | IOException e) {
            tempTaskList = new TaskList();
            throw e;
        } finally {
            this.tasklist = tempTaskList;
            this.isExited = false;
        }
    }

    public boolean isExited() {
        return this.isExited;
    }

    public String getResponse(String userInput) {
        try {
            Command command = Parser.parse(userInput);
            String replyMessage = command.execute(tasklist, storage);
            this.isExited = command.isExited();
            return replyMessage;
        } catch (IOException e) {
            return "Update failed, check file for corruption.";
        } catch (DukeException e) {
            return e.toString();
        }
    }

}
