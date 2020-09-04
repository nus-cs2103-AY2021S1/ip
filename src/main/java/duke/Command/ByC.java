package duke.Command;

import duke.*;
import duke.Command.Command;
import duke.task.Deadline;
import duke.task.Task;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ByC extends Command {

    private final String input;

    public ByC(String input) {
        this.input = input;
    }

    @Override
    public String execute(Ui ui, TaskList todoList, Storage store) throws IOException {
        LocalDate checkBy = LocalDate.parse(input.substring(3));
        String result = "";
        result += "By this day, you have: \n" ;
        int deadCount = 0;
        for (Task checkDead : todoList.todoList) {
            if (checkDead instanceof Deadline &&
                    (((Deadline) checkDead).by.isBefore(checkBy) ||
                            ((Deadline) checkDead).by.equals(checkBy))) {
                result += checkDead.toString();
                deadCount++;
            }
        }
        if (deadCount > 0) {
            result += "   [ A total of " + deadCount + " deadline(s)]";
        } else {
            result += "      [ You have no deadlines by this day ]";
        }
        return result;
    }
}
