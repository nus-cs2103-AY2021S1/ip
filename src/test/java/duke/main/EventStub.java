package duke.main;

import duke.task.Event;

public class EventStub extends Event {

    public EventStub() {
        super("no description", "2000-05-05");
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public String[] getInfo() {
        return new String[] {"E", "read manga", "2021-01-03"};
    }
}
