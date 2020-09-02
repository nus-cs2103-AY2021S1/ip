package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;

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
        String filePath = System.getProperty("user.dir").endsWith("text-ui-test")
                ? "test.txt"
                : System.getProperty("user.dir").endsWith("ip")
                ? "data/duke.txt"
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
