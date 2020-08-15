public class Deadline extends Task {

    protected String time;

    Deadline(String description) {
        super(description);
        String[] strArr = description.split("/", 2);
        this.description = strArr[0];
        this.time = strArr[1].substring(2);
    }

    @Override
    public String toString() {
        return String.format("[D]%s(by:%s)", super.toString(), time);
    }
}
