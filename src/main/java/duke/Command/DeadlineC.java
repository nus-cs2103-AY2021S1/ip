package duke.Command;

import duke.*;
import duke.Command.Command;
import duke.task.Deadline;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DeadlineC extends Command {

    private final String input;

    public DeadlineC(String input) {
        this.input = input;
    }

    @Override
    public String execute(Ui ui, TaskList todoList, Storage store) throws DukeException {
        assert input.length() > 7 : "no date entered";
        String result = "";
        try {
            String fullDL = input.substring(9);
            String dlName = fullDL.split("/by")[0];
            LocalDate dlTime = LocalDate.parse(fullDL.split("/by ")[1]);
            Deadline dl = new Deadline(dlName, dlTime);
            todoList.addDead(dl);
            result += "Aight new task for you: \n" + dl.toString() +"\n";
            result += "Now you got " + todoList.size() + " task(s) waiting man";
            store.write(dl);
        } catch (StringIndexOutOfBoundsException | IOException e) {
            throw new DukeException("You either didn't enter a date or a task");
        } catch ( DateTimeParseException e) {
            throw new DukeException("Something's wrong with the date, make sure the format's yyyy/mm/dd!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Make sure to enter the command as such: deadline {task} /by {yyyy/mm/dd}");
        }
        return result;
    }
}
