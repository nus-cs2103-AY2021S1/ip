package duke;

/**
 * The Ui class will deal with interactions with the user.
 */
public class Ui {
    private String response;

    /**
     * Welcomes the user.
     */
    public String showWelcome() {
        return "Welcome to Duke!";
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String showResponse() {
        return response;
    }
}
