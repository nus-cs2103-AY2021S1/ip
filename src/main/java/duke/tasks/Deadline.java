package duke.tasks;

import java.util.Optional;

public class Deadline extends Task{
    private String deadline;
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (" + deadline + ")";
    }

    @Override
    public Optional<String> getTime() {
        return Optional.of(deadline);
    }
}
