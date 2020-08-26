package bot;

public class Ui {
    private String name;

    public Ui(String name) {
        this.name = name;
    }

    public void greet() {
        String greeting = String.format("Good day, I'm %s. What can I do for you?", name);
        System.out.println(responseWrapper(greeting));
    }

    public void goodbye() {
        String farewell = responseWrapper("Have a good day, mate!");
        System.out.println(farewell);
    }

    public void showMessage(String input) {
        System.out.println(responseWrapper(input));
    }

    public void showLoadingError() {
        System.out.println(responseWrapper("Unable to load user file"));
    }

    private String responseWrapper(String str) {
        final String TEXT_LINE = "________________________________________________________________";
        return TEXT_LINE + "\n    " + str + "\n" + TEXT_LINE;
    }

}
