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
            String[] commandArr = command.split("\\s+");
            String instructions = commandArr[1];
            String date = commandArr[3];
            Event event;
            int counter = list.getList().size();
            if (commandArr.length == 5) {
                String tag = commandArr[4];
                if (!tag.startsWith("#")) {
                    return Warnings.invalidTagWarning();
                }
                event = new Event(false, counter + 1, instructions, date, tag);
            } else {
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
