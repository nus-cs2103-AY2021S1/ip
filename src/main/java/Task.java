public class Task {
    protected String description;
    protected boolean isDone;
    protected static int numberOfTasks = 0;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    public Task(String description, int isDone) {
        this.description = description;
        if (isDone == 0) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
        numberOfTasks++;
    }

    public static void reduceOneTasks() {
        numberOfTasks--;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public Task markAsDone() {
        this.isDone = true;
        System.out.println("Nice!(^∇^) I've marked this task as done:\n[\u2713] " + this.description );
        return this;
    }

    @Override
    public String toString() {
        return "Got it. I've added this task:\n" +
                "[\u2718] " + this.description;
    }

    public String deleteMessage() {
        return "Got it. I've deleted this task:\n" +
                "[\u2718] " + this.description;
    }

    public String data() {
        return  " | " + (isDone
                ? "1"
                : "0" ) + " | " + description;
    }
}