package duke.command;

import duke.exception.InvalidDateFormatException;

import duke.parser.DateParser;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

import java.util.Optional;

public class FindCommand extends Command {
    public final static String COMMAND = "find";
    
    private String date;
    
    public FindCommand(String date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateFormatException {
        Optional<LocalDate> optDate = DateParser.parse(date);
        if (optDate.isPresent()) {
            LocalDate date = optDate.get();

            TaskList filtered = tasks.filter((task) -> 
                    ((task instanceof Deadline && ((Deadline) task).isDueOn(date))
                    || (task instanceof Event && ((Event) task).isOccuringOn(date))));
            ui.showFindResult(filtered);
        } else {
            throw new InvalidDateFormatException();
        }
    }
}
