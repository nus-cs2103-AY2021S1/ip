package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidDeleteCommandException;
import duke.storage.ArrayListToTextConverter;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.List;

public class DeleteCommand extends UserCommand {
    public DeleteCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        List<Task> ls = taskList.getTaskList();
        String[] deleteCommandArray = userInput.split(" ");
        if (deleteCommandArray.length < 2) {
            throw new InvalidDeleteCommandException();
        } else {
            int itemToBeDeleted = Integer.parseInt(deleteCommandArray[1]);
            if (itemToBeDeleted > ls.size() || itemToBeDeleted <= 0) {
                throw new InvalidDeleteCommandException();
            } else {
                Task item = ls.get(itemToBeDeleted - 1);
                ls.remove(itemToBeDeleted - 1);
                System.out.println("Noted. I've removed this task:");
                System.out.println(item.toString());
                System.out.format("Now you have %d tasks in the list\n", ls.size());

//                ArrayListToTextConverter.convertArrayListToText(ls);

            }
        }
    }
}
