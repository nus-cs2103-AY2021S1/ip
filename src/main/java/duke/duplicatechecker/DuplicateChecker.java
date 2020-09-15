package duke.duplicatechecker;

import java.time.LocalDate;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Checks for duplicate Task within the existing TaskList.
 */
public class DuplicateChecker {

    private String description;
    private LocalDate date;
    private TaskList taskList;
    private Task.TaskType taskType;

    /**
     * Constructor.
     *
     * @param description Task description.
     * @param date Date associated with the Task.
     * @param taskList Current TaskList.
     * @param taskType Type of task.
     */
    public DuplicateChecker(String description, LocalDate date, TaskList taskList, Task.TaskType taskType) {
        this.description = description.toLowerCase();
        this.date = date;
        this.taskList = taskList;
        this.taskType = taskType;
    }

    /**
     * Constructor.
     *
     * @param description task description.
     * @param taskList existing tasklist.
     * @param taskType type of task.
     */
    public DuplicateChecker(String description, TaskList taskList, Task.TaskType taskType) {
        this.description = description.toLowerCase();
        this.taskList = taskList;
        this.date = null;
        this.taskType = taskType;
    }

    /**
     * Checks for duplicate description and date.
     *
     * @return boolean.
     */
    public boolean checkForDuplicates() {
        boolean hasDuplicate = false;
        for (int i = 0; i < taskList.listSize(); i++) {
            Task task = taskList.getTaskAtIndex(i);
            if (taskType.equals(task.getTaskType())) {
                String taskDescription = task.getDescription().toLowerCase();
                LocalDate localDate = task.getLocalDate();
                if (checkForDuplicateDescription(taskDescription) && checkForDuplicateDate(localDate)) {
                    hasDuplicate = true;
                }
            }
        }
        return hasDuplicate;
    }

    /**
     * @param localDate Date associated with the task.
     * @return boolean.
     */
    public boolean checkForDuplicateDate(LocalDate localDate) {
        return localDate == null || localDate.equals(date);
    }

    /**
     * @param taskDescription Task's description.
     * @return boolean.
     */
    public boolean checkForDuplicateDescription(String taskDescription) {
        return taskDescription.equals(description);
    }


}
