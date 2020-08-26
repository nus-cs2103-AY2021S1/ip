package duke.command;

import duke.*;
import duke.task.Event;
import duke.task.TaskList;

import java.io.IOException;

public class AddEventCommand extends Command {
    AddEventCommand(String command) {
        super(command);
    }

    public void execute(TaskList list, Ui ui, Storage saveData) {
        try {
            if (this.command.trim().length() == 5) {
                throw new DukeException("☹ OOPS!!! Check event formatting, include description and /at.");
            } else if (!this.command.contains("/at")) {
                throw new DukeException("☹ OOPS!!! Check event formatting, include /at.");
            }
            String holder[] = this.command.split("event")[1].split("/at ");
            String description = holder[0].trim();
            String at = holder[1].trim();
            Event task = new Event(description, at);

            list.add(task);
            ui.saySomthing("Got it. I've added this task:\n" + task.toString() + "\n" + String.format("Now you have %d tasks in the list.", list.size()));

            String save = "E>0>"+description+">"+at;
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
