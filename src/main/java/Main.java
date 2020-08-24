// Main.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

import java.util.Scanner;
import java.io.IOException;

public class Main {

    private static final String BOT_NAME  = "ikurabowl";
    private static final String DB_PATH   = "data/tasks.txt";

    public static void main(String[] args) {
        var tasks = new TaskList(new Database(DB_PATH));
        var bot = new Bot(BOT_NAME, tasks);

        var sc = new Scanner(System.in);

        bot.greet();
        System.out.printf("> ");
        while (sc.hasNextLine() && bot.processCommand(sc.nextLine())) {
            System.out.printf("> ");
        }

        tasks.save();
    }
}
