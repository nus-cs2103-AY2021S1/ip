package duke.commands;

import duke.Parser;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command{

    @Override
    public String execute(String userInput, Ui ui, TaskList tasks, boolean isLoading) {
        // parse instruction
        int index = Parser.parseMarkDoneInstr(userInput);

        // execute
        Task chosenTask = tasks.getTask(index);
        chosenTask.markAsDone();
        assert chosenTask.isDone : "task is not marked as done";

        // show message on UI if not Loading
        String response = "";
        if (!isLoading){
            response = ui.showMarkedDoneTask(chosenTask);
        }
        return response;
    }

}
