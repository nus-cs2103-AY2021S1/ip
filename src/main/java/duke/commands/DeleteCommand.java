package duke.commands;

import duke.Parser;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command{

    @Override
    public String execute(String userInput, Ui ui, TaskList tasks, boolean isLoading) {
        // parse instruction
        int index = Parser.parseDeleteInstr(userInput);

        // execute
        Task chosenTask = tasks.getTask(index);
        tasks.deleteTask(index);
        assert !tasks.taskList.contains(chosenTask) : "task is not deleted deleted from taskList";

        // show message on UI if not Loading
        String response = "";
        if (!isLoading) {
            response = ui.showDeletedTask(chosenTask, tasks.taskList);
        }
        return response;
    }
}
