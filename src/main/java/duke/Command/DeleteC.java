package duke.Command;

import duke.Command.Command;
import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteC extends Command {

    private final String input;

    public DeleteC(String input) {
        this.input = input;
    }

    @Override
    public String execute(Ui ui, TaskList todoList, Storage store) throws IOException, DukeException {
        assert input.length() > 6 : "no date entered";

        String result = "";
        try {
            int deleteID = Integer.parseInt( input.substring(7)) - 1;
            Task deleted = todoList.get(deleteID);
            todoList.deleteTask(deleteID);
            store.overwrite(todoList);
            result += "Gotchu, I am removing \n" + deleted.toString() + "\n";
            result += "Now you got " + todoList.size() + " task(s) waiting man";
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("You didn't enter the ID of the task you want to delete!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no task with that ID, use list to find the " +
                    "ID of the task you want to delete");
        }

        return result;
    }
}
