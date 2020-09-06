package alice.task.stub;

import alice.task.Todo;

public class TodoStub extends Todo {
    public TodoStub() {
        super("TodoStub");
    }

    @Override
    public String toString() {
        return "TodoStub";
    }
}
