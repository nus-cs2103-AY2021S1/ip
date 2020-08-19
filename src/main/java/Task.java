package src.main.java;

public class Task {
    protected String item;
    protected boolean isCompleted;
    protected String sign;

    public Task(String item, String sign) {
        this.item = item;
        this.isCompleted = false;
        this.sign = "<" + sign + ">";
    }

    // marks status of task as done
    public void markAsDone() {
        this.isCompleted = true;
    }

    public String getItem() {
        return this.item;
    }

    public String getStatus() {
        return this.isCompleted ? "[\u2713]" : "[\u2718]";
    }

    public String getSign() {
        return this.sign;
    }
}
