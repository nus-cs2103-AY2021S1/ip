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

            // todo: find a better way to handle these strings
            StringBuilder newDescription = new StringBuilder();
            for(int i = 1; i < words.length - 1; i++) {
                newDescription.append(words[i]).append(" ");
            }
            newDescription.append(words[words.length - 1]);

            ToDo newToDo = new ToDo(newDescription.toString());
            this.taskList.add(newToDo);
            return newToDo.toString();
        }
        return "fuck";

//        Task newEntry = new Task(description);
//        this.taskList.add(newEntry);
//        return "added: " + description;
    }

    // side effect: completes task + returns string for completed task
    protected String completeTask(int taskID) {
        taskList.set(taskID - 1, taskList.get(taskID - 1).complete());
        return taskList.get(taskID - 1).toString();
    }

    private static boolean isToDo(String[] words) {
        return words[0].equals("todo");
    }

    private static boolean isDeadline(String[] words) {
        return words[0].equals("deadline");
    }
    private static boolean isEvent(String[] words) {
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
