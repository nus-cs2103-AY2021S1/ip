public class Todo extends Task implements Saveable {
    Todo(String label, boolean done) {
        super(label, done);
    }

    @Override
    public String getInfo() {
        StringBuilder str = new StringBuilder();
        str.append("T");
        str.append(super.getInfo());
        return str.toString();
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
