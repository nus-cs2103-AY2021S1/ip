package duke.command;

import duke.Gui.Gui;
import duke.component.DukeException;
import duke.component.EnumUserInstruction;
import duke.component.Storage;
import duke.task.*;

import java.util.ArrayList;

public class AddCommand extends Command {
    String fullCommand;
    EnumUserInstruction.userInstruction instructionCommand;

    /**
     * constructor for command to add tasks
     *
     * @param fullCommand entire string of input from user.
     * @param instructionCommand enum of the command instruction from the user.
     */
    public AddCommand(String fullCommand, EnumUserInstruction.userInstruction instructionCommand) {
        this.fullCommand = fullCommand;
        this.instructionCommand = instructionCommand;
    }

    /**
     * Executes command, main logic for creating a new task
     *
     * @param taskList list of tasks.
     * @param gui instance of Ui to deal with user interface.
     * @param storage to read / write to storage.
     * @throws DukeException exception thrown when exception caught while running.
     */
    @Override
    public ArrayList<String> execute(TaskList taskList, Gui gui, Storage storage, ArrayList<String> responseList)
            throws DukeException {
        String[] userInputArr = this.fullCommand.split(" ");
        switch (this.instructionCommand) {
        case TODO:
            try {
                if (userInputArr[1].isBlank()) {
                    throw new DukeException("The description of a todo cannot be empty");
                }
                Task todoTask = new Todo(fullCommand.substring(5));
                taskList.addItem(todoTask);
                // change data file
                storage.addTask(todoTask);

                return gui.addMessage(todoTask, taskList.getTasksLeft());

            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The description of a todo cannot be empty");
            }
        case DEADLINE:
            try {
                String substring = fullCommand.substring(9);
                String[] strArr = substring.split("/by");
                String description = strArr[0];
                String date = strArr[1];
                Task deadlineTask = new Deadline(description, date);
                taskList.addItem(deadlineTask);
                // change data file
                storage.addTask(deadlineTask);

                return gui.addMessage(deadlineTask, taskList.getTasksLeft());

            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please give me details for your deadline task");
            } catch (ArrayIndexOutOfBoundsException a) {
                throw new DukeException("Please give date / time after '/by' for this deadline task");
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            }
        case EVENT:
            try {
                String substring = fullCommand.substring(6);
                String[] strArr = substring.split("/at");
                String description = strArr[0];
                String date = strArr[1];
                Task eventTask = new Event(description, date);
                taskList.addItem(eventTask);

                // change data file
                storage.addTask(eventTask);

                return gui.addMessage(eventTask, taskList.getTasksLeft());

            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please give me information about your event!");
            } catch (ArrayIndexOutOfBoundsException a) {
                throw new DukeException("Please give date / time after '/at' for this event");
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            }
        default:

        }
        ArrayList<String> l = new ArrayList<>();
        l.add("THIS SHOULD NOT SHOW, addcommand class last line");
        return l;
    }
}
