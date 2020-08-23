public class Task {

    public String content;
    public boolean isComplete;

    public Task(String content) throws DukeException {
        if (content.replace(" ", "").equals("")) {
            throw new DukeException("Contents of a task cannot be empty.");
        }
        this.content = content;
        this.isComplete = false;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        if (isComplete) {
            return String.format("[Y] %s", content);
        } else {
            return String.format("[X] %s", content);
        }
    }
}
