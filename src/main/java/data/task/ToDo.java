package data.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String toTxtFormat() {
        return "T | " + super.toTxtFormat();
    }

    public static ToDo parse(String[] txtArray) {
        ToDo toDo = new ToDo(txtArray[2].trim());
        if (txtArray[1].trim().equals("1")) {
            toDo.markAsDone();
        }
        return toDo;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
