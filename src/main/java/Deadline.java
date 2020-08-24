public class Deadline extends Task{
    String deadline;
    String preposition;
    Deadline(String title, String preposition, String deadline) throws WrongUsageException{
        super(title);
        this.name = "deadline";
        this.usage = "deadline [TaskToBeDone] /by [DateTimeString]";
        this.description = "Stores a task in the list marked as a deadline";
        if(title.isEmpty() || preposition.isEmpty() || deadline.isEmpty()){
            throw new WrongUsageException(this.name, this.usage);
        }
        this.preposition = preposition;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(" + preposition + ": " + deadline + ")";
    }
}
