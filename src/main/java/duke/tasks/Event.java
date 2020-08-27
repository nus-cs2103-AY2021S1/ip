package duke.tasks;

import java.util.Optional;

public class Event extends Task {
    private String duration;
    public Event(String task, String duration){
        super(task);
        this.duration = duration;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (" + duration + ")";
    }

    @Override
    public Optional<String> getTime() {
        return Optional.of(duration);
    }
}
