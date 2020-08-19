public class Task {
    public enum Status {
        PENDING,
        DONE;

        public String statusToSymbol() {
            if (this == DONE) {
                return "[✓] ";
            } else {
                return "[✗] ";
            }
        }

    }
    String name;
    Status status;
    public Task(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public void markAsDone() {
        this.status = Status.DONE;
    }

    public void undo() {
        this.status = Status.PENDING;
    }
}
