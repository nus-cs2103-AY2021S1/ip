// Main.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

import java.util.Scanner;
import java.io.IOException;

public class Main {

    private static final String BOT_NAME  = "ikurabowl";
    private static final String DB_PATH   = "data/tasks.txt";

    public static void main(String[] args) {
        var tasks = new TaskList(new Database(DB_PATH));
        var ui = new Frontend(BOT_NAME);
        var bot = new Bot(ui, tasks);

        ui.greet();
        while (ui.readLine().map(bot::processCommand).orElse(false))
            ;
    }
}
