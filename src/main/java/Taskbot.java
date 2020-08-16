public class Taskbot {
    private String logo;
    private final String name = "TaskBot";

    /**
     * Constructor for the Taskbot class
     * @param logo The logo to be displayed for the title
     */
    public Taskbot(String logo) {
        this.logo = logo;
    }

    /**
     * Prints out the title using the given logo
     */
    public void printTitle() {
        System.out.println(logo);
    }

    /**
     * Method invoked to greet the user
     */
    public void greet() {
        System.out.printf("Hello there, my name is %s.\nHow may I be of assistance today?\n", name);
    }

    /**
     * Returns the same word given in the argument
     * @param word The input to be echoed
     */
    public void echo(String word) {
        borderString(word);
    }

    /**
     * Method invoked to say goodbye to the user
     */
    public void sayBye() {
        String message = "Bye! Hope to see you again soon!!";
        borderString(message);
    }

    /**
     * Helper function to wrap the given string in lines
     * @param s
     */
    private void borderString(String s) {
        System.out.println("------------------------------------------");
        System.out.println(s);
        System.out.println("------------------------------------------\n");
    }
}
