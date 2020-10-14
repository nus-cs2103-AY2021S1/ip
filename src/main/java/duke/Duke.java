package duke;

import java.io.IOException;

import command.CommandHandler;
import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Duke {

    private TaskList taskList;
    private Storage storage;
    private boolean isExit = false;

    /**
     * Instantiates Duke bot by loading Storage Data files into Tasklist. If data files or folders do not
     * exist based on the destination and filename given, Duke bot creates path and file specified.
     *
     * @param dest
     * @param fileName
     */
    public Duke(String dest, String fileName) {

        try {
            storage = new Storage(dest, fileName);
            taskList = new TaskList(storage.load());

        } catch (DukeException | IOException e) {
            System.out.println(Ui.showError(e));
        }
    }


    public String getResponse(String input) {
        return CommandHandler.execute(input, this);
    }

    /**
     * @return
     */
    public boolean isExit() {
        return this.isExit;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }
}
