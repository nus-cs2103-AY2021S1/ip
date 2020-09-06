package duke.command;

import java.util.List;

import duke.misc.Ui;
import duke.task.TaskList;


public class FindCommand extends Command {
    public FindCommand(List<String> input) {
        super(input);
    }

    @Override
    public String run(TaskList taskList) {
        assert input.size() == 2: "FindCommand.run(): input must have exactly 2 words";
        String query = input.get(1);
        return Ui.answerFind(taskList.findTasks(query));
    }
}
