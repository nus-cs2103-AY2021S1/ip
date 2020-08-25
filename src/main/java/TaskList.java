import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private final ArrayList<Task> taskList;
    
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    
    // side effect: create & add task + return response
    protected String addEntry(String[] parsedOutput) throws DukeException {
        String command = parsedOutput[0];
        switch (command) {
        case "T":
            ToDo newToDo = new ToDo(parsedOutput[1]);
            this.taskList.add(newToDo);
            return newToDo.toString();
        case "D":
            Deadline newDeadline = Deadline.createDeadline(parsedOutput);
            this.taskList.add(newDeadline);
            return newDeadline.toString();
        case "E":
            Event newEvent = Event.createEvent(parsedOutput);
            this.taskList.add(newEvent);
            return newEvent.toString();
        default:
            return "ugh how did we get here";
        }
    }
    
    // side effect: completes task + returns string for completed task
    protected String completeTask(int taskID) throws DukeException {
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
