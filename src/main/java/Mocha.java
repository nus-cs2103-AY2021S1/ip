import java.util.Scanner;

public class Mocha {
    public static void main(String[] args) {

        String horizontalLine = "_______________________________________________________" + "\r\n";
        String nameIntro = "Hello, I'm Mocha!";
        String greeting = "What's up today!";
        System.out.println(horizontalLine + "\r\n" + nameIntro + "\r\n" + greeting + "\r\n" + horizontalLine + "\r\n");

        Scanner userCommand = new Scanner(System.in);
        while (userCommand.hasNext() && !userCommand.hasNext("bye")) {
            System.out.println(horizontalLine + "\r\n" + userCommand.next() + "\r\n" + horizontalLine);
        }
        if (userCommand.hasNext("bye")) {
            System.out.println(horizontalLine + "\r\n" + "Bye! See ya soon!" + "\r\n" + horizontalLine);
            userCommand.close();
        }
    }
}
