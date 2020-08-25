public class ToDo extends Task {

    public ToDo(String in) {
        super(in);
    }

    public String toString() {
        String donez;
        if (done) {
            donez = "✓";
        } else {
            donez = "✗";
        }
        return "[T] [" + donez + "] " + task;
    }

    public String saveText() {
        return "T | " + super.saveText();
    }
}
