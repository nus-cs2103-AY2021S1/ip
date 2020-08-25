package duke.command;

import duke.common.CustomException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Adds a event task.
 */
public class EventCommand extends Command {
    private static String description;
    private static String at;

    public EventCommand(String description, String at) {
        EventCommand.description = description;
        EventCommand.at = at;
    }
    public void execute (TaskList tasks, Ui ui, Storage storage) throws CustomException, IOException {
//        try {
            Task temp = new Event(description, at);
            tasks.addTask(temp);
            Storage.appendToFile(temp);
            Ui.display("Yay! New task added:\n   " + temp +
                    "\nNow you have " + tasks.getSize() + " tasks in your list.");
//        } catch (Exception e) {
//            throw new CustomException("Please input a suitable date of <yyyy-mm-dd> format!");
//        }
    }
}
