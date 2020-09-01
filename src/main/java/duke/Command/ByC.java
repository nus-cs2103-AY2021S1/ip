package duke.Command;

import duke.*;
import duke.Command.Command;
import duke.task.Deadline;
import duke.task.Task;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ByC extends Command {

    public void execute(Ui ui, TaskList todoList, Storage store) throws IOException {
        LocalDate checkBy = LocalDate.parse(ui.sc.nextLine().trim());
        System.out.println("By this day, you have: ");
        int deadCount = 0;
        for (Task checkDead : todoList.todoList) {
            if (checkDead instanceof Deadline &&
                    (((Deadline) checkDead).by.isBefore(checkBy) ||
                            ((Deadline) checkDead).by.equals(checkBy))) {
                System.out.println(checkDead.toString());
                deadCount++;
            }
        }
        if (deadCount > 0) {
            System.out.println("   [ A total of " + deadCount + " deadline(s)]");
        } else {
            System.out.println("   [ You have no deadlines by this day ]");
        }
    }
}
