import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Chatbot bot = new Chatbot();
        String line = bot.getHorizontalLine();
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            System.out.println(line);
            String str = sc.next();
            if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                sc.close();
                return;
            } else {
                System.out.println(str);
                System.out.println(line);
            }
        }
    }
}
