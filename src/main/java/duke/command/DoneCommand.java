package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidDoneCommandException;
import duke.storage.ArrayListToTextConverter;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.List;

public class DoneCommand extends UserCommand {

    public DoneCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        List<Task>ls = taskList.getTaskList();
        String[] doneCommandArray = userInput.split(" ");
        if (doneCommandArray.length < 2) {
            throw new InvalidDoneCommandException();
        } else {
            int itemToBeMarkedAsDone = Integer.parseInt(doneCommandArray[1]);
            if (itemToBeMarkedAsDone > ls.size() || itemToBeMarkedAsDone <= 0) {
                throw new InvalidDoneCommandException();
            } else {
                ls.get(itemToBeMarkedAsDone - 1).markAsDone();
//                ArrayListToTextConverter.convertArrayListToText(ls);
            }
        }
    }
}
