import java.util.Objects;

public class Task {

    private final String name;
    private boolean isDone;

    protected Task(String name, TaskType taskType) throws DukeEmptyDescException {
        if (Ui.isBlankString(name)) {
            throw new DukeEmptyDescException(taskType);
        } else {
            this.name = name;
            isDone = false;
        }
    }

    protected Task(String name, TaskType taskType, boolean isDone) throws DukeEmptyDescException {
        if (Ui.isBlankString(name)) {
            throw new DukeEmptyDescException(taskType);
        } else {
            this.name = name;
            this.isDone = isDone;
        }
    }


    private String getStatusIcon() {
        return (isDone ? "O" : "X"); //return tick or X symbols
    }

    /**
     * Completes the task.
     */
    public void complete() {
        isDone = true;
    }

    /**
     * Checks if the task contains a keyword.
     * @param key Keyword to be searched.
     * @return Boolean representing if the keyword.
     */
    public boolean hasKeyword(String key) {
        String[] words = name.split(" ");
        for (String word : words) {
            if (key.equals(word)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else {
            Task task = (Task) o;
            return name.equals(task.name);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isDone);
    }
}
