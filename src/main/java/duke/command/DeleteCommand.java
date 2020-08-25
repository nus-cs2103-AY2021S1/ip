package duke.command;

import duke.Storage;
import duke.TaskList;

public class DeleteCommand extends Command {
    public DeleteCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    @Override
    public void execute(String command, TaskList list, Storage storage) {
        String hor_line = "____________________________________\n";
        String index = command.substring(command.length() - 1);
        int number = Integer.parseInt(index);
        String taskMessage = list.getList().get(number - 1).toString();
        list.deleteTask(number);
        System.out.println(hor_line + "Task deleted liao: \n" + taskMessage + "\n" +
                "You got " + list.getList().size() + " tasks left. \n" + hor_line);
        storage.writeData(list.getList());
    }
}
