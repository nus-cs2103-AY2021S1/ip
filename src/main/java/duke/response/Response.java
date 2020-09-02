package duke.response;

public class Response {
    private String text = "";
    private boolean isExit = false;

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getExit() {
        return isExit;
    }

    public String getText() {
        return text;
    }
}
