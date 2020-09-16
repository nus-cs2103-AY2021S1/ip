package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TaskList class is used to store different Task objects and
 * allows the user to modify the list of tasks through
 * this class.
 */
public class TaskList {

    private List<Task> list;
    private int activeTasks = 0;
    private int completedTasks = 0;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    private TaskList(List<Task> list) {
        this.list = list;
        for (Task t : this.list) {
            if (t.isDone()) {
                this.completedTasks += 1;
            } else {
                this.activeTasks += 1;
            }
        }
    }

    public List<Task> getList() {
        return this.list;
    }

    /**
     * Gets the task at the index of the list.
     * @param index The index of the task required.
     * @return The task if index is valid, else null.
     */
    public Task getTask(int index) {
        if (index >= this.list.size() || index < 0) {
            return null;
        }
        return this.list.get(index);
    }

    /**
     * Adds a Task to the list.
     *
     * @param item Task object to be added on to the list.
     * @return String result that describes the item added
     * if successful.
     */
    public String addItem(Task item) {
        if (item == null) {
            return "Task is null! Nothing was added.";
        }
        this.list.add(item);
        if (item.isDone()) {
            this.completedTasks += 1;
        } else {
            this.activeTasks += 1;
        }
        return "added: " + item + "\nActive Tasks: "
                + this.activeTasks + "\nCompleted Tasks: " + this.completedTasks;
    }

    /**
     * Deletes a Task from the list.
     *
     * @param index The index of the Task object to be deleted.
     * @return String result that describes the deleted Task
     * if successful.
     */
    public String deleteItem(int index) {
        if (index >= this.list.size() || index < 0) {
            return "Please choose a valid task to delete";
        }
        Task deletedTask = this.list.remove(index);
        if (deletedTask.isDone()) {
            this.completedTasks -= 1;
        } else {
            this.activeTasks -= 1;
        }
        return "Noted. I have deleted the following task: \n"
                + deletedTask + "\nActive Tasks: "
                + this.activeTasks + "\nCompleted Tasks: " + this.completedTasks;
    }

    /**
     * Adds a note to the specified Task.
     *
     * @param index The index of the specific task to add the note to.
     * @param noteContent The content of the note.
     * @return String result that describes the task and the note.
     */
    public String editNote(int index, String noteContent) {
        if (index >= this.list.size() || index < 0) {
            return "Please choose a valid task to add the note to";
        }
        Task taskToAdd = this.list.get(index);
        return taskToAdd.addNote(noteContent);
    }

    /**
     * Marks a Task as completed.
     *
     * @param index The index of the Task object to be marked as done.
     * @return String result that describes the completed Task
     * if successful.
     */
    public String markDone(int index) {
        if (index >= this.list.size() || index < 0) {
            return "Please choose a valid task to mark as done";
        }
        if (!this.list.get(index).isDone()) {
            this.activeTasks -= 1;
            this.completedTasks += 1;
            return this.list.get(index).markDone() + "\nActive Tasks: "
                    + this.activeTasks + "\nCompleted Tasks: " + this.completedTasks;
        }
        return "The task is already done!";
    }

    /**
     * Marks a Task as not completed.
     *
     * @param index The index of the Task object to be marked as not done.
     * @return String result that describes the uncompleted Task if successful.
     */
    public String revertDone(int index) {
        if (index >= this.list.size() || index < 0) {
            return "Please choose a valid task to mark as not done";
        }
        if (this.list.get(index).isDone()) {
            this.activeTasks += 1;
            this.completedTasks -= 1;
            return this.list.get(index).revertDone() + "\nActive Tasks: "
                    + this.activeTasks + "\nCompleted Tasks: " + this.completedTasks;
        }
        return "The task is not yet done!";
    }

    /**
     * Finds the tasks that have the word parameter in the task description.
     *
     * @param word The word to be searched for.
     * @return TaskList of the results from the search.
     */
    public String findWord(String word) {
        List<Task> filteredList = new ArrayList<>(this.list);
        filteredList = filteredList.stream()
                .filter(task -> task.getDescription().contains(word))
                .collect(Collectors.toList());
        return "Using keyword: " + word + "\n" + new TaskList(filteredList).toString();
    }

    /**
     * Custom toString representation of a TaskList which provides a view of the
     * current Tasks in the list along with additional information such as
     * number of active tasks and completed tasks.
     *
     * @return String representation of current Tasks in the list.
     */
    @Override
    public String toString() {
        if (this.list.size() == 0) {
            return "There are currently no tasks.";
        }
        String result = "Current tasks:\n";
        for (int i = 1; i <= this.list.size(); i++) {
            result += i + ". " + this.list.get(i - 1) + "\n";
        }
        result += "\nActive Tasks: "
                + this.activeTasks + "\nCompleted Tasks: " + this.completedTasks;
        return result;
    }
}
