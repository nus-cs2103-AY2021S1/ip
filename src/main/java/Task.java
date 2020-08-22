public abstract class Task {
    protected String description;
    protected String done;

    public Task(String description, String done) {
        this.description = description;
        this.done = done;
    }

    public static String getDesc(String s) {
        return s.substring(7);
    }

    public static String stringFormat(String[] arr) throws Exception {
        String s;
        switch (arr[0]) {
            case "T":
                s = String.format("%s,%s,%s\n", arr[0], arr[1], arr[2]);
                break;
            case "E":
                s = String.format("%s,%s,%s,%s\n", arr[0], arr[1], arr[2], arr[3]);
                break;
            case "D":
                s = String.format("%s,%s,%s,%s\n", arr[0], arr[1], arr[2], arr[3]);
                break;
            default:
                throw new InvalidCommandException();
        }
        return s;
    }

    public String getStatus() {
        return this.done;
    }

    public void setStatus(String checked) {
        this.done = checked;
    }

    public abstract String[] getStringArr();

    @Override
    public String toString() {
        String icon = this.done.equals("1") ? "[✓]" : "[✗]";
        return String.format("%s %s", icon, this.description);
    }
}