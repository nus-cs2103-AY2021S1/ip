package duke.logic.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.model.TaskManager;
import duke.model.task.Deadline;
import duke.model.task.Event;
import duke.model.task.Task;
import duke.model.task.ToDo;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Handles 'deadline', 'event' and 'todo' command input by user.
 */
public class AddCommand extends Command {

    /**
     * Constructor for AddCommand class.
     *
     * @param command String input by user.
     */
    public AddCommand(String command) {
        super(command);
    }

    /**
     * Interprets the type of Task the user wishes to add.
     *
     * @param tm TaskManager that handles tasks in memory.
     * @param ui User interface that interacts with the user.
     * @param storage Storage class that handles saving and loading from file.
     * @return Response generated after command is executed.
     * @throws DukeException If command is not properly formatted.
     */
    @Override
    public String execute(TaskManager tm, Ui ui, Storage storage) throws DukeException {
        if (command.startsWith("deadline")) {
            // Handles empty deadline command
            if (command.length() <= 9) {
                throw new DukeException("Description of a deadline cannot be empty!");
            }
            String[] commandDetails = command.substring(9).split(" /by ", 2);

            // Handles improperly formatted deadline command
            if (commandDetails.length != 2) {
                throw new DukeException("Deadline not properly formatted!");
            }

            LocalDate dlDate;
            String description = commandDetails[0];
            try {
                dlDate = LocalDate.parse(commandDetails[1]);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid DateTime format. Please use YYYY-MM-DD.");
            }

            boolean isDuplicate = isDuplicateDeadline(tm, description, dlDate);
            if (isDuplicate) {
                throw new DukeException("The task you have added already exists.");
            }

            Deadline deadline = new Deadline(description, dlDate);
            tm.addTask(deadline);
            postCommandSave(tm, storage);
            return ("Task added: " + deadline);

        } else if (command.startsWith("event")) {
            // Handles empty event command
            if (command.length() <= 6) {
                throw new DukeException("Description of a deadline cannot be empty!");
            }
            String[] commandDetails = command.substring(6).split(" /at ", 2);

            // Handles improperly formatted event command
            if (commandDetails.length != 2) {
                throw new DukeException("Event not properly formatted!");
            }

            LocalDate eventDate;
            String description = commandDetails[0];
            try {
                eventDate = LocalDate.parse(commandDetails[1]);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid DateTime format. Please use YYYY-MM-DD.");
            }

            boolean isDuplicate = isDuplicateEvent(tm, description, eventDate);
            if (isDuplicate) {
                throw new DukeException("The task you have added already exists.");
            }

            Event event = new Event(commandDetails[0], eventDate);
            tm.addTask(event);
            postCommandSave(tm, storage);
            return ("Task added: " + event);

        } else if (command.startsWith("todo")) {
            // Handles empty todo command
            if (command.length() <= 5) {
                throw new DukeException("Description of a deadline cannot be empty!");
            }
            String commandDetails = command.substring(5);

            boolean isDuplicate = isDuplicateToDo(tm, commandDetails);
            if (isDuplicate) {
                throw new DukeException("The task you have added already exists.");
            }

            ToDo todo = new ToDo(commandDetails);
            tm.addTask(todo);
            postCommandSave(tm, storage);
            return ("Task added: " + todo);

        } else {
            throw new DukeException("Command not recognised!");
        }
    }

    /**
     * Detects if new Deadline is a duplicate of a previous Deadline.
     *
     * @param tm TaskManager that handles tasks in memory.
     * @param description Description associated with new Deadline.
     * @param dlDate Date associated with the new Deadline.
     * @return True if a duplicate Deadline is present, false otherwise.
     */
    private boolean isDuplicateDeadline(TaskManager tm, String description, LocalDate dlDate) {
        ArrayList<Task> duplicatesList = tm.findTasks(description);
        if (duplicatesList.isEmpty()) {
            return false;
        }
        for (Task task:duplicatesList) {
            boolean descriptionMatches = task.getDescription().equals(description);
            boolean dateMatches = false;
            boolean isDeadline = task instanceof Deadline;
            if (isDeadline) {
                dateMatches = ((Deadline) task).getBy().compareTo(dlDate) == 0;
            }
            if (isDeadline && descriptionMatches && dateMatches) {
                return true;
            }
        }
        return false;
    }

    /**
     * Detects if a new Event is a duplicate of a previous Event.
     *
     * @param tm TaskManager that handles tasks in memory.
     * @param description Description associated with new Event.
     * @param eventDate Date associated with the new Event.
     * @return True if a duplicate Event is present, false otherwise.
     */
    private boolean isDuplicateEvent(TaskManager tm, String description, LocalDate eventDate) {
        ArrayList<Task> duplicatesList = tm.findTasks(description);
        if (duplicatesList.isEmpty()) {
            return false;
        }
        for (Task task:duplicatesList) {
            boolean descriptionMatches = task.getDescription().equals(description);
            boolean dateMatches = false;
            boolean isEvent = task instanceof Event;
            if (isEvent) {
                dateMatches = ((Event) task).getAt().compareTo(eventDate) == 0;
            }
            if (isEvent && descriptionMatches && dateMatches) {
                return true;
            }
        }
        return false;
    }

    /**
     * Detects if a new ToDo is a duplicate of a previous ToDo.
     *
     * @param tm TaskManager that handles tasks in memory.
     * @param description Description associated with the new ToDo.
     * @return True if a duplicate ToDo is present, false otherwise.
     */
    private boolean isDuplicateToDo(TaskManager tm, String description) {
        ArrayList<Task> duplicatesList = tm.findTasks(description);
        if (duplicatesList.isEmpty()) {
            return false;
        }
        for (Task task:duplicatesList) {
            if (task.getDescription().equals(description) && task instanceof ToDo) {
                System.out.println("here");
                return true;
            }
        }
        return false;
    }
}
