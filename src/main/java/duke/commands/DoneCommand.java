package duke.commands;

import duke.*;

public class DoneCommand extends Command{

    @Override
    public String execute(String userInput, Ui ui, TaskList tasks, boolean isLoading) throws DukeException {
        // parse instruction
        int index = Parser.parseMarkDoneInstr(userInput);
        if (!(0 <= index && index < tasks.taskList.size())) {
            if (tasks.taskList.size() == 1) {
                throw new DukeException("☹ OOPS!!! index have to be 1");
            } else {
                throw new DukeException("☹ OOPS!!! index have to be between 1 and " + (tasks.taskList.size()));
            }
        }

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
