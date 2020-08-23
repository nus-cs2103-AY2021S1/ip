import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public String listContents() throws DukeException {
        if (list.size() > 0) {
            String text = "Here is your list:";
            for (int i = 1; i <= list.size(); i++) {
                text = text + "\n" + i + "." + list.get(i - 1);
            }
            return text;
        } else {
            throw(DukeException.emptyList());
        }
    }

    public void add(String task, String time, TaskType type) {
        switch (type) {
        case DEADLINE:
            Task nextDeadline = new Deadline(task, time);
            list.add(nextDeadline);
            break;
        case EVENT:
            Task nextEvent = new Event(task, time);
            list.add(nextEvent);
            break;
        case TODO:
            Task nextToDo = new ToDo(task);
            list.add(nextToDo);
            break;
        }
    }

    public String delete(int index) {
        Task task = list.get(index);
        list.remove(index);
        return task.toString();
    }

    public String done(int index) {
        list.get(index).setDone();
        return list.get(index).toString();
    }

    public String extractListData() {
        int len = list.size();
        if (len > 0) {
            String text = list.get(0).toCommand();
            for (int i = 1; i < len; i++) {
                Task t = list.get(i);
                text = text + "\n" + t.toCommand();
            }
            return text;
        } else {
            return "";
        }
    }
}
