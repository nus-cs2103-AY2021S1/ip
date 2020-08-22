package main.java;

public class Event extends Task {
    private String duration;

    Event(String title, String duration) {
        super(title);
        this.duration = duration;
    }

    Event(String title, boolean isDone, String duration) {
        super(title, isDone);
        this.duration = duration;
    }

    static Event of(String command) throws DukeException {
        if (command.length() <= 6) {
            throw new DukeException("Event cannot be empty.");
        }
        String[] split = command.substring(6).split("\\s+/at\\s+");
        if (split.length != 2) {
            throw new DukeException("Wrong format.");
        }
        return new Event(split[0], split[1]);
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.duration + ")";
    }

    public String print() {
        return "E | " + super.print() + " | " + this.duration;
    }
}
