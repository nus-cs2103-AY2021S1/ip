package duke.command;

import duke.Event;
import duke.Storage;
import duke.TaskList;

/**
 * EventCommand class handles instructions when user wants to create Event.
 */
public class EventCommand extends Command {
    public EventCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    /**
     * Adds an Event object to TaskList object.
     * @param command User input
     * @param list TaskList object containing all tasks
     * @param storage Storage object that reads and writes to duke.txt
     * @return A response saying event has been added
     */
    @Override
    public String execute(String command, TaskList list, Storage storage) {
        try {
            String horizontalLine = "____________________________________\n";
            String shortenedCommand = command.substring(6);
            String[] commandArr = shortenedCommand.split(" /at ");
            String instructions = commandArr[0];
            String remainingCommand = commandArr[1];
            Event event;
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
                event = new Event(false, counter + 1, instructions, date, tag);
            } else {
                date = remainingCommand;
                event = new Event(false, counter + 1, instructions, date);
            }
            list.addTask(counter, event);
            String taskMessage = list.getList().get(counter).toString();
            storage.writeData(list.getList());
            return horizontalLine + "Okok. I help you add this task: \n"
                    + taskMessage + "\n" + "You got " + (counter + 1) + " tasks in the list.\n" + horizontalLine;
        } catch (Exception ex) {
            return Warnings.invalidEventWarning();
        }

    }
}
