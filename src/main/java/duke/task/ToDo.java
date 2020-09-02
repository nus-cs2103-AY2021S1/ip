package duke.task;

import java.time.LocalDate;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public static ToDo load(String taskDetails) {
        String[] splitTaskDetails = taskDetails.split("\\|", 3);
        for (int i = 0; i < splitTaskDetails.length; i++) {
            splitTaskDetails[i] = splitTaskDetails[i].strip();
        }
        ToDo todo = new ToDo(splitTaskDetails[2].strip());
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