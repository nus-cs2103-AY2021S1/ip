public class Task {

    enum Status {
        COMPLETED,
        INCOMPLETE
    }

    String description;
    Status status = Status.INCOMPLETE;

    public Task (String description) {
        this.description = description;
        this.status = Status.INCOMPLETE;
    }

    public void markDone() {
        this.status = Status.COMPLETED;
    }

    public String toString() {
        return (this.status == Status.COMPLETED ? "[\u2718]" : "[\u2713]") + this.description;
    }

}
