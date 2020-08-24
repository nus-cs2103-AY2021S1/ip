import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void printDivider() {
        System.out.println("\t______________________________________________\n");
    }

    public void printErrorDivider() {
        System.out.println("\t!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
    }

    public void printWelcome() {
        printDivider();
        String MrCamel =
                "\t                  ,,__\n"
                        + "\t        ..  ..   / o._)\n"
                        + "\t       /--'/--\\  \\-'|| \n"
                        + "\t      /        \\_/ / |\n"
                        + "\t    .'\\  \\__\\  __.'.'\n"
                        + "\t      )\\ |  )\\ |\n"
                        + "\t     // \\\\ // \\\\\n"
                        + "\t    ||_  \\\\|_  \\\\_\n"
                        + "\t    '--' '--'' '--'\n";
        System.out.println("\tMr Camel says hello. What can Mr Camel do for you today?\n" + MrCamel);
        printDivider();
    }

    public void printGoodbye() {
        printDivider();
        System.out.println("\tBye. Mr Camel will miss you! :(");
        printDivider();
    }

    public void printError(DukeException e) {
        printErrorDivider();
        System.out.println("\t" + e.getMessage());
        printErrorDivider();
    }

    public void printMsg(String msg) {
        System.out.println("\t" + msg);
    }
}
