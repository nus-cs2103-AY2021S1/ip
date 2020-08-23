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

    public Task(String content, boolean isComplete) {
        this.content = content;
        this.isComplete = isComplete;
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

    private static int booleanToInt(boolean bool) {
        return bool ? 1 : 0;
    }

    public String toSaveString() {
        return String.format("%s/%s", booleanToInt(isComplete), content);
    }
}
