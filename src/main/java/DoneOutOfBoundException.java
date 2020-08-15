package main.java;

public class DoneOutOfBoundException extends DukeException {
    int oneBasedIndex;

    public DoneOutOfBoundException(int oneBasedIndex) {
        this.oneBasedIndex = oneBasedIndex;
    }
    @Override
    public String toString() {
        return super.toString() + " " + "The task index " + oneBasedIndex + " is not in the task list.";
    }
}
