package duke.Command;

import duke.*;
import duke.Command.Command;
import duke.task.Deadline;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DeadlineC extends Command {

    public void execute(Ui ui, TaskList todoList, Storage store){
        String fullDL = ui.sc.nextLine();
        try {
            String dlName = fullDL.split("/by")[0];
            LocalDate dlTime = LocalDate.parse(fullDL.split("/by ")[1]);
            Deadline dl = new Deadline(dlName, dlTime);
            todoList.addDead(dl);
            System.out.println("Aight new task for you: \n" + dl.toString());
            System.out.println("Now you got " + todoList.size() + " task(s) waiting man");
            store.write(dl);
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            System.out.println("You either didn't enter a date or a task");
        }
    }
}
