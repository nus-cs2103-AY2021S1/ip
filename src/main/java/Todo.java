import javax.swing.*;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    private Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    public static Todo decode(String saved) throws AliceException {
        String[] inputs = saved.split(" \\| ");
        boolean isDone = inputs[0].equals("1");
        if (inputs.length == 2) {
            return new Todo(isDone, inputs[1]);
        } else {
            throw new AliceException("Corrupted Todo data");
        }
    }

    @Override
    public String encode() {
        return "T | " + super.encode();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
