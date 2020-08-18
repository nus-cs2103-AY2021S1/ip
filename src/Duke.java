import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println(new Greet(Greet.defaultGreet));

        String nextLine = input.nextLine();
        while (!nextLine.equals("bye")) {
            System.out.println(new Echo(nextLine));
            nextLine = input.nextLine();
        }

        input.close();
        System.out.println(new Exit(Exit.defaultExiting));
    }
}