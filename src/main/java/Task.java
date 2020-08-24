public class Task {

    enum Status {
        COMPLETED,
        INCOMPLETE
    }

    String description;
    Status status = Status.INCOMPLETE;
    String date;

    public Task (String description, String date) {
        this.description = description;
        this.date = date;
        this.status = Status.INCOMPLETE;
    }

    public void markDone() {
        this.status = Status.COMPLETED;
    }

    public boolean isDone() { return this.status == Status.COMPLETED; }

    public String writeToFile() {
        return (this.status.equals(Status.COMPLETED) ? "1" : "0")
                + "|" + this.description.strip()
                + (this.date.isBlank() ? "" : ("|" + this.date.strip()));
    }

    public String toString() {
        return (this.status == Status.COMPLETED ? "[\u2718]" : "[\u2713]") + this.description;
    }

}
