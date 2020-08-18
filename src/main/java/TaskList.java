import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    // side effect: create & add task + return response
    protected String addEntry(String[] parsedOutput) {
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
                return "ugh";
        }
    }

    // side effect: completes task + returns string for completed task
    protected String completeTask(int taskID) throws DukeException {
        if (taskID > taskList.size()) {
            throw new DukeException("invalid done command: task id > list size");
        } else if (taskID < 1) {
            throw new DukeException("invalid done command: task id < 1");
        }
        taskList.set(taskID - 1, taskList.get(taskID - 1).complete());
        return taskList.get(taskID - 1).toString();
    }


    public ArrayList<Task> getAllTasks() {
        return taskList;
    }

    public String getCurrentStatus() {
        int incompleteTasks = 0;
        for(Task t : taskList) {
            if(!t.isComplete()) {
                incompleteTasks++;
            }
        }
        return "Now you have " + incompleteTasks
                + ((incompleteTasks == 1) ? " task" :" tasks")
                + " in the list.";
    }
}
