package duke;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
<<<<<<< HEAD
    public String serialize() {
        return String.format("T | %d | %s", getStatusCode(), description);
    }

    @Override
=======
>>>>>>> 7cf06867bc1780a8ba55bc5d6537869fa9988b2d
    public String toString() {
        return "[T]" + super.toString();
    }
}
