public class Task {

    // task description
    private final String desc;

    public Task(String desc) {
        this.desc = desc;
    }

    public String getDescription() {
        return desc;
    }

    @Override
    public String toString() {
        return getDescription();
    }

}
