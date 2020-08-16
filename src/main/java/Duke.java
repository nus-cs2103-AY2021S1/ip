import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner sc = new Scanner (System.in);

        //Greeting the user
        System.out.println ("Hello! I'm Duke");
        System.out.println ("What can I do for you?");

        String next = sc.nextLine();
        while (!next.equals("bye")){
            System.out.println (next);
            next = sc.nextLine();
        }

        System.out.println ("Bye. Hope to see your again soon!");
    }
}
