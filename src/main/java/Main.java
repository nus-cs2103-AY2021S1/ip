// Main.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

import java.util.Scanner;

public class Main {

    private static String LINE = "____________________________________________________________";



    public static void main(String[] args) {
        var bot = new Bot("ikurabowl");

        var sc = new Scanner(System.in);

        bot.greet();
        while (sc.hasNextLine() && bot.processCommand(sc.nextLine())) {
        }
    }
}
