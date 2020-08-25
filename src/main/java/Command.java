public class Command {
    protected String name;
    protected Integer index;
    protected Task task;
    protected String errorMessage;

    public Command(String name) {
        this.name = name;
        this.index = null;
        this.task = null;
        this.errorMessage = null;
    }

    public Command(String name, int index) {
        this.name = name;
        this.index = index;
        this.task = null;
        this.errorMessage = null;
    }

    public Command(String name, Task task) {
        this.name = name;
        this.index = null;
        this.task = task;
        this.errorMessage = null;
    }

    public Command(String name, String message) {
        this.name = name;
        this.index = null;
        this.task = null;
        this.errorMessage = message;
    }

    @Override
    public String toString() {
        return String.format("Command: %s %s %s %s", name, index, task, errorMessage);
    }
}
