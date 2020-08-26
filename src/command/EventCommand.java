package command;

import exception.IncorrectFormatException;
import task.Event;
import ui.UIPrint;
import data.DukeData;
import function.DukeFunction;

public class EventCommand extends Command {

    public EventCommand() {
        names = new String[] { "event" };
    }

    @Override
    public void execute(String str) {
        Event newEvent = Event.createEvent(str);
        DukeData.tasks.add(newEvent);
        DukeFunction.reportNewTask(newEvent);
    }

}
