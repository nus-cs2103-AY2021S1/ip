public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
<<<<<<< HEAD
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        //return (isDone ? "tick" : "cross");
=======
        return (isDone ? "O" : "X"); //return tick or X symbols
>>>>>>> branch-level-8
    }

    public void markAsDone() {
        isDone = !isDone;
    }

    public void printTask(int i) {
        String output = "     " + i + "." + this.toString();
        System.out.println(output);
    }

    public void printTask() {
        String output = "     " + this.toString();
        System.out.println(output);
    }

    public String getString() {
        return "     " + this.toString();
    }

    public String diskFormat() {
        return this.getStatusIcon() + " | " + this.description;
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

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}