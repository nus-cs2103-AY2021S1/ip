package duke;

import java.io.IOException;

public class AddTodoCommand extends Command {

    AddTodoCommand(String command) {
        super(command);
    }

    public void execute(TaskList list, Ui ui, Storage saveData) {
        try {
            if (this.command.trim().length() == 4) {
                throw new DukeException("☹ OOPS!!! Check todo formatting, include description");
            }
            Todo task = new Todo(this.command.substring(5));
            list.add(task);
            ui.saySomthing("Got it. I've added this task:\n" + task.toString() + "\n" + String.format("Now you have %d tasks in the list.", list.size()));
            String save = "T>0>"+this.command.substring(5);
            saveData.addTask(save);
        } catch (DukeException | IOException e) {
            ui.saySomthing(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
