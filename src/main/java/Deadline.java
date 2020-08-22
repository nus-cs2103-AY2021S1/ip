public class Deadline extends Task {
    public Deadline(String description, int index, boolean isDone) {
        super(description, index, isDone);
        super.type = TaskType.DEADLINE;
    }
    @Override
    public String getStatusWithIndex() {
        int idx = this.description.indexOf('/');
        String task = this.description.substring(0, idx);
        String end = new StringBuilder(this.description.substring(idx + 1)).insert(2,':').toString();
        return String.format("%s. %s%s%s(%s)", index, super.type, isDone ? super.done : super.start, task, end);
    }
    @Override
    public String toString() {
        int idx = this.description.indexOf('/');
        String task = this.description.substring(0, idx);
        String end = new StringBuilder(this.description.substring(idx + 1)).insert(2,':').toString();
        return String.format("%s%s%s(%s)", super.type, isDone ? super.done : super.start, task, end);
    }
}
