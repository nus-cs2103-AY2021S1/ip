public class Todo extends Task{

    public Todo(String description) {
        super(description,"");
    }

    public String writeToFile() {
        return "T|" + super.writeToFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
