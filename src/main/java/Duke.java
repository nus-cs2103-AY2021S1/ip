import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "I'm DukeForQ, your chatbot! You can enter everything you want to enter. If you want to exit, enter 'bye'!");
        String s;
        while (true) {
            s = sc.nextLine();
            if (s.equals("bye")) {
                System.out.println("Bye, hope to see you again!");
                sc.close();
                System.exit(0);
            } else {
                System.out.println(s);
            }
        }
    }
}
