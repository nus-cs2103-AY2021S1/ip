import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void markAsDone(short id) throws IndexOutOfBoundsException {
        list.get(id - 1).markAsDone();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public Task getTaskAtIndex(short id) {
        return list.get(id - 1);
    }

    public Task getLastTask() {
        return list.get(list.size() - 1);
    }

    public short size() {
        return (short) list.size();
    }

    public void add(TaskType taskType, String name, LocalDate date, LocalTime time) throws BlankTaskException {
        switch (taskType) {
        case D:
            list.add(new Deadline(name, date, time));
            break;
        case E:
            list.add(new Event(name, date, time));
            break;
        default:
            list.add(new ToDo(name));
            break;
        }
    }

    public void delete(short id) throws IndexOutOfBoundsException {
        list.remove(id - 1);
    }
}
