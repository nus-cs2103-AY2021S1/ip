package duke.task;

public class ConcreteTaskStub extends Task {

    ConcreteTaskStub(String description) {
        super(description);
    }

    public String encode() {
        return "";
    }

    public boolean match(String test) {
        return true;
    }
}
