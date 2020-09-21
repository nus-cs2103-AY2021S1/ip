package dukemain;

import data.TaskList;

public class DukeMain {

    public static void main(String[] args) {
        TaskList taskList = new TaskList("src/savedata/data.txt");
        Ui.startUpMessage();
        taskList.runCommands();
        taskList.save();
    }
}