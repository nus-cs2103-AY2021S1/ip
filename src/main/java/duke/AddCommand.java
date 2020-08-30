package duke;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents an add command.
 */
public class AddCommand extends Command {
    private final CommandEnum type;

    /**
     * Constructs AddCommand initialized with command type and description.
     * @param type Type of add command.
     * @param description Description of the command.
     */
    AddCommand(CommandEnum type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String output = "";
        try {
            switch (type) {
            case TODO:
                if (description.isEmpty()) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                Task todoTask = new Todo(description);
                tasks.add(todoTask);
                output += "Got it. I've added this task:\n\t"
                        + todoTask + "\nYou now have " + tasks.size()
                        + " tasks in the list.";
                storage.writeToFile(tasks);
                break;
            case DEADLINE:
                String task = description.split(" /by ")[0];
                String by = description.split(" /by ")[1];
                Task deadlineTask = new Deadline(task, LocalDate.parse(by));
                tasks.add(deadlineTask);
                output += "Got it. I've added this task:\n\t"
                        + deadlineTask + "\nYou now have " + tasks.size()
                        + " tasks in the list.";
                storage.writeToFile(tasks);
                break;
            case EVENT:
                task = description.split(" /at ")[0];
                String at = description.split(" /at ")[1];
                Task eventTask = new Event(task, LocalDate.parse(at));
                tasks.add(eventTask);
                output += "Got it. I've added this task:\n\t"
                        + eventTask + "\nYou now have " + tasks.size()
                        + " tasks in the list.";
                storage.writeToFile(tasks);
                break;
            default:
                throw new DukeException("Please key in a valid command");
            }
            ui.showOutput(output);
        } catch (IOException | DateTimeException ex) {
            throw new DukeException(ex.getMessage());
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new DukeException("Please follow a valid command format!");
        }


    }

    @Override
    public boolean isExit() {
        return false;
    }
}
