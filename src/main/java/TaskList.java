import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task tsk) {
        taskList.add(tsk);
    }

    public Task deleteTask(int taskNum) throws InvalidTaskNumber {
        if (taskNum < 1 || taskNum > taskList.size()) {
            throw new InvalidTaskNumber();
        } else {
            Task tsk = taskList.get(taskNum - 1);
            taskList.remove(taskNum - 1);
            return tsk;
        }
    }

    public List<Task> getAllTasks() {
        return taskList;
    }

    public Task markDone(int taskNum) throws InvalidTaskNumber {
        if (taskNum < 1 || taskNum > taskList.size()) {
            throw new InvalidTaskNumber();
        } else {
            Task tsk = taskList.get(taskNum - 1);
            tsk.markAsDone();
            return tsk;
        }
    }

    //get tasks of same dates
    public List<Task> getSameDateTasks(String date) {
        List<Task> listOfTasks = new ArrayList<>();
        LocalDate eventDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        for (Task task : taskList) {
            LocalDate dateInTask = task.getDate().orElse(null);
            if (eventDate.equals(dateInTask)) {
                listOfTasks.add(task);
            }
        }
        return listOfTasks;
    }

    public int getNumTasks() {
        return taskList.size();
    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            content.append(taskList.get(i));
            content.append(System.lineSeparator());
        }
        return content.toString();
    }
}
