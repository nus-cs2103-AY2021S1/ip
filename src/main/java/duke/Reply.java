package duke;

public class Reply {

    private String res;

    public Reply() {
        res = "";
    }

    public void addNewLines(String x) {
        res += x + "\n";
    }

    @Override
    public String toString() {
        return res;
    }
}
