package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public static ToDo load(String toDoDetails) {
        String[] splitToDoDetails = toDoDetails.split("\\|", 3);
        for (int i = 0; i < splitToDoDetails.length; i++) {
            splitToDoDetails[i] = splitToDoDetails[i].strip();
        }
        ToDo todo = new ToDo(splitToDoDetails[2].strip());
        if (splitToDoDetails[1].equals("true")) {
            todo.markAsDone();
        }
        return todo;
    }

    @Override
    public String saveAs() {
        return "T | " + super.saveAs();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}