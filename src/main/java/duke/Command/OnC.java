package duke.Command;

import duke.*;
import duke.Command.Command;
import duke.task.Event;
import duke.task.Task;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class OnC extends Command {

    @Override
    public String execute(Ui ui, TaskList todoList, Storage store) throws IOException {
        String result = "";
        LocalDate checkOn = LocalDate.parse(ui.sc.nextLine().trim());
        result += "On this day, you have: \n";
        int eventCount = 0;
        for (Task checkEvent : todoList.getTodoList()) {
            if (checkEvent instanceof Event && ((Event) checkEvent).at.equals(checkOn)) {
                result += checkEvent.toString() + "\n";
                eventCount++;
            }
        }
        if (eventCount > 0) {
            result += "   [ A total of " + eventCount + " event(s)]";
        } else {
            result += "   [ You have no events on this day ]";
        }
        return result;
    }
}
