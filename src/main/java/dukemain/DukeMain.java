package dukemain;

import data.TaskList;
import ui.Ui;

public class DukeMain {

    private TaskList list;

    public DukeMain() {
        this.list = new TaskList("src/savedata/data.txt");
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList("src/savedata/data.txt");
        Ui.startUpMessage();
        taskList.runCommands();
        taskList.save();
    }

    public String getResponse(String input) {
        String out = this.list.runSingleCommand(input);
        list.save();
        return out;
    }

}

