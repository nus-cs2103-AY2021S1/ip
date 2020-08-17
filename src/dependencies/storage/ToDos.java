package dependencies.storage;

import static dependencies.storage.CompletionState.UNFINISHED;

public class ToDos extends Task {
    ToDos(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return String.format(
                "[ToDo][%s] %s", super.state == UNFINISHED ? "X"
                        : Character.toString((char)0x2713),
                super.task);
    }
}
