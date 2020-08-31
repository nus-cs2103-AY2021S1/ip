import java.time.LocalDate;

public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public static ToDos load(String taskDetails) {
        String[] splitTaskDetails = taskDetails.split("\\|",3);
        for (int i = 0; i < splitTaskDetails.length; i++) {
            splitTaskDetails[i] = splitTaskDetails[i].strip();
        }
        ToDos todo = new ToDos(splitTaskDetails[2].strip());
        if (splitTaskDetails[1].equals("true")) {
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