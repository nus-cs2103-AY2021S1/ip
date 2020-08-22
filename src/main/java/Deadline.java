package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }
    public Deadline(String description, boolean isDone, LocalDateTime deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String datePattern = "dd/MM/yyyy HH:mm";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
        String date = this.deadline.format(dateFormatter);
        return "[D]" +"[" + this.getStatusIcon()+"] " + this.description + "(by:" + date +")";
    }

    @Override
    public String writeToFile() {
        String result = "D # ";
        if(isDone) {
            result+="1 # ";
        } else {
            result+="0 # ";
        }
        result+=description;
        String datePattern = "dd/MM/yyyy HH:mm";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
        result+=" # "+deadline.format(dateFormatter);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Deadline) {
            Deadline cur = (Deadline) o;
            if (cur.isDone == this.isDone && cur.description.equals(this.description)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
