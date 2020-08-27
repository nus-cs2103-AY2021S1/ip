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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof ToDo) {
            ToDo other = (ToDo) o;
            return this.desc.equals(other.desc) && this.isDone == other.isDone;
        } else {
            return false;
        }
    }
}
