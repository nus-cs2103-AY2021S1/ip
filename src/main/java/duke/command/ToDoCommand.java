package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.ToDo;

public class ToDoCommand extends Command {

    /**
     * Constructs a ToDoCommand object with the input specified
     * @param input User's input that is processed by the ToDoCommand Object
     */
    public ToDoCommand(String input) {
        super(input);
    }

    /**
     * Invokes the ToDoCommand object to process the User's request based on User's input
     * @param tasks TaskList that contains an ArrayList of Task
     * @param ui Ui object that interacts with User
     * @param storage Storage object that reads from/write to specified filePath
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        Task task = new ToDo(input);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

        String message = "";
        message += "Got it. I've added this task:\n";
        message += task.toString() + "\n";
        message += "Now you have " + tasks.size() + " tasks in the list.\n";
        ui.setMessage(message);
        tasks.sort();
    }

    /**
     * Returns false as ToDoCommand is not for termination
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
