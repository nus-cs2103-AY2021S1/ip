package command;

import task.Event;
import data.DukeData;
import ui.Ui;

public class EventCommand extends Command {

    public EventCommand() {
        names = new String[] { "event" };
    }

    @Override
    public void execute(String str) {
        Event newEvent = Event.createEvent(str);
        DukeData.tasks.add(newEvent);
        Ui.reportNewTask(newEvent);
    }

}
