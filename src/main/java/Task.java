public class Task {

    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String serialize() {
        return (this.isDone ? "1" : "0") + "|" + this.name;
    }

    public static Task parse(String serial) {
        Character type = serial.charAt(0);
        String[] split = serial.split("\\|");
        switch (type) {
        case 'T':
            return Todo.parse(split);
        case 'E':
            return Event.parse(split);
        case 'D':
            return Deadline.parse(split);
        }
        return null;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "✓" : "✘") + "] " + this.name;
    }
}
