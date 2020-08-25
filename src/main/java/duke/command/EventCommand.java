package duke.command;

import duke.Event;
import duke.Storage;
import duke.TaskList;

public class EventCommand extends Command {
    public EventCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    @Override
    public void execute(String command, TaskList list, Storage storage) {
        String hor_line = "____________________________________\n";
        String instructions = command.substring(6);
        String[] arr = instructions.split("/at");
        instructions = arr[0].substring(0, arr[0].length() - 1);
        String date = arr[1].substring(1);
        int counter = list.getList().size();
        list.addTask(counter, new Event(false, counter + 1, instructions, date));
        String taskMessage = list.getList().get(counter).toString();
        System.out.println(hor_line + "Okok. I help you add this task: \n" +
                taskMessage + "\n" + "You got " +
                (counter + 1) + " tasks in the list.\n" + hor_line);
        storage.writeData(list.getList());
    }
}
