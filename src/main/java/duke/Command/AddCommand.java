package duke.Command;

import duke.Task.Event;
import duke.Task.Deadline;
import duke.Task.Task;
import duke.TaskList;
import duke.Ui;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents command to add all forms of tasks into list.
 */
public class AddCommand extends Command  {

    /**
     * Constructs the command with the type of task being added.
     * @param text type of task being added.
     */
     public AddCommand(String text) {
        this.commandText = text;
    }

    /**
     * Adds the task.
     * @param taskDetails details of task given by user.
     * @param taskList current list of tasks.
     */
     @Override
     public void execute(String taskDetails, TaskList taskList) {
        try {
            if (this.commandText.equals(TYPES.TODO.text)) {
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

                if (this.commandText.equals(TYPES.DEADLINE.text)) {
                    Task toAdd = new Deadline(description.strip(), actualDate);
                    taskList.addTask(toAdd);

                } else if (this.commandText.equals(TYPES.EVENT.text)) {
                    Task toAdd = new Event(description.strip(), actualDate);
                    taskList.addTask(toAdd);

                }
            }
        } catch (DateTimeException e) {
            System.out.println("Please key in again with a valid date\n" + Ui.LINE);
        }
    }

    enum TYPES {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");
        private String text;

        TYPES(String text) {
            this.text = text;
        }
    }
}
