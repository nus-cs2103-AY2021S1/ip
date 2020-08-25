import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<String> tasks) throws DukeException {
        taskList = new ArrayList<>();
        generateTaskList(tasks);
    }

    public int getSize() {
        return taskList.size();
    }

    public Task addTask(Task task) {
        taskList.add(task);
        return task;
    }

    public String printList() {
        String output = "Here are the tasks in your list:\n";
        int count = taskList.size();
        if (count <= 0) {
            return "There is no task in your list currently. ";
        } else {
            for (int i = 0; i < count; i++) {
                output += (i + 1) + ". " + taskList.get(i) + "\n";
            }
            return output.strip();
        }
    }

    public Task deleteTask(int num) {
        Task deletedTask = taskList.get(num - 1);
        taskList.remove(num - 1);
        return deletedTask;
    }

    public Task markTaskAsDone(int idx) {
        return taskList.get(idx - 1).markAsDone();
    }

    private void generateTaskList(List<String> tasks) throws DukeException {
        for (String task : tasks) {
            if (task.length() < 1) {
                continue;
            }
            String[] taskDetail = task.split(" \\| ");
            String type = taskDetail[0];
            boolean isDone = Integer.parseInt(taskDetail[1]) == 1;
            String description = taskDetail[2];
            switch (type) {
                case "T":
                    taskList.add(new ToDo(isDone, description));
                    break;
                case "E":
                    taskList.add(new Event(isDone, description, taskDetail[3]));
                    break;
                case "D":
                    taskList.add(new Deadline(isDone, description, taskDetail[3]));
                    break;
            }
        }
    }

    public String getTaskListForSave() {
        String allTasks = "";
        for (Task task : taskList) {
            allTasks += task.getTaskDetailsForSave() + "\n";
        }
        return allTasks;
    }



}
