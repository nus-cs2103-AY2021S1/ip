import java.util.ArrayList;

public class Task {
    ArrayList<TaskDetail> thingsToDo;

    public Task() {
        this.thingsToDo = new ArrayList<>();
    }

    // TODO: 17/8/20 print type, done
    public Pair<String, Integer> add(TaskDetail detail) {
        this.thingsToDo.add(detail);
        return new Pair<>(detail.toString() + "\n", thingsToDo.size());
    }

    public String printTodoList() {
        String message = "";
        for (int i = 0; i < thingsToDo.size(); i++) {
            String sign = thingsToDo.get(i).done ? "✓" : "✗";
            message = message
                    + "     "
                    + (i + 1)
                    + "."
                    + thingsToDo.get(i).toString()
                    + "\n";
        }
        return message;
    }

    public String markAsDone(int i) {
        this.thingsToDo.get(i - 1).done = true;
        String message = "       [✓] " + thingsToDo.get(i - 1).description + "\n";
        return message;
    }
}
