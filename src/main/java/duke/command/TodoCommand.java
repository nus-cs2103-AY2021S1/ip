package duke.command;

import duke.exception.DukeException;

import duke.storage.Storage;

import duke.task.ToDo;

import duke.tasklist.TaskList;

import duke.ui.Ui;

public class TodoCommand extends Command {
    private String todoDetails;
    public TodoCommand(String todoDetails) {
        this.todoDetails = todoDetails;
    }

    /**
     * Creates new duke.task, adds duke.task to TaskList then updates the Storage.
     *
     * @param taskList the list of tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            ToDo newToDo = new ToDo(this.todoDetails, false);
            taskList.add(newToDo);
            storage.save(taskList);
            String response = "Got it. I've added this task: \n" + taskList.get(taskList.size() - 1) + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.";
            return response;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please specify your todo description! \n");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
