package duke;

import java.util.Scanner;

public class Ui {
    Scanner s;

    Ui() {
        s = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println("\t " + message);
    }

    void showLogo() {
        String logo = "\t██████╗ ██╗   ██╗██╗  ██╗███████╗\n" +
                "\t██╔══██╗██║   ██║██║ ██╔╝██╔════╝\n" +
                "\t██║  ██║██║   ██║█████╔╝ █████╗  \n" +
                "\t██║  ██║██║   ██║██╔═██╗ ██╔══╝  \n" +
                "\t██████╔╝╚██████╔╝██║  ██╗███████╗\n" +
                "\t╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚══════╝\n";
        System.out.println("\tHello boss! This is\n" + logo);
    }

    void showWelcome() {
        showLogo();
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t___________________________________________________________________________\n");
    }

    void showLine() {
        System.out.println("\t___________________________________________________________________________");
    }

    public void bye() {
        displayMessage("Bye. Hope to see you again soon");
    }

    String readCommand() {
        return s.nextLine();
    }
}
