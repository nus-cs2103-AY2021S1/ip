package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui;

public class Todo extends Task{
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public Todo markDone() {
        super.markDone();
        return this;
    }

    @Override
    public void excute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.add(this);
        ui.showAddedMessage(tasklist, tasklist.getNumOfTasks() - 1);
        storage.writeData(tasklist.taskList);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String fileFormattedString() {
        String doneOrNot = isDone ? "1" : "0";
        return "T | " + doneOrNot + " | " + this.name;
    }
}
