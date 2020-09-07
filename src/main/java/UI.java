/**
 * Encapsulates messages for interacting with the user.
 */

/**
 * manages user interfacce interactions
 */
public class UI {
    public void print(String s) {
        System.out.println(s);
    }

    public String greeting() {
        return "Welcome to use dukebot!\n";
    }

    public String inputInstruction() {
        return "Please enter your instructions as follows\n"
                + "enter find/delete/todo/deadline/event/list/stats/done\n";
    }
}
