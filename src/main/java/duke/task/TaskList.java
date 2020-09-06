package duke.task;

import duke.command.Parser;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasklist;

    private TaskList() {
        this.tasklist = new ArrayList<>();
    }

//    /**
//     *
//     * @param taskHistory
//     * @return
//     */
//    public String loadTasks(ArrayList<String> taskHistory) {
//        for (int i = 0; i < taskHistory.size(); i++) {
//
//            Parser.process()
//            tasklist.add(taskHistory.get(i));
//        }
//        return "Total number of Task loaded from previous file: " + taskHistory.size();
//    }

    /**
     * Adds a task to the tasklist
     * @param task The task as instantiated from Parser to a Task object
     * @return String representation of the Task object that is added
     */
    public String addTask(Task task) {
        tasklist.add(task);
        return task.toString();
    }

    /**
     * Deletes a task from the tasklist
     * @param taskNumber The index of the task in the tasklist
     * @return String representation of the Task object that is deleted
     */
    public String deleteTask(int taskNumber) {
        int i = taskNumber - 1;
        Task removedTask = this.tasklist.get(i);
        this.tasklist.remove(i);
        return removedTask.toString();
    }

    /**
     * Updates a task in the tasklist to mark it as done
     * @param taskNumber The index of the task in the tasklist
     * @return String representation of the Task object that is updated
     */
    public String updateTask(int taskNumber) {
        int i = taskNumber - 1;
        Task updatedTask = this.tasklist.get(i);
        updatedTask.setDone();
        return updatedTask.toString();
    }

    /**
     * Returns the current size of the task list
     * @return Integer value of the size of task list
     */
    public int getListSize() {
        return this.tasklist.size();
    }

    /**
     * Returns the task that is indicated by its index in the Tasklist
     * @param index The index of the task in the taskList
     * @return Task object
     */
    public Task getTask(int index) {
        return this.tasklist.get(index);
    }

    /**
     * Creates a tasklist object
     * @return TaskList Object
     */
    public static TaskList createTaskList() {
        return new TaskList();
    }

}
