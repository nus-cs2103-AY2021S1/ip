package duke.command;

import duke.*;

/**
 * Represents command to show list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Class constructor.
     * @param command String parsed by Parser object.
     */
    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        int counter = 0;
        ui.printList(tasks);
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(i + TASK_INDEX + "." + tasks.getTask(i));
            counter++;
        }
        if (counter == 0) {
            ui.printNoTasks();
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
