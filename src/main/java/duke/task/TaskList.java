package duke.task;

import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Implements the list of tasks.
 * 
 * @author Audrey Felicio Anwar
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;
    private Ui ui;
    
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
        this.ui = new Ui();
    }
    
    public TaskList(ArrayList<Task> savedTasks) {
        this.tasks = savedTasks;
        this.taskCount = savedTasks.size();
        this.ui = new Ui();
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
     */
    public void addTask(Task task) {
        tasks.add(task);
        taskCount += 1;
        ui.printMessage(" Your task has been recorded.",
                "   " + task,
                " You have " + taskCount + " tasks currently.");
    }

    /**
     * Deletes a specific task.
     * 
     * @param index Position of the task to be deleted.
     */
    public void deleteTask(int index) {
        if (index < 1 || taskCount < index) {
            ui.printMessage(" Sorry I cannot find your specified task :(");
        } else {
            Task removed = tasks.get(index - 1);
            tasks.remove(index - 1);
            taskCount -= 1;
            ui.printMessage(" Okay, I will remove this task for you",
                    "   " + removed,
                    " You have " + taskCount + " tasks currently.");
        }
    }

    /**
     * Lists all tasks in task list.
     */
    public void listTasks() {
        if (taskCount == 0) {
            ui.printMessage(" You've got no tasks now.",
                    " If you want to get busy add more task.",
                    " I'll remember them for you :)");
        } else {
            ui.printMessage(" Let me list out all your tasks...");
            for (int i = 0; i < taskCount; i++) {
                ui.printMessage(" " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    /**
     * Marks a specific task as done.
     * 
     * @param index Position of the task to be completed.
     */
    public void markAsDone(int index) {
        if (index < 1 || taskCount < index) {
            ui.printMessage(" Sorry I cannot find your specified task :(");
        } else {
            tasks.get(index - 1).completeTask();
            ui.printMessage(" Congratulations for finishing this task!",
                    " Let me mark this as done for you.",
                    "   " + tasks.get(index - 1));
        }
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
            String description = currentTask.getDescription();
            for (int j = 0; j <= description.length() - wordLength; j++) {
                String currentSearch = description.substring(j, j + wordLength);
                if (currentSearch.equals(keyword)) {
                    matchingTasks.add(currentTask);
                }
            }
        }
        return matchingTasks;
    }
}
