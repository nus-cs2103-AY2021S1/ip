// Main.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

import java.util.Scanner;
import java.io.IOException;

public class Main {

    private static final String BOT_NAME  = "ikurabowl";
    private static final String DB_PATH   = "data/tasks.txt";

    public static void main(String[] args) {
        var bot = new Bot(BOT_NAME);

        var db = new Database(DB_PATH);

        try {
            bot.setTasks(db.loadTasks());
        } catch (IOException e) {
            System.out.printf("error occured while reading/creating the task list:\n%s\n", e);
            System.exit(1);
        } catch (InvalidDatabaseException e) {
            System.out.printf("malformed line while reading task list:\n%s\n", e);
            System.exit(1);
        }

        var sc = new Scanner(System.in);

        bot.greet();
        System.out.printf("> ");
        while (sc.hasNextLine() && bot.processCommand(sc.nextLine())) {
            System.out.printf("> ");
        }

        try {
            db.saveTasks(bot.getTasks());
        } catch (IOException e) {
            System.out.printf("failed to save task list to disk:\n%s\n", e);
            System.exit(1);
        }
    }
}
