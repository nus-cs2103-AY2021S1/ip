public class ToDo extends Task {

    public ToDo(String task_info) {
        super(task_info);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

