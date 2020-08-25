import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private final List<Task> list;

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public int size() {
        return list.size();
    }

    public List<Task> getList() {
        return list;
    }

    public static Task createTask(String type, String description) throws DukeException {
        Task task = new Task("", "");
        String[] split;
        String desc;
        description = description.trim();
        if (description.isEmpty()) {
            throw new DukeException("Description cannot be empty!");
        }
        switch (type) {
        case "todo":
            task = new Todo(description);
            break;
        case "deadline":
            try {
                split = description.split(" /by ");
                desc = split[0];
                String by = split[1];
                task = new Deadline(desc, by);
            } catch (IndexOutOfBoundsException ioobe) {
                throw new DukeException("Invalid " + type + " description!");
            }
            break;
        case "event":
            try {
                split = description.split(" /at ");
                desc = split[0];
                String at = split[1];
                task = new Event(desc, at);
            } catch (IndexOutOfBoundsException ioobe) {
                throw new DukeException("Invalid " + type + " description!");
            }
            break;
        }
        return task;
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task remove(int i) throws DukeException {
        try {
            i--;
            return list.remove(i);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new DukeException("Invalid task number!");
        }
    }

    public Task markDone(int i) throws DukeException {
        try {
            i--;
            Task task = list.get(i);
            if (task.getDone()) {
                throw new DukeException("Task is already done!");
            } else {
                task.markAsDone();
            }
            return task;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new DukeException("Invalid task number!");
        }
    }

    public String getPrintMessage() {
        if (list.isEmpty()) {
            return "Your list is empty!";
        }
        int num = 1;
        StringBuilder msg = new StringBuilder((list.size() > 1)
                ? "Here are the tasks in your list:\n"
                : "Here's your one and only task:\n");
        for (Task task: list) {
            msg.append(String.format("%d. %s", num, task));
            if (num < list.size()) {
                msg.append("\n");
            }
            num++;
            }
        return msg.toString();
    }
}
