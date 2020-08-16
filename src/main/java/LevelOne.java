package main.java;

import java.util.Scanner;

public class LevelOne {
    public static void interact() {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.nextLine();
        while(!next.equals("bye")) {
            System.out.println(Duke.makeBlock(next));
            next = scanner.nextLine();
        }
        System.out.println(Duke.makeBlock(Duke.EXITMESSAGE));
    }
}
