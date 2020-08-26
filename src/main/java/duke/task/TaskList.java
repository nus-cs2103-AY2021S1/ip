package duke.task;

import duke.exception.InvalidRequestException;

import java.util.ArrayList;

/**
 * TaskList class consists of information and methods related to the list of tasks.
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
    public void addTask(Task newTask) {

        listOfTasks.add(newTask);
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param index The index of the task to be deleted.
     * @throws InvalidRequestException If the command is invalid.
     */
    public void deleteTask(int index) throws InvalidRequestException {
            if (listOfTasks.size() < index || index < 0) {
                throw new InvalidRequestException("You have entered an invalid task "
                        + "number! Please try again.");
            }
            listOfTasks.remove(index - 1);
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
            this.listOfTasks.get(index - 1).setTaskToBeDone();

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
