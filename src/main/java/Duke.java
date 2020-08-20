import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Yoo ( ﾟ▽ﾟ)/ \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while(!input.equals("bye")) {
            System.out.println(input);
            input = sc.next();
        }
        System.out.println("Bye! Come back soon ( ･ω･)ﾉ");
    }
}
