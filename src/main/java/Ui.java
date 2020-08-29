import java.util.Scanner;

public class Ui {
    private Scanner sc;
    
    Ui() {
        sc = new Scanner(System.in);
    }
    
    void greet() {
        String logo = "\n.-. .-')                .-') _  .-. .-')                .-') _\n"
                + "\\  ( OO )              (  OO) ) \\  ( OO )              (  OO) )\n"
                + " ;-----.\\  .-'),-----. /     '._ ;-----.\\  .-'),-----. /     '._\n"
                + " | .-.  | ( OO'  .-.  '|'--...__)| .-.  | ( OO'  .-.  '|'--...__)\n"
                + " | '-' /_)/   |  | |  |'--.  .--'| '-' /_)/   |  | |  |'--.  .--'\n"
                + " | .-. `. \\_) |  | |  |   |  |   | .-. `. \\_) |  | |  |   |  |\n"
                + " | |  \\  |  \\ |  | |  |   |  |   | |  \\  |  \\ |  | |  |   |  |\n"
                + " | '--'  /   `'  '-'  '   |  |   | '--'  /   `'  '-'  '   |  |\n"
                + " `------'      `-----'    `--'   `------'      `-----'    `--'\n";
        System.out.println("helluu! I'm\n" + logo + "\nwhat would you like me to do?\n");
    }
    
    void exit() {
        System.out.println("    bye! see you soon!");
    }
    
    String getUserInput() {
        return sc.nextLine();
    }
    
    void printStatus(String status) {
        System.out.println(status);
    }
}
