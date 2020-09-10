package duke;

import command.CommandHandler;
import exception.DukeException;
import java.io.IOException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Duke {

    private TaskList taskList;
    private Storage storage;
    private boolean isExit = false;


    public Duke (String dest, String fileName){

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

    public boolean isExit(){
        return this.isExit;
    }

    public TaskList getTaskList(){
        return this.taskList;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public void setExit(boolean isExit){
        this.isExit = isExit;
    }
}
