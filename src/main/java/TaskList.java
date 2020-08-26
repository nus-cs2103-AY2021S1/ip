import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<String> encodedTasks) throws AliceException {
        this.tasks = new ArrayList<>();
        if (encodedTasks != null) {
            for (int i = 0; i < encodedTasks.size(); i++) {
                String currTask = encodedTasks.get(i);
                String[] typeAndDetails = currTask.split(" \\| ", 2);

                if (typeAndDetails.length != 2) {
                    throw new AliceException("Corrupted data");
                }

                switch (typeAndDetails[0]) {
                case "T":
                    tasks.add(Todo.decode(typeAndDetails[1]));
                    break;
                case "D":
                    tasks.add(Deadline.decode(typeAndDetails[1]));
                    break;
                case "E":
                    tasks.add(Event.decode(typeAndDetails[1]));
                    break;
                default:
                    throw new AliceException("Corrupted data");
                }
            }
        }
    }

    public List<String> encode() {
        String[] dataToSave = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            dataToSave[i] = tasks.get(i).encode();
        }
        return Arrays.asList(dataToSave);
    }

    public String getAllTasks() {
        if (tasks.isEmpty()) {
            return null;
        }

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            s.append(i + 1).append(". ").append(tasks.get(i));
            if (i < tasks.size() - 1) {
                s.append("\n");
            }
        }
        return s.toString();
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task markTaskAsDone(int index) throws InvalidCommandException {
        try {
            tasks.get(index).markAsDone();
            return tasks.get(index);
//            return "Great work! I've marked this task as done:\n    " + tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("That task number does not exist.");
        }
    }

    public Task deleteTask(int index) throws InvalidCommandException {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("That task number does not exist.");
        }
    }

    public void clearAllTasks() {
        tasks.clear();
    }

}
