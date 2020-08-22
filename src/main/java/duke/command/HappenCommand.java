package duke.command;

import duke.component.*;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HappenCommand extends Command {
    public HappenCommand(String input) {
        super(input);
    }

    @Override
    public void execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException {
        String description = input.substring(7);
        try {
            if (description.equals("on today")) {
                ui.printList(list, Task::happenToday, "happening today ");
            } else if (description.startsWith("on ")) {
                LocalDate date = LocalDate.parse(description.substring(3),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                ui.printList(list, t -> t.happenOnDate(date), "happening on " +
                        date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ");
            } else if (description.equals("before today")) {
                ui.printList(list, Task::happenBeforeToday, "happening before today ");
            } else if (description.startsWith("before ")) {
                LocalDate date = LocalDate.parse(description.substring(7),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                ui.printList(list, t -> t.happenBeforeDate(date), "happening before " +
                        date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ");
            } else if (description.equals("after today")) {

            } else {
                throw new InvalidCommandException("Invalid happen command input");
            }
        } catch (InvalidCommandException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid date format. Please use yyyy-MM-dd");
        }
    }
}
