package main.java;

public class DeleteOutOfBoundException extends DukeException {
    int oneBasedIndex;

    public DeleteOutOfBoundException(int oneBasedIndex) {
        this.oneBasedIndex = oneBasedIndex;
    }
    @Override
    public String toString() {
        return super.toString() + " " + "The task index " + oneBasedIndex + " is not in the task list.";
    }
}
