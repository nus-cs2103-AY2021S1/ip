package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Todo;

public class TodoCommand extends Command {
    public TodoCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    @Override
    public void execute(String command, TaskList list, Storage storage) {
        String hor_line = "____________________________________\n";
        String instructions = command.substring(5);
        int counter = list.getList().size();
        list.addTask(counter, new Todo(false, counter + 1, instructions));
        String taskMessage = list.getList().get(counter).toString();
        System.out.println(hor_line + "Okok. I add for you: \n" +
                taskMessage + "\n" +
                "You got " + (counter + 1) + " tasks in the list.\n" + hor_line);
        storage.writeData(list.getList());
        counter++;
    }
}
