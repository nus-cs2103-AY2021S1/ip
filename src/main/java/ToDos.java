import java.util.ArrayList;

public class ToDos extends Task{
    public ToDos(String taskTitle) {
        super(taskTitle);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
