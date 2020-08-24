import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum TaskType {
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline");

    private String type;

    private TaskType(String type) {
        this.type = type;
    }

    String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}

public class TaskList {
    private List<Task> list;

    TaskList() {
        this.list = new ArrayList<>();
    }

    TaskList(List<Task> tasks) {
        list = tasks;
    }

    void addTask(Task task) {
        list.add(task);
    }

    void markDone(int index) {
        this.list.get(index - 1).markAsDone();
    }

    int size() {
        return list.size();
    }

    Task getTask(int index) {
        return list.get(index - 1);
    }

    int getSize() {
        return list.size();
    }

    List<Task> getTasks() {
        return new ArrayList<>(list);
    }

    String listTasks() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            output.append("\t ").append(i + 1).append(".").append(list.get(i)).append(i == list.size() - 1 ? "" : "\n");
        }
        return output.toString();
    }

    String showTasksOnDate(LocalDate date) {
        Stream<Task> filtered = list.stream().filter(task -> task.getDate().equals(date));
        AtomicInteger i = new AtomicInteger(1);
        StringBuilder output = new StringBuilder();
        filtered.forEach(task -> {
            output.append("\t ").append(i.getAndIncrement()).append(".").append(task).append("\n");
        });
        if (i.intValue() == 1) {
            return "\t No tasks found on " + date.format(DateTimeFormatter.ofPattern("MMMM d yyyy"));
        }
        return output.toString();
    }

    void deleteTask(int index) throws InvalidArgumentException {
        this.list.remove(index - 1);
    }
}
