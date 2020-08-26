import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public static void addNewTodoTask(String taskTitle, ArrayList<Task> tasks) {
        ToDos newTodoTask = new ToDos(taskTitle, false);
        tasks.add(newTodoTask);
        Ui.addTodoTaskMsg(tasks);
    }

    public static void addNewDeadlineTask(String taskTitle, String deadlineTime, ArrayList<Task> tasks) {
        Deadlines newDeadline = new Deadlines(taskTitle, deadlineTime, false);
        tasks.add(newDeadline);
        Ui.addDeadlineTaskMsg(tasks);
    }

    public static void addNewEventTask(String taskTitle, String eventTime, ArrayList<Task> tasks) {
        Events newEvent = new Events(taskTitle, eventTime, false);
        tasks.add(newEvent);
        Ui.addEventTaskMsg(tasks);
    }

    public static void deleteTask(int index, ArrayList<Task> tasks) {
        Task taskToDelete = tasks.get(index - 1);
        tasks.remove(index - 1);
        int newSizeOfTasks = tasks.size();
        Ui.deleteTaskMsg(index, newSizeOfTasks, taskToDelete);
    }

    public static void doneTask(int index, ArrayList<Task> tasks) {
        tasks.get(index - 1).markAsDone();
        Ui.doneTaskMsg(index, tasks);
    }
}
