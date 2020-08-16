import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    // sideffect: create & add task + return response
    protected String addEntry(String description) {
        String[] words = description.split(" ");

        if(isToDo(words)) {
            ToDo newToDo = ToDo.createToDo(words);
            this.taskList.add(newToDo);
            return newToDo.toString();
        }

        if (isDeadlineAction(words)) {
            Deadline newDeadline = Deadline.createDeadline(description);
            this.taskList.add(newDeadline);
            return newDeadline.toString();
        }

        if (isEventAction(words)) {
            Event newEvent = Event.createEvent(description);
            this.taskList.add(newEvent);
            return newEvent.toString();
        }
        return "fuck";

    }

    // side effect: completes task + returns string for completed task
    protected String completeTask(int taskID) {
        taskList.set(taskID - 1, taskList.get(taskID - 1).complete());
        return taskList.get(taskID - 1).toString();
    }

    private static boolean isToDo(String[] words) {
        return words[0].equals("todo");
    }
    private static boolean isDeadlineAction(String[] words) {
        return words[0].equals("deadline");
    }
    private static boolean isEventAction(String[] words) {
        return words[0].equals("event");
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
