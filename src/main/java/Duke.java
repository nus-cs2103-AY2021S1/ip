import java.util.Scanner;

public class Duke {
    final static String GREETING_MESSAGE = "Buenos Dias! Soy Duke, como estas mi amigo?";
    final static String TERMINATION = "bye";
    final static String TERMINATION_MESSAGE = "Adios, amigos!";

    public static void main(String[] args) {
        System.out.println(GREETING_MESSAGE);

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        if (userInput.equals(TERMINATION)) {
            System.out.println(TERMINATION_MESSAGE);
            sc.close();
        } else {
            System.out.println(userInput);
        }
    }
}
