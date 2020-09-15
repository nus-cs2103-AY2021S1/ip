package duke;

public class MyString {

    private String res;

    public MyString() {
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
