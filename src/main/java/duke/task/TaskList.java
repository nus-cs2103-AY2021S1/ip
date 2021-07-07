package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.ui.Ui;


/**
 * Implements the list of tasks.
 *
 * @author Audrey Felicio Anwar
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;

    /**
     * Initializes an empty TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
    }

    /**
     * Initializes a TaskList object.
     *
     * @param savedTasks Tasks gathered from save file.
     */
    public TaskList(ArrayList<Task> savedTasks) {
        this.tasks = savedTasks;
        this.taskCount = savedTasks.size();
    }

    /**
     * Returns user current tasks.
     *
     * @return User current tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds task to task list.
     *
     * @param task Task to be added.
     * @param ui Ui to get responses.
     */
    public void addTask(Task task, Ui ui) {
        tasks.add(task);
        taskCount += 1;
        ui.accumulateResponses(" Your task has been recorded.",
                "   " + task,
                " You have " + taskCount + " tasks currently.");
    }

    /**
     * Deletes a specific task.
     *
     * @param index Position of the task to be deleted.
     * @param ui Ui to get responses.
     */
    public void deleteTask(int index, Ui ui) {
        if (index < 1 || taskCount < index) {
            ui.accumulateResponses(" Sorry I cannot find your specified task :(");
        } else {
            assert taskCount >= index : " Index must not be greater than task count";
            Task removed = tasks.get(index - 1);
            tasks.remove(index - 1);
            taskCount -= 1;
            ui.accumulateResponses(" Okay, I will remove this task for you",
                    "   " + removed,
                    " You have " + taskCount + " tasks currently.");
        }
    }

    /**
     * Lists all tasks in task list.
     *
     * @param ui Ui to get responses.
     */
    public void listTasks(Ui ui) {
        if (taskCount == 0) {
            ui.accumulateResponses(" You've got no tasks now.",
                    " If you want to get busy add more task.",
                    " I'll remember them for you :)");
        } else {
            assert taskCount > 0 : " Task list must not be empty";
            ui.accumulateResponses(" Let me list out all your tasks...");
            for (int i = 0; i < taskCount; i++) {
                ui.accumulateResponses(" " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    /**
     * Marks a specific task as done.
     *
     * @param index Position of the task to be completed.
     * @param ui Ui to get responses.
     */
    public void markAsDone(int index, Ui ui) {
        if (index < 1 || taskCount < index) {
            ui.accumulateResponses(" Sorry I cannot find your specified task :(");
        } else {
            assert taskCount >= index : " Index must not be greater than task count";
            tasks.get(index - 1).completeTask();
            ui.accumulateResponses(" Congratulations for finishing this task!",
                    " Let me mark this as done for you.",
                    "   " + tasks.get(index - 1));
        }
    }

    private boolean checkIfKeywordExists(String keyword, Task currentTask, int wordLength) {
        String description = currentTask.getDescription();
        for (int j = 0; j <= description.length() - wordLength; j++) {
            String currentSearch = description.substring(j, j + wordLength);
            if (currentSearch.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds all tasks that match the keyword.
     *
     * @param keyword Keyword to be searched.
     * @return Tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        int wordLength = keyword.length();
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < taskCount; i++) {
            Task currentTask = tasks.get(i);
            if (checkIfKeywordExists(keyword, currentTask, wordLength)) {
                matchingTasks.add(currentTask);
            }
        }
        return matchingTasks;
    }

    /**
     * Updates a task with new details.
     *
     * @param index Index of item to be updated.
     * @param type Type of attribute to be updated.
     * @param newDetails Updated details to be saved.
     * @param ui Ui to get responses.
     * @throws DukeException If there is date parsing error.
     */
    public void updateTask(int index, String type, String newDetails, Ui ui) throws DukeException {
        if (index < 1 || taskCount < index) {
            ui.accumulateResponses(" Sorry I cannot find your specified task :(");
            return;
        }
        assert taskCount >= index : " Index must not be greater than task count";
        Task toBeUpdated = tasks.get(index - 1);
        try {
            if (type.equals("description")) {
                toBeUpdated.changeDescription(newDetails);
            } else if (type.equals("time")) {
                toBeUpdated.changeTime(LocalDate.parse(newDetails));
            } else if (type.equals("mark")) {
                int indicator = Integer.parseInt(newDetails);
                if (indicator != 0 && indicator != 1) {
                    throw new DukeException(" Please provide 0 or 1 after mark");
                }
                boolean isDone = indicator == 0 ? false : true;
                toBeUpdated.changeIsDone(isDone);
            } else {
                throw new DukeException(" Attribute to be changed not found :(");
            }
            ui.accumulateResponses(" Your task have been updated",
                    "   " + toBeUpdated);
        } catch (DateTimeParseException error) {
            throw new DukeException(" I cannot recognize the date you put in :(");
        } catch (NumberFormatException error) {
            throw new DukeException(" Please provide 0 or 1 after mark");
        }
    }
}
