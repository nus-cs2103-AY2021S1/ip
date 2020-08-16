import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        greet();
        while (isRunning) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("bye! see you soon!\n");
                isRunning = false;
            } else {
                echo(input);
            }
        }
    }

    static void greet() {
        String logo = "\n.-. .-')                .-') _  .-. .-')                .-') _    \n"
                + "\\  ( OO )              (  OO) ) \\  ( OO )              (  OO) )   \n"
                + " ;-----.\\  .-'),-----. /     '._ ;-----.\\  .-'),-----. /     '._  \n"
                + " | .-.  | ( OO'  .-.  '|'--...__)| .-.  | ( OO'  .-.  '|'--...__) \n"
                + " | '-' /_)/   |  | |  |'--.  .--'| '-' /_)/   |  | |  |'--.  .--' \n"
                + " | .-. `. \\_) |  | |  |   |  |   | .-. `. \\_) |  | |  |   |  |    \n"
                + " | |  \\  |  \\ |  | |  |   |  |   | |  \\  |  \\ |  | |  |   |  |    \n"
                + " | '--'  /   `'  '-'  '   |  |   | '--'  /   `'  '-'  '   |  |    \n"
                + " `------'      `-----'    `--'   `------'      `-----'    `--'    \n";
        System.out.println("helluu! I'm\n" + logo + "\nwhat would you like me to do?\n");
    }

    static void echo(String str) {
        System.out.println(str + "\n");
    }

}
