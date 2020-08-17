import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Yooo, I'm Deke.\nWhat can I do for you today?");
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            if (input.equals("bye")) { //if user types "bye"
                System.out.println("Bye bye!!! See you again next time :)");
                input = "";
                sc.close();
            } else { //if user types anything other than "bye"
                System.out.println("you said: " + input);
                input = sc.nextLine();
            }
        }
    }
}
