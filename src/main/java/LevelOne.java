package main.java;

import java.util.Scanner;

public class LevelOne {
    public static void interact() {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        while(!next.equals("bye")) {
            System.out.println(Duke.makeBlock(next));
            next = scanner.next();
        }
        System.out.println(Duke.makeBlock(Duke.EXITMESSAGE));
    }
}
