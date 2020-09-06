package duke.parser;

import duke.DukeException;
import duke.task.TaskList;

public class TaskIndexStringChecker extends StringChecker {

    public TaskIndexStringChecker(String[] userInput) {
        super(userInput);
    }

    public int checkTask(TaskList tasks) throws DukeException {
        checkTaskIndexPresent();
        return checkTaskIndexCorrect(tasks);
    }

    private void checkTaskIndexPresent() throws DukeException {
        if (super.checkEmptyString(getStringArray(),2)) {
            throw new DukeException("Please enter a task number for your command!");
        };
    }

    private int checkTaskIndexCorrect(TaskList tasks) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(getStringArray()[1]);
            if (taskNumber <= 0 || !tasks.isTaskPresent(taskNumber - 1)) {
                throw new DukeException("Oops, enter a task number that already exists in the list "
                        + "(starting from 1 to " + tasks.totalTask() + ").");
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new DukeException("Task Number specified must be an Integer!");
        }
    }
}
