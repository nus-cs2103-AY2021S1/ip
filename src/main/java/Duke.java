package main.java;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // startup
        System.out.println("hi");
        Scanner s = new Scanner(System.in);

        // main loop
        while (true) {
            String input = s.next();
            if (input.equals("bye")){
                break;
            }

            System.out.println(input);
        }

        // end
        System.out.println("bye");
    }

}