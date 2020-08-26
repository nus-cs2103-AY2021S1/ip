package duke.command;

import duke.Duke;
import duke.task.Event;

public class EventCommand extends Command {

    public EventCommand() {
        names = new String[] { "event" };
    }

    @Override
    public void execute(String str, Duke duke) {
        Event newEvent = Event.createEvent(str);
        duke.taskList.tasks.add(newEvent);
        duke.ui.reportNewTask(newEvent);
    }

}
