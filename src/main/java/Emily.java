package main.java;
import java.util.Scanner;

public class Emily {
    //level 1: response and echo
    public static void main(String[] args) {
        String divider = "---------------";
        System.out.println("Hello, I am Emily\nWhat can i do for you?\n"+ divider);
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while(!input.equals("bye")){

            System.out.println("    " +  divider +
                    "\n    " + input +
                    "\n    " + divider);

            input = sc.nextLine();
        }

        System.out.println(divider + "\nBye~, hope to see you again!");
    }
}
