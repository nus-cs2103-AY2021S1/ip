public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public static ToDo load(String loadTask) {
        String[] splitTask = loadTask.split(" \\| ", 3);
        ToDo todo = new ToDo(splitTask[2]);
        if (splitTask[1].equals("1")) {
            todo.markAsDone();
        }
        return todo;
    }

    @Override
    public String save(int isFinished) {
        return "T | " + super.save(isFinished);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}