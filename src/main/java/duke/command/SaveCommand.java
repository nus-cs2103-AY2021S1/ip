package duke.command;

import duke.misc.Ui;
import duke.task.TaskList;

import java.util.List;

public class SaveCommand extends Command {
    public SaveCommand(List<String> input) {
        super(input);
    }

    @Override
    public String run(TaskList taskList) {
        taskList.save();
        return Ui.answerSave();
    }
}
