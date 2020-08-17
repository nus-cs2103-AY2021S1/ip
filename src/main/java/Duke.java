import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean on = true;
        String line = "    ____________________________________________________________";
        Scanner sc = new Scanner(System.in);
        System.out.println(line);
        System.out.println("     Hello! I'm Duke\n" +
                "     What can I do for you?");
        System.out.println(line);

        while(on) {
            String input = sc.nextLine();
            if(input.compareTo("bye") == 0) {
                on = false;
                System.out.println(line);
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println("     " + input);
                System.out.println(line);
            }
        }
    }
}
