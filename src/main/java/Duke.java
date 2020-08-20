import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Greet
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        // Echo
        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")){
                break;
            } else {
                System.out.println(input);
            }
        }

        // Exit
        System.out.println("Bye. Hope to see you again soon!");

        sc.close();
    }

}
