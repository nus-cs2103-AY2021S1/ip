import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    public int size() {
        return this.list.size();
    }

    public Task addTask(String type, String input) {
        Task task;
        if (type.equals("todo")) {
            task = new Todo(input);
        } else if (type.equals("deadline")) {
            String[] arr = input.split("/by");
            task = new Deadline(arr[0].trim(), arr[1].trim());
        } else { // event type
            String[] arr = input.split("/at");
            task = new Event(arr[0].trim(), arr[1].trim());
        }
        this.list.add(task);
        return task;
    }

    public Task markAsDone(int i) {
        return this.list.get(i).markAsDone();
    }

    @Override
    public String toString() {
        String listString = "";
        for (int i = 0; i < this.size(); i++) {
            int taskNum = i + 1;
            listString += taskNum + ".";
            listString += this.list.get(i).toString();
            if (i < this.size() - 1) {
                listString += '\n';
            }
        }
        return listString;
    }
}
