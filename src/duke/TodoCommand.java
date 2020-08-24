package duke;

public class TodoCommand extends Command {

    public TodoCommand(String task) {
        super(task);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        processTodo(this.task, taskList, ui, storage);
    }

    public void processTodo(String theRest, TaskList taskList, Ui ui, Storage storage) {
        Todo todo = new Todo(theRest);
        taskList.saveToList(todo);
        storage.updateData(taskList.getTasks());
    }
}
