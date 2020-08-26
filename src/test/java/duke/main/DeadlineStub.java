package duke.main;

import duke.task.Deadline;

public class DeadlineStub extends Deadline {

    public DeadlineStub() {
        super("no description", "2000-05-05");
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public String[] getInfo() {
        return new String[] {"D", "finish assignment", "2021-02-03"};
    }
}
