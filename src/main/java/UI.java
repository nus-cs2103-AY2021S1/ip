
/**
 * manages user interfacce interactions
 */
public class UI {

    /**
     * Returns the String description.
     * @return String description.
     */
    public void print(String s) {
        System.out.println(s);
    }

    /**
     * Returns the String description for greeting.
     * @return String greeting.
     */
    public String greeting() {
        return "Welcome to use dukebot!\n";
    }

    /**
     * Returns the input instruction.
     * @return String describing possible instructions.
     */
    public String inputInstruction() {
        return "Please enter your instructions as follows: \n"
                + "enter find/delete/todo/deadline/event/list/stats/done\n";
    }
}
