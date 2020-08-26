package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Holds a Collection of Tasks and has methods to manipulate these Tasks, is serializable as well
 */
public class TaskList implements Serializable {
    private final ArrayList<Task> taskList;
    
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    
    
    /**
     * Creates a task and adds it as an entry to the tasklist.
     *
     * @param parsedInput The parsed output of the Parser object
     * @param commandTag  A string tag to conveniently identify the type of Task entry it should be
     *
     * @return Description of the added Task entry
     *
     * @throws DukeException If the Task can't be created
     */
    // side effect: create & add task + return response
    public String addEntry(String[] parsedInput, String commandTag) throws DukeException {
        switch (commandTag) {
        case "T":
            ToDo newToDo = new ToDo(parsedInput[1]);
            this.taskList.add(newToDo);
            return newToDo.toString();
        case "D":
            Deadline newDeadline = Deadline.createDeadline(parsedInput);
            this.taskList.add(newDeadline);
            return newDeadline.toString();
        case "E":
            Event newEvent = Event.createEvent(parsedInput);
            this.taskList.add(newEvent);
            return newEvent.toString();
        default:
            return "ugh how did we get here";
        }
    }
    
    /**
     * Marks a Task as complete and modifies the TaskList
     *
     * @param taskID Selected Task to mark as complete
     *
     * @return Description of the completed task
     *
     * @throws DukeException If it's an invalid task
     */
    public String completeTask(int taskID) throws DukeException {
        verifyTaskValidity(taskID);
        taskList.set(taskID - 1, taskList.get(taskID - 1).complete());
        return taskList.get(taskID - 1).toString();
    }
    
    /**
     * Deletes the specified Task and modifies the TaskList by decrementing the IDs of the remaining tasks
     *
     * @param taskID Selected Task to delete
     *
     * @return Description of the deleted task
     *
     * @throws DukeException If it's an invalid task
     */
    public String deleteTask(int taskID) throws DukeException {
        verifyTaskValidity(taskID);
        Task toDelete = taskList.get(taskID - 1);
        taskList.remove(toDelete);
        for (int i = taskID - 1; i < taskList.size(); i++) {
            taskList.set(i, taskList.get(i).decrementID());
        }
        Task.decrementTaskCount();
        return toDelete.toString();
    }
    
    /**
     * Verifies the validity of the task to be handled, whether it exists in the TaskList or not
     *
     * @param taskID TaskID to be checked
     *
     * @throws DukeException Invalid task if it's more than the current list size or fewer than 1
     */
    private void verifyTaskValidity(int taskID) throws DukeException {
        if (taskID > taskList.size()) {
            throw new DukeException("invalid task: task id > list size");
        } else if (taskID < 1) {
            throw new DukeException("invalid task: task id < 1");
        }
    }
    
    public ArrayList<Task> getAllTasks() {
        return taskList;
    }
    
    /**
     * Returns the String description of the remaining undone tasks in the list
     *
     * @return String description
     */
    public String getCurrentStatus() {
        int incompleteTasks = 0;
        for (Task t : taskList) {
            if (!t.isComplete()) {
                incompleteTasks++;
            }
        }
        return "Now you have " + incompleteTasks
                + ((incompleteTasks == 1)
                   ? " undone task"
                   : " undone tasks")
                + " in the list.";
    }
    
    
}
