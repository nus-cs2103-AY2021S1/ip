public class Command {
    protected String name;
    protected Integer index;
    protected Task task;
    protected String message;

    public Command(String name) {
        this.name = name;
        this.index = null;
        this.task = null;
        this.message = null;
    }

    public Command(String name, int index) {
        this.name = name;
        this.index = index;
        this.task = null;
        this.message = null;
    }

    public Command(String name, Task task) {
        this.name = name;
        this.index = null;
        this.task = task;
        this.message = null;
    }

    public Command(String name, String message) {
        this.name = name;
        this.index = null;
        this.task = null;
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("Command: %s %s %s %s", name, index, task, message);
    }
}
