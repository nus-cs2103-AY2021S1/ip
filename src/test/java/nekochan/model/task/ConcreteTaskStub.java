package nekochan.model.task;

public class ConcreteTaskStub extends Task {

    ConcreteTaskStub(String description) {
        super(description);
    }

    ConcreteTaskStub(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public ConcreteTaskStub setCompleted() {
        return new ConcreteTaskStub(description, true);
    }

    @Override
    public Task deepCopy() {
        return new ConcreteTaskStub(description);
    }

    @Override
    public String encode() {
        return "";
    }

    @Override
    boolean isSimilar(Object obj) {
        return true;
    }

    @Override
    boolean isDuplicate(Object obj) {
        return true;
    }

    @Override
    public boolean match(String test) {
        return true;
    }
}
