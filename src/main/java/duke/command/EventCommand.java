package duke.command;

import duke.main.TaskList;
import duke.task.Event;

public class EventCommand extends Command {

    protected Event event;

    public EventCommand(String taskDescription, String date) {
        this.event = new Event(taskDescription, date);
    }

    @Override
    public void perform(TaskList tasks) {
        tasks.add(event);
        System.out.println(" Okay! I have added this task:" + "\n" + "   "
                + event.toString() + "\n" + " Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks."
                : " task."));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
