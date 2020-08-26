package duke.tasks;

public class ToDo extends Task{
    public ToDo(String task) {
        super(task);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
