import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String tab = "  ";
        String lineBreaker = "__________________________________";
        String greet = "Hello! I'm Duke" + "\n" + tab + "What can I do for you?";
        String end = "Bye. Hope to see you again soon!";
        Scanner sc = new Scanner(System.in);

        System.out.println(tab + lineBreaker);
        System.out.println(tab + greet);
        System.out.println(tab + lineBreaker);

        while(sc.hasNext()) {
            String input = sc.next();
            System.out.println(tab + lineBreaker);
            if(input.equals("bye")) {
                System.out.println(tab + end);
                System.out.println(tab + lineBreaker);
                break;
            } else {
                System.out.println(tab + input);
                System.out.println(tab + lineBreaker);
            }
        }
    }
}
