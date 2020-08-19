public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
//        return (isDone ? "tick" : "cross");
    }

    public void markAsDone() {
        isDone = !isDone;
    }

    public void printTask(int i) {
        String output = "     " + i + "." + "[" + this.getStatusIcon() + "]" + " " + this.description;
        System.out.println(output);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof  Task)) {
            return false;
        } else {
            Task t = (Task) obj;

            return t.description.equals(this.description);
        }
    }
}