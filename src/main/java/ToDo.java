public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    protected static ToDo createToDo(String[] words) {
        StringBuilder newDescription = new StringBuilder();
        for(int i = 1; i < words.length - 1; i++) {
            newDescription.append(words[i]).append(" ");
        }
        newDescription.append(words[words.length - 1]);
        return new ToDo(newDescription.toString());
    }

    @Override
    protected boolean isComplete() {
        return super.isComplete();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
