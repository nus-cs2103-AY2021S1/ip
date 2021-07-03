public class DoAfter extends Task implements Savable {
    private String beforeEvent;

    DoAfter(String label, String beforeEvent, boolean done) {
        super(label, done);
        this.beforeEvent = beforeEvent;
    }

    @Override
    public String getInfo() {
        StringBuilder str = new StringBuilder();
        str.append("A");
        str.append(super.getInfo());
        str.append(super.separator);
        str.append(beforeEvent);

        return str.toString();
    }

    @Override
    public String toString() {
        return String.format("[A]%s (after: %s)", super.toString(), beforeEvent);
    }
}
