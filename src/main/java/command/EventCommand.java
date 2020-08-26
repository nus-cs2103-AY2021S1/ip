package command;

import exception.DukeException;
import storage.Storage;
import task.Event;
import tasklist.TaskList;
import ui.Ui;

public class EventCommand extends Command {
    private String eventDetails;

    public EventCommand(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] details = this.eventDetails.split(" /at ", 2);
        Event newEvent = new Event(details[0], details[1], false);
        taskList.add(newEvent);
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
