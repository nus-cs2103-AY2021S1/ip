package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // startup
        System.out.println("hi");
        Scanner s = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        // main loop
        while (true) {
            String input = s.next();
            if (input.equals("bye")){
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i+1) + ". " + list.get(i));
                }
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
        }

        // end
        System.out.println("bye");
    }

}