public class DatedTask extends Task {
    protected String date;

    public DatedTask(String name, String date) {
        super(name);
        this.date = date;
    }

    public DatedTask(String name, boolean completed, String date) {
        super(name, completed);
        this.date = date;
    }

    @Override
    public String format() {
        return super.format() + SAVE_DELIMITER + this.date;
    }
}
