package duke.task;

import java.util.ArrayList;

import duke.exception.InvalidRequestException;


/**
 * Represents a TaskList and consists of information and methods related to the list of tasks.
 */
public class TaskList {

    private ArrayList<Task> listOfTasks;

    /**
     * Constructs a TaskList.
     *
     * @param listOfTasks The list of tasks.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param newTask The task to be added.
     */
    public void addTask(Task newTask) throws InvalidRequestException {
        int numberOfTasks = listOfTasks.size();
        boolean isDuplicate = false;
        for (int i = 0; i < numberOfTasks; i++) {
            if (newTask.writeToFile().equals(listOfTasks.get(i).writeToFile())) {
                isDuplicate = true;
                break;
            }
        }
        if (!isDuplicate) {
            listOfTasks.add(newTask);
            assert listOfTasks.size() > numberOfTasks : "Addition failed";
        } else {
            throw new InvalidRequestException("Replicates detected."
                    + " This task has already been marked as done!");
        }
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param index The index of the task to be deleted.
     * @throws InvalidRequestException If the command is invalid.
     */
    public void deleteTask(int index) throws InvalidRequestException {
        int numberOfTasks = listOfTasks.size();
        if (listOfTasks.size() < index || index < 0) {
            throw new InvalidRequestException("You have entered an invalid duke.task "
                    + "number! Please try again.");
        }
        listOfTasks.remove(index - 1);
        assert listOfTasks.size() < numberOfTasks : "Deletion failed";
    }

    /**
     * Sets the task in the list to be done.
     *
     * @param index The index of the task to be set as done.
     * @throws InvalidRequestException If the command is invalid.
     */
    public void setAsDone(int index) throws InvalidRequestException {
        if (listOfTasks.size() < index || index < 0) {
            throw new InvalidRequestException("You have entered an invalid task "
                    + "number! Please try again.");
        }
        Task task = this.listOfTasks.get(index - 1);
        task.setTaskToBeDone();
        assert task.getStatusSymbol().equals("\u2713") : "Failed to mark task as done";
    }

    /**
     * Return the particular task from the list by its index.
     *
     * @param index The index of the task.
     * @return The task with the index specified in the arraylist.
     * @throws InvalidRequestException If the command is invalid.
     */
    public Task getTask(int index) throws InvalidRequestException {
        if (listOfTasks.size() < index || index < 0) {
            throw new InvalidRequestException("You have entered an invalid task "
                    + "number! Please try again.");
        }
        return this.listOfTasks.get(index - 1);
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

}
