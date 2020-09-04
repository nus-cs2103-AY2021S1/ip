package duke.ui;

/**
 * deals with interactions with the user
 */

public class Ui {
    public String showWelcome() {
        StringBuilder sb = new StringBuilder();
        sb.append("hello! i'm shiro :-)\n")
                .append("what can i do for you today?\n")
                .append("\n")
                .append("more: type 'help' for more information");
        return sb.toString();
    }

    public void showLoadingError() {
        System.out.println("There was an error loading the file");
    }

    public String exit() {
        return "byebye! hope to see you again soon :-)";
    }
}
