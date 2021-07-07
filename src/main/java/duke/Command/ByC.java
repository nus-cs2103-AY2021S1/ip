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

    private boolean isBy(Deadline d, LocalDate date) {
        return (d.by.isBefore(date) || d.by.equals(date));
    }

    @Override
    public String execute(Ui ui, TaskList todoList, Storage store) throws IOException, DukeException {
        assert input.length() > 2 : "no date entered";
        String result = "";
        try {
            LocalDate checkBy = LocalDate.parse(input.substring(3));
            result += "By this day, you have: \n" ;
            int deadCount = 0;
            for (Task checkDead : todoList.todoList) {
                if (checkDead instanceof Deadline && isBy((Deadline)checkDead, checkBy)) {
                    result += checkDead.toString();
                    deadCount++;
                }
            }
            if (deadCount > 0) {
                result += "   [ A total of " + deadCount + " deadline(s)]";
            } else {
                result += "      [ You have no deadlines by this day ]";
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("You didn't enter date!");
        }

        return result;
    }
}
