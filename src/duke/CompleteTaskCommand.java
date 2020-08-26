package duke;

import java.io.IOException;

public class CompleteTaskCommand extends Command {
    public CompleteTaskCommand(String command) {
        super(command);
    }

    public void execute(TaskList list, Ui ui, Storage saveData) {
        try {
            if (this.command.trim().length() == 4) {
                throw new DukeException("☹ OOPS!!! Check done formatting, include which task to complete.");
            } else if (Character.getNumericValue(this.command.charAt(5)) > list.size() || Character.getNumericValue(this.command.charAt(5)) == 0) {
                throw new DukeException("☹ OOPS!!! Task not in the list");
            }
            int index = Character.getNumericValue(this.command.charAt(5));
            completeTask(index, list, ui);
            saveData.completeTask(index);
        } catch (DukeException | IOException e) {
            ui.saySomthing(e.getMessage());
        }
    }

    public void completeTask(int index, TaskList list, Ui ui) {
        list.get(index-1).markAsDone();
        ui.saySomthing("Nice! I've marked this task as done:\n" + list.get(index-1).toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
