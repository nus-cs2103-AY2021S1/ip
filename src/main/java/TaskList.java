import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<String> taskStrings) {
        tasks = new ArrayList<>();
        for (String taskString : taskStrings) {
            try {
                this.tasks.add(lineToTask(taskString));
            } catch (DukeException ignored) {
            }
        }
    }

    public Task lineToTask(String taskString) throws DukeException {
        String[] taskLine = taskString.split("~");
        Task task = null;
        try {
            switch (taskLine[0]) {
                case "T":
                    task = new ToDo(taskLine[2]);
                    break;
                case "D":
                    task = new Deadline(taskLine[2], taskLine[3]);
                    break;
                case "E":
                    task = new Event(taskLine[2], taskLine[3]);
                    break;
            }
            if (taskLine[1].equals("1")) {
                assert task != null;
                task.markAsDone();
            }
            return task;
        } catch (DukeException ex) {
            throw ex;
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public String listTasks() {
        String tasks = "";
        Task t;
        for (int i = 0; i < this.tasks.size(); i++) {
            t = this.tasks.get(i);
            tasks += String.format("\t %d.%s%n", i + 1, t);
        }
        return tasks;
    }

    public int size() {
        return tasks.size();
    }
}
