public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
        typeOfTask = TypeOfTask.TODO;
    }

    @Override
    public String toString() {
        return "[" + typeOfTask + "]" + super.toString();
    }
}
