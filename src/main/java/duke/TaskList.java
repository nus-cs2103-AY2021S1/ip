package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private final ArrayList<Task> taskList;
    
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    
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
    
    // side effect: completes task + returns string for completed task
    public String completeTask(int taskID) throws DukeException {
        verifyTaskValidity(taskID);
        taskList.set(taskID - 1, taskList.get(taskID - 1).complete());
        return taskList.get(taskID - 1).toString();
    }
    
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
