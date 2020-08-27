public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
        taskType = TaskType.TODO;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString();
    }
    
    @Override
    public String generateSaveFileData() {
        return "T|" + (isDone ? "1" : "0") + "|" + desc;
    }
}
