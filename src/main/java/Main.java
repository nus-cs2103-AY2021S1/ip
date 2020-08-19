// Main.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var bot = new Bot("ikurabowl");

        var sc = new Scanner(System.in);

        bot.greet();
        System.out.printf("> ");
        while (sc.hasNextLine() && bot.processCommand(sc.nextLine())) {
            System.out.printf("> ");
        }
    }
}
