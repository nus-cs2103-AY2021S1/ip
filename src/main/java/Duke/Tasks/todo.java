/**
 * The todo is a subclass of Task and it is used to describe tasks that have no specific deadline
 */
package Duke.Tasks;

public class todo extends Task {
    /**
     * assigns name to name value
     * @param name super(name) so that it does whatever is mentioned in the parent class
     */
    public todo(String name) {
        super(name);
    }

    /**
     * assigns name and done to values
     * @param name assigns name to this.name
     * @param done assigns done to this.done
     */
    public todo(String name, boolean done){
        super(name, done);
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

    /**
     * gives a specific string representation for that in the tasks.txt file and overrides that in Task to make
     * it unique to that for todo
     * @return the string representation
     */
    public String inputListFormat(){
        return "T" + super.inputListFormat();
    }
}