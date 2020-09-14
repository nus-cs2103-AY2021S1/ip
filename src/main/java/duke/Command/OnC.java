package duke.Command;

import duke.*;
import duke.Command.Command;
import duke.task.Event;
import duke.task.Task;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class OnC extends Command {

    private final String input;

    public OnC(String input) {
        this.input = input;
    }

    @Override
    public String execute(Ui ui, TaskList todoList, Storage store) throws IOException, DukeException {
        assert input.length() > 2 : "no date entered";
        String result = "";
        try {
            LocalDate checkOn = LocalDate.parse(input.substring(3).trim());
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
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("You didn't enter a date!");
        }

        return result;
    }
}
