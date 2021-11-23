package manager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import tasks.Task;

/**
 * Calculates the statistics comprising of the number of
 * tasks done on time, tasks to be done, overdue tasks
 * and tasks with dates indeterminable.
 */
public class Statistics {

    private TaskList taskList;

    private int hasBeenDone;
    private int toBeDone;
    private int overdue;
    private int indeterminable;

    private LocalDateTime currentTime = LocalDateTime.now();
    private LocalDate currentDate = LocalDate.now();

    /**
     * Creates a Statistics object.
     */
    public Statistics(TaskList taskList) {
        this.taskList = taskList;
        this.hasBeenDone = 0;
        this.toBeDone = 0;
        this.overdue = 0;
        this.indeterminable = 0;
    }

    /**
     * Prints the statistics comprising of the number of
     * tasks done on time, tasks to be done, overdue tasks
     * and tasks with dates indeterminable.
     */
    public void printStatistics() {
        this.calculateStatistics(this.taskList);
        System.out.println("Oohhhhh cannnnn do! Here are your numbers:\n");
        System.out.println("Tasks done: " + this.hasBeenDone);
        System.out.println("Tasks to be done: " + this.toBeDone);
        System.out.println("Tasks that are overdue: " + this.overdue);
        System.out.println("Tasks I have no clue yet to tell the time: " + this.indeterminable);
    }

    private void calculateStatistics(TaskList tasklist) {
        List<Task> tasks = tasklist.getList();
        for (Task task : tasks) {
            if (isDone(task)) {
                this.hasBeenDone++;
            } else if (toBeDone(task)) {
                this.toBeDone++;
            } else if (task.getDate() == null && task.getDateTime() == null) {
                this.indeterminable++;
            } else {
                this.overdue++;
            }
        }
    }

    private boolean isDone(Task task) {
        return task.getStatusIcon().equals("\u2713");
    }

    private boolean toBeDone(Task task) {
        LocalDate date = task.getDate();
        if (date == null) {
            LocalDateTime time = task.getDateTime();
            if (time == null) {
                return false;
            }
            return this.currentTime.isBefore(time) || this.currentTime.isEqual(time);
        }
        return this.currentDate.isBefore(date) || this.currentDate.isEqual(date);
    }
}
