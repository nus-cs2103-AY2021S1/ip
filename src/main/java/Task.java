import java.util.ArrayList;

public class Task {
    ArrayList<Detail> thingsToDo;

    public Task() {
        this.thingsToDo = new ArrayList<>();
    }

    public String add(String desc) {
        this.thingsToDo.add(new Detail(desc));
        return "     added: " + desc + "\n";
    }

    public String printTodoList() {
        String message = "";
        for (int i = 0; i < thingsToDo.size(); i++) {
            String sign = thingsToDo.get(i).done ? "✓" : "✗";
            message = message
                    + "     "
                    + (i + 1)
                    + ". [" + sign + "] "
                    + thingsToDo.get(i).description
                    + "\n";
        }
        return message;
    }

    public String markAsDone(int i) {
        this.thingsToDo.get(i - 1).done = true;
        String message = "       [✓] " + thingsToDo.get(i - 1).description + "\n";
        return message;
    }

    private class Detail {
        boolean done;
        String description;

        public Detail(String description) {
            this.done = false;
            this.description = description;
        }
    }
}
