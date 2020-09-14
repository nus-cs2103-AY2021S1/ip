package duke.Command;

import duke.*;
import duke.Command.Command;
import duke.task.Event;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EventC extends Command {

    private final String input;

    public EventC(String input) {
        this.input = input;
    }

    @Override
    public String execute(Ui ui, TaskList todoList, Storage store) throws IOException, DukeException {
        assert input.length() > 5 : "no date entered";

        String result = "";
        try {
            String fullE = input.substring(6) ;
            String eventName = fullE.split("/at")[0];
            LocalDate eventTime = LocalDate.parse(fullE.split("/at ")[1]);
            Event e = new Event(eventName, eventTime);
            todoList.addEvent(e);
            result += "Aight new task for you: \n" + e.toString();
            result += "\nNow you got " + todoList.size() + " task(s) waiting man";
            store.write(e);
        } catch (StringIndexOutOfBoundsException | IOException e) {
            throw new DukeException("You didn't enter a date!");
        }
        return result;
    }
}
