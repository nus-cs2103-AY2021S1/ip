import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        IntroModule.display();
        StringIdentifier strIden = new StringIdentifier();
        Scanner sc = new Scanner(System.in);

        while (strIden.isRunning()) {
            String userInput = sc.nextLine();
            strIden.checker(userInput);
        }
    }
}
