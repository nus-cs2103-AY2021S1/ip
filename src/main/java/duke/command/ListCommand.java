package duke.command;

import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends UserCommand{


    public ListCommand(String userInput) {
        super(userInput);
    }


    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printList(taskList);
    }
}
