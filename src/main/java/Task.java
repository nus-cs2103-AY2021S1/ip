public class Task {
    private String name;
    private Boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return this.name;
    }

    public String getCheckBox() {
        if (this.done) {
            return ("[✓]");
        } else {
            return("[✗]");
        }
    }

    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return (getCheckBox() + " " + this.getName());
    }
}
