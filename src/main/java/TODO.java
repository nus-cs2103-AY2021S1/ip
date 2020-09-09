public class TODO extends Task {

    public TODO(String name, Status status) {
        super(name, status);
    }

    @Override public String toString() {
        return "[T] " + this.status.statusToSymbol() + this.name;
    }
}
