package utils;

import java.util.Scanner;

public class UI {

    private final Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }
    public void print(Object object) {
        String lineDecoration = "\t" + "_".repeat(60);
        System.out.println(lineDecoration);
        System.out.println("\t" + object);
        System.out.println(lineDecoration);
    }

    public String read() {
        return scanner.nextLine();
    }
}
