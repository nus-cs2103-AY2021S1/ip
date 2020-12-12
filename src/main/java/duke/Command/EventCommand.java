package duke.Command;

import duke.AppUi;
import duke.task.Event;
import duke.TaskList;

public class EventCommand extends Command {

    private static final int EVENT = 2;
    private TaskList list;
    private AppUi appUi;

    public EventCommand(String input, TaskList list) {
        super(input);
        this.list = list;
        this.appUi = new AppUi();
    }

    public String execute(){
        Event event = Event.of(input);
        if (event == null) {
            return appUi.getWrongEventFormatMsg();
        }
        list.add(event);
        String res = "";
        if (event.getDate() == null) {
            res += appUi.getWrongDateFormatMsg();
        }
        if (event.getTime() == null) {
            res += appUi.getWrongTimeFormatMsg();
        }
        return res + appUi.getAfterAddMsg(event, EVENT, list.getSize());
    }
}
