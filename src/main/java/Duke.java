import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String lineBreaker = "__________________________________";
        String greet = "Hello! I'm Duke" + "\n" +"What can I do for you?";
        String end = "Bye. Hope to see you again soon!";
        Scanner sc = new Scanner(System.in);

        System.out.println(lineBreaker);
        System.out.println(greet);
        System.out.println(lineBreaker);

        while(sc.hasNext()) {
            String input = sc.next();
            System.out.println(lineBreaker);
            if(input.equals("bye")) {
                System.out.println(end);
                System.out.println(lineBreaker);
                break;
            } else {
                System.out.println(input);
                System.out.println(lineBreaker);
            }
        }
    }
}
