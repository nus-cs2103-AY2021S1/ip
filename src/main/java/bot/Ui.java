package bot;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private String name;

    public Ui(String name) {
        this.name = name;
    }

    /**
     * Print to system predetermined greeting.
     */
    public void greet() {
        String greeting = String.format("Good day, I'm %s. What can I do for you?", name);
        System.out.println(responseWrapper(greeting));
    }

    public void showMessage(String input) {
        System.out.println(responseWrapper(input));
    }

    public void showLoadingError() {
        System.out.println(responseWrapper("Unable to load user file"));
    }

    private String responseWrapper(String str) {
        final String line = "________________________________________________________________";
        return line + "\n    " + str + "\n" + line;
    }

}
