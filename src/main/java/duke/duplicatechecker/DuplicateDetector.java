package duke.duplicatechecker;

import java.time.LocalDate;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Checks for duplicate task within the existing tasklist.
 */
public class DuplicateDetector {

    private String description;
    private LocalDate date;
    private TaskList taskList;
    private String taskType;

    /**
     * @param description task description.
     * @param date        date of the task.
     * @param taskList    existing tasklist.
     * @param taskType    type of task.
     */
    public DuplicateDetector(String description, LocalDate date, TaskList taskList, String taskType) {
        this.description = description.toLowerCase();
        this.date = date;
        this.taskList = taskList;
        this.taskType = taskType;
    }

    /**
     * @param description task description.
     * @param taskList    existing tasklist.
     * @param taskType    type of task.
     */
    public DuplicateDetector(String description, TaskList taskList, String taskType) {
        this.description = description.toLowerCase();
        this.taskList = taskList;
        this.date = null;
        this.taskType = taskType;
    }

    /**
     * @return boolean.
     */
    public boolean checkForDuplicates() {
        boolean hasDuplicate = false;
        for (int i = 0; i < taskList.listSize(); i++) {
            Task task = taskList.getTask(i);
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
     * @param localDate date of the task.
     * @return boolean.
     */
    public boolean checkForDuplicateDate(LocalDate localDate) {
        return localDate == null || localDate.equals(date);
    }

    /**
     * @param taskDescription task's description
     * @return boolean.
     */
    public boolean checkForDuplicateDescription(String taskDescription) {
        return taskDescription.equals(description);
    }


}
