// Main.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura;

import ikura.gui.GuiLauncher;

public class Main {

    private static final String BOT_NAME  = "ikurabowl";
    private static final String DB_PATH   = "data/tasks.txt";

    /**
     * The entry point for the application.
     *
     * @param args the command ilne arguments
     */
    public static void main(String[] args) {

        GuiLauncher.startGui();

        /*
        var tasks = new TaskList(new Database(DB_PATH));
        var ui = new Frontend(BOT_NAME);
        var bot = new Bot(ui, tasks);

        ui.greet();
        while (ui.readLine().map(bot::processCommand).orElse(false))
            ;

        */
    }
}
