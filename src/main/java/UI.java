/**
 * The User Interface Object with a scanner and storage object that takes in and stores user input
 */
public class UI {
    private Storage storage;

    /**
     * Initializes a UI object with the ability to scan for user input and store it
     * @param store
     */
    public UI(Storage store) {
        this.storage = store;
    }

    /**
     * Prints a welcome message for the users
     */
    public void welcome() {
        System.out.println("_____________________________");
        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you?");
        System.out.println("_____________________________");
    }

    /**
     * Gets a response to an user input
     * @param input A String from the user
     * @return String containing the response
     */
    public String getResponse(String input) {
        TaskList list = Storage.load();
        return Parser.getResponse(input, list);
    }

    /**
     * Stores the resultant tasklist in a file and prints a simple goodbye message for the user
     */
    public void escape() {
        storage.save();
        System.out.println("_____________________________");
        System.out.println("Bye. Hope to see you again soon!\n"
                + "_____________________________");
    }
}
