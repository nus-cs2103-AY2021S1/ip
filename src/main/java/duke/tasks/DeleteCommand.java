package duke.tasks;

import java.io.IOException;

/**
 * Represents a Delete Command. This command handles the delete input
 * from users.
 */
public class DeleteCommand extends Command {
    protected String delete;

    /**
     * Constructor that stores the string to be deleted.
     * @param delete string to be removed from list of tasks.
     */
    public DeleteCommand(String delete) {
        this.delete = delete;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws IOException {

        String command = this.delete.replaceAll("[^\\d.]", "");
        int i = Integer.parseInt(command.trim());
        Task deletedTask = tasks.taskLs.get(i - 1);
        int numTask = tasks.taskLs.size();

        String printGui = "";
        printGui = printGui + "Noted. I've removed this task: " + "\n";
        printGui = printGui + deletedTask + "\n";
        printGui = printGui + "Now you have " + numTask + " tasks in the list.";

        //delete task from list of tasks
        tasks.delete(this.delete);

        //write to file
        String s = storage.genList(tasks.getTaskLs());
        storage.writeToFile("data/duke.rtf", s);

        return printGui;
    }
}
