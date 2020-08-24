import java.util.Scanner;

public class Ui {

    private Scanner sc = new Scanner(System.in);

    public void greeting() {
        String logo = " ____   ____\n"
                + "|  _ \\ |  _ \\\n"
                + "| | | || | | |\n"
                + "| |_| || |_| |\n"
                + "|____/ |____/\n";
        System.out.println("Hi! I'm\n" + logo + "How can I help you? :)\n"
                + "_________________________________________");
    }

    public void printLine() {
        System.out.println("_________________________________________");
    }

    public String readInput() {
        return sc.nextLine();
    }
}
