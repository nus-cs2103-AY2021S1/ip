import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class to represent all the tasks.
 * @author vanGoghhh
 */

public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructor for tasklist.
     *
     * @param taskList arraylist containing all the tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Mark a task as completed.
     *
     * @param completedTask task to be completed.
     */
    protected void markTaskDone(Task completedTask) {
        completedTask.markAsDone();
        int indexOfTask = this.taskList.indexOf(completedTask);
        this.taskList.get(indexOfTask).markAsDone();

    }

    /**
     * Adds a task into the tasklist.
     *
     * @param task the task to be added.
     */
    protected void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a task from the tasklist.
     *
     * @param task the task to be deleted.
     */
    protected void deleteTask(Task task) {
        this.taskList.remove(task);
    }

    /**
     * .
     * Gets the tasklist containing all the tasks.
     *
     * @return arraylist containing all the tasks.
     */
    protected ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns number of uncompleted tasks in the tasklist.
     *
     * @return number of uncompleted tasks.
     */
    protected int checkTasksLeft() {
        int index = 0;
        for (Task task : this.taskList) {
            if (!task.getStatus()) {
                index++;
            }
        }
        return index;
    }

    /**
     * Search and return tasks using keyword.
     *
     * @param keyWord word used to search.
     * @return Tasks with description matching the keyword.
     */
    protected ArrayList<Task> findTask(String keyWord) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        foundTasks = this.taskList.stream().filter(task -> task.description.equals(keyWord))
                                            .collect(Collectors.toCollection(ArrayList::new));
        return foundTasks;
    }

    protected ArrayList<Task> filterTask() {
        return this.taskList.stream().filter(task -> task instanceof Deadline ||
                task instanceof Event).collect(Collectors.toCollection(ArrayList::new));
    }

    protected ArrayList<Task> sortTask() {
        ArrayList<Task> sortedEventAndDeadline = this.taskList.stream()
                .filter(task -> task instanceof Deadline
                        || task instanceof Event)
                .sorted((task1, task2) -> task1.getTaskDeadline()
                        .compareTo(task2.getTaskDeadline()))
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Task> onlyTodo = this.taskList.stream()
                .filter(task -> task instanceof Todo)
                .collect(Collectors.toCollection(ArrayList::new));
        sortedEventAndDeadline.addAll(onlyTodo);
        return sortedEventAndDeadline;

    }
    protected Task getTaskToUpdate(int taskNumber) {
        return this.taskList.get(taskNumber - 1);
    }

    protected Task getUpdatedTask(String detailToUpdate) throws DukeException {
        try {
            Task updatingTask = null;
            for (Task task: taskList) {
                if (task.isBeingUpdated) {
                    updatingTask = task;
                }
            }
            String[] updateDetails = detailToUpdate.split(" ");
            if (updateDetails[0].equals("date")) {
                LocalDate newDate = LocalDate.parse(updateDetails[1]);
                if (updatingTask instanceof Deadline) {
                    return new Deadline(updatingTask.description, newDate);
                } else {
                    return new Event(updatingTask.description, newDate);
                }
            } else if (updateDetails[0].equals("desc")) {
                String newDescription = updateDetails[1];
                if (updatingTask instanceof Deadline) {
                    return new Deadline(newDescription, ((Deadline) updatingTask).getTaskDeadline() );
                } else if (updatingTask instanceof Event){
                    return new Event(newDescription, ((Event) updatingTask).getTaskDeadline());
                } else {
                    return new Todo(newDescription);
                }
            } else {
                throw new InvalidCommandException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        }
    }

    protected void updateTask(Task updatedTask) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).isBeingUpdated) {
                taskList.set(i, updatedTask);
            }
        }
    }
}
