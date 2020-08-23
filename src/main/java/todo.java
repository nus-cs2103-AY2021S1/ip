/**
 * The todo is a subclass of Task and it is used to describe tasks that have no specific deadline
 */
public class todo extends Task {
    /**
     *
     * @param name super(name) so that it does whatever is mentioned in the parent class
     */
    public todo(String name) {
        super(name);
    }
    /**
     * takes no arguments and overrides the toString method
     * @return the specific representation for todo class as mentioned with [T] indicating that it is a todo class
     *
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    public String inputListFormat(){
        return "T" + super.inputListFormat();
    }
}