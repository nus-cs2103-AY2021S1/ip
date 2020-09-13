package duke.task;
import duke.exceptions.DukeException;

public class Task {
    private boolean isDone;
    private String taskContent;
    private String taskTag;

    public Task(String taskContent) {
        this.taskContent = taskContent;
        this.isDone = false;
        this.taskTag = null;
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void test() throws DukeException {
        if (taskContent.length() == 0) {
            throw new DukeException("OOPS!!! The description of a task cannot be empty.");
        }
    }
    public String getType() {
        return "";
    }
    public boolean isDone() {
        return this.isDone;
    }
    public String getContent() {
        return this.taskContent;
    }
    public String getDate() {
        return "";
    }

    /**
     * Sets a tag for task.
     * @param tag is fetched from user command and stored as taskTag.
     * @return original tag if exists. Otherwise, it will return null.
     */
    public String setTag(String tag) {
        String oldTag = this.taskTag;
        if (tag.equals("null")) {
            this.taskTag = null;
        } else {
            this.taskTag = tag;
        }
        return oldTag;
    }

    public String getTag() {
        return this.taskTag;
    }
    @Override
    public String toString() {
        String res = "";
        if (isDone) {
            res += "[" + "\u2713" + "] " + this.taskContent;
        } else {
            res += "[" + "\u2718" + "] " + this.taskContent;
        }
        if (taskTag != null) {
            res += " " + taskTag;
        }
        return res;
    }
}
