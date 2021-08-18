public class Todos extends Task{
    String TASKINDICATOR = "[T]";

    public Todos (String userInput) {
        super(userInput);
    }

    public String printName() {
        return TASKINDICATOR + super.printName();
    }
}