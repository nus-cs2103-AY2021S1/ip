public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public boolean getStatus() {
        return this.done;
    }

    public void setStatus(boolean b) {
        this.done = b;
    }

    @Override
    public String toString() {
        String icon = getStatus() ? "[✓]" : "[✗]";
        return String.format("%s %s", icon, this.description);
    }
}