package duke.command;

import duke.task.Task;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a TasksOnCommand for listing Tasks on a specific Date.
 */
public class TasksOnCommand extends Command{
    private LocalDate date;

    /**
     * Creates an instance of a TasksOnCommand.
     *
     * @param date The Date on which all the listed Tasks are on.
     */
    public TasksOnCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Finds the tasks on the specified Date and then proceeds to list them.
     *
     * @param tasks The TaskList which contains all the tasks.
     * @param ui The Ui which will generate outputs significant to the user.
     * @param storage The Storage which will record any changes into the file in its path.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);

        StringBuilder output = new StringBuilder("\t Here are the tasks on ")
                .append(formattedDate).append(":\n");

        int numbering = 1;
        boolean isFree = true;
        Task task;
        for (int i = 0; i < tasks.size(); i++) {
            task = tasks.get(i);
            if (task.hasSameDate(this.date)) {
                if (isFree) {
                    isFree = false;
                }
                output.append("\t ").append(numbering).append(".").append(task).append("\n");
                numbering++;
            }
        }

        if (isFree) {
            ui.showMessage("\tYay! You have nothing to do on " + formattedDate + "! :-)\n");
        } else {
            ui.showMessage(output.toString());
        }
    }

    /**
     * Indicates whether the program should exit after executing this Command.
     *
     * @return False since the program should still go on after listing all the tasks on a specific Date.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
