package Duke.TaskList.tasks;

public class ToDos extends Task {
    public ToDos(String string) {
        super(string.substring(5), string, 5);
    }
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public void update(String newTask) {
        fullText = fullText.substring(0, commandIndex) + newTask;
    }
}
