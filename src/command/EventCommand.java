package command;

import data.DukeTaskList;
import task.Event;
import ui.Ui;

public class EventCommand extends Command {

    public EventCommand() {
        names = new String[] { "event" };
    }

    @Override
    public void execute(String str) {
        Event newEvent = Event.createEvent(str);
        DukeTaskList.tasks.add(newEvent);
        Ui.reportNewTask(newEvent);
    }

}
