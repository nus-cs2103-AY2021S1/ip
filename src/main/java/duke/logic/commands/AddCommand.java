package duke.logic.commands;

import duke.exceptions.DukeException;
import duke.model.TaskManager;
import duke.model.task.Deadline;
import duke.model.task.Event;
import duke.model.task.ToDo;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command{

    public AddCommand(String command){
        super(command);
    }

    @Override
    public void execute(TaskManager tm, Ui ui, Storage storage) throws DukeException {
        if (command.startsWith("deadline")){
            if (command.length() <= 9) {
                throw new DukeException("Description of a deadline cannot be empty!");
            }
            String[] commandDetails = command.substring(9).split(" /by ", 2);
            if (commandDetails.length != 2) {
                throw new DukeException("Deadline not properly formatted!");
            }
            try {
                LocalDate dlDate = LocalDate.parse(commandDetails[1]);
                Deadline deadline = new Deadline(commandDetails[0], dlDate);
                tm.addTask(deadline);
                ui.showDetails("Task added: " + deadline);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid DateTime format. Please use YYYY-MM-DD.");
            }
        } else if (command.startsWith("event")) {
            if (command.length() <= 6) {
                throw new DukeException("Description of a deadline cannot be empty!");
            }
            String[] commandDetails = command.substring(6).split(" /at ", 2);
            if (commandDetails.length != 2) {
                throw new DukeException("Event not properly formatted!");
            }
            try {
                LocalDate eventDate = LocalDate.parse(commandDetails[1]);
                Event event = new Event(commandDetails[0], eventDate);
                tm.addTask(event);
                ui.showDetails("Task added: " + event);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid DateTime format. Please use YYYY-MM-DD.");
            }
        } else if (command.startsWith("todo")) {
            if (command.length() <= 5) {
                throw new DukeException("Description of a deadline cannot be empty!");
            }
            String commandDetails = command.substring(5);
            ToDo todo = new ToDo(commandDetails);
            tm.addTask(todo);
            ui.showDetails("Task added: " + todo);
        } else {
            throw new DukeException("Command not recognised!");
        }
    }
}
