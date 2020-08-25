package duke.command;

import duke.Deadline;
import duke.Storage;
import duke.TaskList;
import duke.Todo;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    @Override
    public void execute(String command, TaskList list, Storage storage) {
        String hor_line = "____________________________________\n";
        String instructions = command.substring(9);
        String[] arr = instructions.split("/by");
        instructions = arr[0].substring(0, arr[0].length() - 1);
        String date = arr[1].substring(1);
        int counter = list.getList().size();
        list.addTask(counter, new Deadline(false, counter + 1, instructions, date));
        String taskMessage = list.getList().get(counter).toString();
        System.out.println(hor_line + "Okok. I help you add this task: \n" +
                taskMessage + "\n" + "You got " +
                (counter + 1) + " tasks in the list.\n" + hor_line);
        storage.writeData(list.getList());
    }
}
