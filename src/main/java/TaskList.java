import java.time.LocalDate;
import java.util.List;

public class TaskList {

    private List<Task> store;

    public TaskList(List<Task> lst) {
        this.store = lst;
    }

    public List<Task> getList() {
        return store;
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        int count = 0;
        for (Task task : store) {
            count++;
            System.out.println(String.format("%d. %s", count, task));
        }
    }

    public void doneTask(int num) {
        store.get(num - 1).setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(store.get(num - 1));
    }

    public void deleteTask(int num) {
        Task task = store.get(num - 1);
        store.remove(task);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list", store.size()));
    }

    public void addTaskToList(Task task) {
        store.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list", store.size()));
    }

    public void addTask(String command, String description) throws InvalidEventException, InvalidDeadlineException {
        String splitted[];
        LocalDate date;
        switch (command) {
            case "todo":
                addTaskToList(new Todo(description));
                break;
            case "deadline":
                splitted = description.split(" /by ", 2);
                if (splitted.length == 1) {
                    throw new InvalidDeadlineException();
                }
                date = LocalDate.parse(splitted[1]);
                addTaskToList(new Deadline(splitted[0], date));
                break;
            case "event":
                splitted = description.split(" /at ", 2);
                if (splitted.length == 1) {
                    throw new InvalidEventException();
                }
                date = LocalDate.parse(splitted[1]);
                addTaskToList(new Event(splitted[0], date));
                break;
            default:
                break;
        }

    }
}
