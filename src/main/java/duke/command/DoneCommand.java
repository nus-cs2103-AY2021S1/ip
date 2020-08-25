package duke.command;

import duke.Storage;
import duke.TaskList;

public class DoneCommand extends Command {
    public DoneCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    @Override
    public void execute(String command, TaskList list, Storage storage) {
        String hor_line = "____________________________________\n";
        String index = command.substring(command.length() - 1);
        int number = Integer.parseInt(index) - 1;
        list.getList().set(number, list.getList().get(number).markDone());
        String taskMessage = list.getList().get(number).toString();
        System.out.println(hor_line + "Swee! Now I will mark this as done: \n" +
                taskMessage + "\n" + hor_line);
        storage.writeData(list.getList());
    }
}
