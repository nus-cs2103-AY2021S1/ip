import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.file.Files;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

public class TaskManager {

    private static TaskManager tskManager = null;
    private TaskPrinter taskPrinter;
    private ArrayList<Task> tasks;

    private TaskManager() {
        this.tasks = new ArrayList<>();
        this.taskPrinter = new TaskPrinter();
    }

    public static TaskManager getInstance() {
        if (tskManager == null) {
            tskManager = new TaskManager();
        }
        return tskManager;
    }

    public void loadTasks(Stream<Task> taskStream) {
        taskStream.forEach(this.tasks::add);
    }

    private void updateStore() {
        Path path = Path.of("src","main", "chatbot", "data.txt");
        try {
            Iterator iter = tasks.iterator();
            String dataStr = "";
            while (iter.hasNext()) {
                Task tsk = (Task)iter.next();
                String timestamp = tsk.timestamp.length() == 0 ? "-" : tsk.timestamp;
                String entry = tsk.type + " | " +
                        tsk.getStatus() + " | " +
                        tsk.description + " | " +
                        timestamp  +
                        System.lineSeparator();
                dataStr = dataStr + entry;
            }
            Files.write(path, dataStr.getBytes());
        } catch (IOException e) {
            taskPrinter.display(e.getMessage());
        }
    }

    public void addTask(Task task) {
        if (task == null) {
            return;
        }
        tasks.add(task);
        taskPrinter.display("Got it. I've added this task:\n        " + task +
                String.format("\n    Now you have %d task(s) in the list.", count()));
        updateStore();
    }

    public void removeTask(int index) {
        Task removed = this.tasks.remove(index);
        taskPrinter.display("Alright. I've removed this task:\n        " + removed +
                String.format("\n    Now you have %d task(s) in the list.", count()));
        updateStore();
    }

    public void listAll() {
        taskPrinter.list(this.tasks);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public boolean markAsDone(int index) {
        Task taskDone = getTask(index).markDone();
        if (this.tasks.set(index, taskDone) != null) {
            updateStore();
            taskPrinter.display("Nice! I've marked this task as done:\n    " +
                    "    " + taskDone);
            return true;
        }
        return false;
    }

    public int count() {
        return this.tasks.size();
    }
}
