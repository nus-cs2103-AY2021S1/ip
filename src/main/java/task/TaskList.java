package task;

import exceptions.DukeException;
import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        if (tasks == null) {
            this.tasks = new ArrayList<>();
        } else {
            this.tasks = tasks;
        }
    }

    /**
     * Prints the tasks in the list.
     */
    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            String item = i + "." + tasks.get(i - 1).toString();
            System.out.println(item);
        }
    }

    /**
     * Adds the tasks to the tasklist.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        // inform user item has been added
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }


    /**
     * Deletes the tasks from the tasklist.
     *
     * @param taskNo Task number to be deleted.
     */
    public void deleteTask(int taskNo) throws DukeException {
        // verify task number exists, then delete
        if (taskNo - 1 < tasks.size()) {
            Task toDelete = tasks.get(taskNo - 1);
            tasks.remove(taskNo - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(toDelete.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            // task number does not exist
            throw new DukeException("Sorry, this task does not exist!\n");
        }
    }

    /**
     * Marks the tasks as completed in the tasklist.
     *
     * @param taskNo Task number to be marked as done.
     */
    public void markTaskDone(int taskNo) throws DukeException {
        // verify task number exists, then mark as done
        if (taskNo - 1 < tasks.size()) {
            if (tasks.get(taskNo - 1).isDone) {
                // task marked as done already
                throw new DukeException("Duke.Task.Task is already done! :)\n");
            } else {
                tasks.get(taskNo - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(taskNo - 1).toString());
            }
        } else {
            // task number does not exist
            throw new DukeException("Sorry, this task does not exist!\n");
        }
    }

    /**
     * Finds tasks with given keyword and prints them.
     *
     * @param keyword String that user wants to find task with.
     * @throws DukeException if keyword is empty.
     */
    public void findTask(String keyword) throws DukeException {
        if (keyword == null) {
            throw new DukeException("Please indicate the keyword you wish to use to find tasks with.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++){
                Task task = tasks.get(i);
                boolean isFound = task.description.contains(keyword);

                if (isFound){
                    System.out.println((i + 1) + "." + task.toString());
                }
            }
        }
    }
}
