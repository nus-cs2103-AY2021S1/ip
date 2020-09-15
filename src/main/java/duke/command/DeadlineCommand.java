package duke.command;

import duke.Deadline;
import duke.Event;
import duke.Storage;
import duke.TaskList;

/**
 * DeadlineCommand class handles instructions when user wants to create Deadline.
 */

public class DeadlineCommand extends Command {
    public DeadlineCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    /**
     * Adds a Deadline object to TaskList object.
     * @param command User input
     * @param list TaskList object containing all tasks
     * @param storage Storage object that reads and writes to duke.txt
     * @return A response saying deadline has been added
     */
    @Override
    public String execute(String command, TaskList list, Storage storage) {
        try {
            String horizontalLine = "____________________________________\n";
            String shortenedCommand = command.substring(9);
            String[] commandArr = shortenedCommand.split(" /by ");
            String instructions = commandArr[0];
            String remainingCommand = commandArr[1];
            Deadline deadline;
            int counter = list.getList().size();
            String date;
            if (remainingCommand.contains("#")) {
                String[] remainingCommandArr = remainingCommand.split("#");
                if (remainingCommandArr[0].endsWith(" ")) {
                    int endIndex = remainingCommandArr[0].length() - 1;
                    date = remainingCommandArr[0].substring(0, endIndex);
                } else {
                    date = remainingCommandArr[0];
                }
                String tag = "#" + remainingCommandArr[1];
                deadline = new Deadline(false, counter + 1, instructions, date, tag);
            } else {
                date = remainingCommand;
                deadline = new Deadline(false, counter + 1, instructions, date);
            }
            list.addTask(counter, deadline);
            storage.writeData(list.getList());
            String taskMessage = list.getList().get(counter).toString();
            return horizontalLine + "Okok. I help you add this task: \n"
                    + taskMessage + "\n" + "You got " + (counter + 1) + " tasks in the list.\n" + horizontalLine;
        } catch (Exception ex) {
            return Warnings.invalidDeadlineWarning();
        }
    }
}
