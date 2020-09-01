package duke.command;

import java.time.DateTimeException;
import java.time.LocalDate;

import duke.TaskList;
import duke.ui.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;


/**
 * Represents command to add all forms of tasks into list.
 */
public class AddCommand extends Command {
    /**
     * Constructs the command with the type of task being added.
     *
     * @param text type of task being added.
     */
    public AddCommand(String text) {
        this.commandText = text;
    }

    /**
     * Adds the task.
     *
     * @param taskDetails details of task given by user.
     * @param taskList    current list of tasks.
     */
    @Override
    public void execute(String taskDetails, TaskList taskList) {
        try {
            if (this.commandText.equals(Types.TODO.text)) {
                Task toAdd = new Task(taskDetails);
                taskList.addTask(toAdd);
            } else {
                String[] partsOfTask = taskDetails.split("/");
                String description = partsOfTask[0];
                String date = partsOfTask[1].strip();

                int day = Integer.parseInt(date.substring(0, 2));
                int month = Integer.parseInt(date.substring(2, 4));
                int year = Integer.parseInt(date.substring(4, 8));
                LocalDate actualDate = LocalDate.of(year, month, day);

                if (this.commandText.equals(Types.DEADLINE.text)) {
                    Task toAdd = new Deadline(description.strip(), actualDate);
                    taskList.addTask(toAdd);

                } else if (this.commandText.equals(Types.EVENT.text)) {
                    Task toAdd = new Event(description.strip(), actualDate);
                    taskList.addTask(toAdd);

                }
            }
        } catch (DateTimeException e) {
            System.out.println("Please key in again with a valid date\n" + Ui.LINE);
        }
    }

    enum Types {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");
        private String text;

        Types(String text) {
            this.text = text;
        }
    }
}
