package command;

import exception.DukeException;
import storage.Storage;
import task.ToDo;
import tasklist.TaskList;
import ui.Ui;

public class TodoCommand extends Command {
    private String todoDetails;
    public TodoCommand(String todoDetails) {
        this.todoDetails = todoDetails;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ToDo newToDo = new ToDo(this.todoDetails, false);
        taskList.add(newToDo);
        String output = ui.LINE + "Got it. I've added this task: \n"
                + taskList.get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " tasks in the list."
                + "\n" + ui.LINE;
        System.out.println(output);
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
