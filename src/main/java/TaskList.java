import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    TaskList(ArrayList<String[]> taskDetails) {
        this.tasks = new ArrayList<>();
        for (String[] taskArr : taskDetails) {
            String taskType = taskArr[0];
            boolean done = taskArr[1] == "1";

            switch (taskType) {
            case ("T"):
                tasks.add(new Todo(taskArr[2], done));
                break;
            case ("D"):
                tasks.add(new Deadline(taskArr[2], taskArr[3], done));
                break;
            case ("E"):
                tasks.add(new Event(taskArr[2], taskArr[3], done));
                break;
            }
        }
    }

    public Integer getSize() {
        return tasks.size();
    }

    public ArrayList<String> getTasksInfo() {
        ArrayList<String> tasksInfo = new ArrayList<>();
        for (Task task : tasks) {
            tasksInfo.add(task.getInfo());
        }
        return tasksInfo;
    }

    public ArrayList<String> getTasksDescription() {
        ArrayList<String> tasksDescription = new ArrayList<>();
        for (Task task : tasks) {
            tasksDescription.add(task.toString());
        }
        return tasksDescription;
    }

    public Task addTask(String[] newTaskDetails) throws DukeException {
        String taskType = newTaskDetails[0];
        Task task;

        switch (taskType) {
        case ("T"):
            task = new Todo(newTaskDetails[1], false);
            break;
        case ("D"):
            task = new Deadline(newTaskDetails[1],  newTaskDetails[2], false);
            break;
        case("E"):
            task = new Event(newTaskDetails[1],  newTaskDetails[2], false);
            break;
        default:
            throw new DukeException();
        }

        // Add task
        tasks.add(task);
        return task;
    }

    public Task markDone(int index) {
        int itemToMark = index - 1;
        tasks.get(itemToMark).markDone();
        return tasks.get(itemToMark);
    }

    public Task deleteTask(int index) {
        int itemToDelete = index - 1;
        Task removedTask = tasks.remove(itemToDelete);
        return removedTask;
    }
}
