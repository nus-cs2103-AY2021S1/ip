package nekochan.task;

public class ConcreteTaskStub extends Task {

    ConcreteTaskStub(String description) {
        super(description);
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
    public boolean match(String test) {
        return true;
    }
}
