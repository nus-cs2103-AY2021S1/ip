import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("______________________");

        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();

        while(!input.equals("bye")) {
            System.out.println(input);
            input = sc.nextLine();
        }
    }
}
