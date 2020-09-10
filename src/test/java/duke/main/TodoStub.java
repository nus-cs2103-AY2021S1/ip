package duke.main;

import duke.task.Todo;

public class TodoStub extends Todo {

    public TodoStub() {
        super("no description");
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public String[] getInfo() {
        return new String[] {"T", "wash dishes"};
    }
}
