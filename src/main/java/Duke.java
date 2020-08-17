import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String divider = "************************************************\n";
        String intro = "Hello! I'm Duke\nWhat can i do for you?\n";
        System.out.println(divider + intro + divider);
        boolean carryOn = true;
        while(carryOn) {
            String inputString = input.nextLine();
            if (!inputString.equals("bye")) {
                System.out.println(divider + inputString + "\n" + divider);
            } else {
                System.out.println(divider + "Bye! See you next time!" + "\n" + divider);
                carryOn = false;
            }
        }
    }
}
