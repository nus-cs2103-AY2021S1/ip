/**
 * Todos are generic tasks, with a "Todo" indicator.
 *
 * @author Dominic Siew Zhen Yu
 */
public class Todos extends Task{
    String TASKINDICATOR = "[T]";

    /**
     * the constructor of the Todos object
     *
     * @param userInput the name of the object
     */

    public Todos (String userInput) {
        super(userInput);
    }

    /**
     * printName() method returns the name of the Todos object, and an indication that it
     * is a todos task.
     *
     * @return a string representation of a Task object
     */

    public String printName() {
        return TASKINDICATOR + " " + super.printName();
    }
}