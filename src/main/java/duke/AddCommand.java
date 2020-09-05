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
                output += convertToOutputString(todoTask.toString(), tasks.size());
                storage.writeToFile(tasks);
                break;
            case DEADLINE:
                String[] inputArray = description.split(" /by ");
                String task = inputArray[0];
                String by = inputArray[1];
                Task deadlineTask = new Deadline(task, LocalDate.parse(by));
                tasks.add(deadlineTask);
                output += output += convertToOutputString(deadlineTask.toString(), tasks.size());
                storage.writeToFile(tasks);
                break;
            case EVENT:
                inputArray = description.split(" /at ");
                task = inputArray[0];
                String at = inputArray[1];
                Task eventTask = new Event(task, LocalDate.parse(at));
                tasks.add(eventTask);
                output += output += convertToOutputString(eventTask.toString(), tasks.size());
                storage.writeToFile(tasks);
                break;
            default:
                throw new DukeException("Please key in a valid command");
            }
            ui.showOutputOnScreen(output);
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

    private String convertToOutputString(String task, int size) {
        return "Got it. I've added this task:\n\t" + task + "\nYou now have " + size + " tasks in the list.";
    }
}
