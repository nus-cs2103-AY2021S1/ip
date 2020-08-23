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

    public Task addTask(String task) throws DukeException {
        Task ret;
        if (task.startsWith("todo")) {
            if (task.length() <= 5) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            ret = new ToDo(task.substring(5));
            tasks.add(ret);
        } else if (task.startsWith("deadline")) {
            if (task.length() <= 9) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String[] taskArr = task.substring(9).split(" /by ");
            try {
                ret = new Deadline(taskArr[0], taskArr[1]);
                tasks.add(ret);
            } catch (IndexOutOfBoundsException ex) {
                throw new DukeException("Invalid description of a deadline.");
            }
        } else if (task.startsWith("event")) {
            if (task.length() <= 6) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            String[] taskArr = task.substring(6).split(" /at ");
            try {
                ret = new Event(taskArr[0], taskArr[1]);
                tasks.add(ret);
            } catch (IndexOutOfBoundsException ex) {
                throw new DukeException("Invalid description of an event.");
            }
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return ret;
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
