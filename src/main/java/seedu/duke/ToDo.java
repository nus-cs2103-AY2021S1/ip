package seedu.duke;

import java.time.LocalDateTime;

/**
 * Class that represents
 */
public class ToDo extends Task {

    public ToDo(String name, boolean status) {
        super(name, status);
    }

    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public LocalDateTime getTime(){
        return null;
    }

}
