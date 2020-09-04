package raythx98.grandma.ui;

/**
 * Deals with the user interface.
 */
public class Ui {

    private String toShowOnScreen = "";

    /**
     * Prints a greeting message.
     */
    public String greet() {
        return "Hello mah dud, itza ol' grandma robo speakin :D "
                + help()
                + "\nwhat duh hell du yu wan?";
    }

    /**
     * Prints a farewell message.
     */
    public void farewell() {
        toShowOnScreen += "Never come back,\n"
                + "dun wanna see yu ever agin.\n"
                + "Exiting in 5... \nminutes? hours? idk you'll find out";
    }

    /**
     * Something.
     *
     * @return
     */
    public String help() {
        toShowOnScreen += "These r wud u tell your ol' grandma here...\n"
                + "To add task\n        todo {description} \n                /by {YYYY-MM-DD HHmm}\n"
                + "        deadline {description} \n                /by {YYYY-MM-DD HHmm}\n"
                + "        event {description} \n                /at {YYYY-MM-DD HHmm}\n"
                + "To delete task\n        delete {task number}\n"
                + "To mark task done\n        done {task number}\n"
                + "To list all tasks\n        list\n"
                + "To find tasks\n        find {keyword}\n"
                + "To exit\n        bye";
        return toShowOnScreen;
    }

    /**
     * Something.
     */
    public void resetString() {
        toShowOnScreen = "";
    }

    /**
     * Something.
     * @param string
     */
    public void addShowOnScreen(String string) {
        toShowOnScreen += string;
    }

    /**
     * Something.
     *
     * @return asd.
     */
    public String finalShowOnScreen() {
        return toShowOnScreen;
    }

    /**
     * Prints the error message.
     *
     * @param e String of error to be printed.
     */
    public String showError(Exception e) {
        return e.getMessage();
    }

    /**
     * Something.
     *
     * @return Soemthing.
     */
    public String showUncheckedException() {
        return "Wtf kind of error u giving me";
    }
}
