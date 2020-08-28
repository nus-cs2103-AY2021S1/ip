/**
 * represents a todo task
 */

public class ToDo extends Task{
    public ToDo(String taskDescription) throws EmptyDescriptionException {
        if (taskDescription.length() <= 5) {
            throw new EmptyDescriptionException("oops! the description of a todo cannot be empty");
        } else {
            int space = taskDescription.indexOf(" ");

            this.task = taskDescription.substring(space + 1);
            this.done = false;
        }
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String encode() {
        StringBuilder encodedTask = new StringBuilder();

        encodedTask.append("T | ")
                .append(this.isDoneInt() + " | ")
                .append(this.task);

        return encodedTask.toString();
    }

    public static ToDo decode(String string) throws EmptyDescriptionException {
        String[] split = string.split(" \\| ");

        String taskDescription = "todo " + split[2];

        ToDo todo = new ToDo(taskDescription);

        if (split[1].contains("1")) {
            todo.markAsDone();
        }

        return todo;
    }
}
