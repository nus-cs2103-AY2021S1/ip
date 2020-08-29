import java.util.Scanner;

/**
 * deals with interactions with the user
 */

public class Ui {
    public void showWelcome() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n")
                .append("hello! i'm duke :-)\n")
                .append("how may i help you?\n")
                .append("--------------------------------------------------------------");
        System.out.println(sb.toString());
    }

    public void showLoadingError() {
        System.out.println("There was an error loading the file");
    }

    public String readCommand() {
        String input;
        Scanner scan = new Scanner(System.in);

        input = scan.nextLine();

        return input;
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public String exit() {
        return "byebye! hope to see you again soon :-)";
    }

    public void showLine() {
        System.out.println("--------------------------------------------------------------");
    }
}
