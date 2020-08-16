import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hi there, I'm TARS!\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            System.out.println(input);
            input = scanner.nextLine();
        }
        System.out.println("Bye bye. See you again soon!");
    }
}
