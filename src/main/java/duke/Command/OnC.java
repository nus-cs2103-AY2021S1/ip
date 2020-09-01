package duke.Command;

import duke.*;
import duke.Command.Command;
import duke.task.Event;
import duke.task.Task;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class OnC extends Command {

    public void execute(Ui ui, TaskList todoList, Storage store) throws IOException {
        LocalDate checkOn = LocalDate.parse(ui.sc.nextLine().trim());
        System.out.println("On this day, you have: ");
        int eventCount = 0;
        for (Task checkEvent : todoList.getTodoList()) {
            if (checkEvent instanceof Event && ((Event) checkEvent).at.equals(checkOn)) {
                System.out.println(checkEvent.toString());
                eventCount++;
            }
        }
        if (eventCount > 0) {
            System.out.println("   [ A total of " + eventCount + " event(s)]");
        } else {
            System.out.println("   [ You have no events on this day ]");
        }
    }
}
