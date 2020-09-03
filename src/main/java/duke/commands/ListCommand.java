package duke.commands;


import duke.TaskList;
import duke.Ui;

public class ListCommand {
    public ListCommand() {

    }


    public String run(TaskList taskList) {
        return Ui.getTasks(taskList);
    }
}
