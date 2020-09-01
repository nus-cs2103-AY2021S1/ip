package duke.Command;

import duke.*;
import duke.Command.Command;
import duke.task.Event;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EventC extends Command {

    public void execute(Ui ui, TaskList todoList, Storage store) throws IOException {
        try {
            String fullE = ui.sc.nextLine();
            String eventName = fullE.split("/at")[0];
            LocalDate eventTime = LocalDate.parse(fullE.split("/at ")[1]);
            Event e = new Event(eventName, eventTime);
            todoList.addEvent(e);
            System.out.println("Aight new task for you: \n" + e.toString());
            System.out.println("Now you got " + todoList.size() + " task(s) waiting man");
            store.write(e);
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            System.out.println("You either didn't enter a date or a task");
        }
    }
}
