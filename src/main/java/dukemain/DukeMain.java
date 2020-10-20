package dukemain;

import data.TaskList;
import ui.Ui;

public class DukeMain {

    private TaskList list;

    public DukeMain() {
        // data.txt will be created in same directory as duke.jar
        // if it does not already exist
        this.list = new TaskList("data.txt");
    }

    // For CLI Duke. But does not run anymore not sure why??
    public static void main(String[] args) {
        TaskList taskList = new TaskList("src/savedata/data.txt");
        Ui.startUpMessage();
        taskList.runCommands();
        taskList.save();
    }

    public String getResponse(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.list.runSingleCommand(input));
        this.list.save();
        return sb.toString();
    }

}

