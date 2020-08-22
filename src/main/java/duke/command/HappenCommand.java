package duke.command;

import duke.component.*;

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
                ui.printList(list, t -> t.happenToday(), "happening today ");
            } else if (description.startsWith("on ")) {
                LocalDate date = LocalDate.parse(description.substring(3),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                ui.printList(list, t -> t.happenOnDate(date), "happening on " +
                        date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ");
            }
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid date format. Please use yyyy-MM-dd");
        }
    }
}
