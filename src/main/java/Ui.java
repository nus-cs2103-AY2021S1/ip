import java.util.Scanner;

public class Ui {

    private Scanner sc;
    private String line = "____________________________\n"
                         +"____________________________\n";

    private String logo = "****** ****** ****** ******\n"
                         +"   *   *      *      *\n"
                         +"   *   ****** ****** ******\n"
                         +"*  *   *      *      *\n"
                         +"***    ****** *      *\n";

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showLoadingError() {
        System.out.println("******LOADING ERROR******");
    }

    public void showSavingError() {
        System.out.println("******SAVING ERROR******");
    }

    public void printLine() {
        System.out.println(line);
    }

    public void printLogo() {
        System.out.println(logo);
    }

    public void printStarting() {
        System.out.println("My name is\n");
        printLogo();
        System.out.println("What do you want?");
        printLine();
    }

    public String[] readCommand() {
        return sc.nextLine().trim().split(" ", 2);
    }
}
