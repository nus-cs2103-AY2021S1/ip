package main.java;

public class Deadline extends Task {
    private String deadline;

    Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    Deadline(String title, boolean isDone, String deadline) {
        super(title, isDone);
        this.deadline = deadline;
    }

    static Deadline of(String command) throws DukeException {
        if (command.length() <= 9) {
            throw new DukeException("Deadline cannot be empty.");
        }
        String[] split = command.substring(9).split("\\s+/by\\s+");
        if (split.length != 2) {
            throw new DukeException("Wrong format.");
        }
        return new Deadline(split[0], split[1]);
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    public String print() {
        return "D | " + super.print() + " | " + this.deadline;
    }
}
