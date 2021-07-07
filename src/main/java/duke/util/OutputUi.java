package duke.util;

public class OutputUi {
    private StringBuilder sb;

    public OutputUi() {
        this.sb = new StringBuilder();
    }

    public void reset() {
        this.sb = new StringBuilder();
    }

    public void addSentence(String s) {
        this.sb.append("  " + s + "\n");
    }

    public String getResponse() {
        return this.sb.toString();
    }
}
