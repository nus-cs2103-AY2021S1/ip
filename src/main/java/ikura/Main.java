// Main.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura;

import ikura.gui.GuiFrontend;

public class Main {

    private static final String DB_PATH   = "data/tasks.txt";

    /**
     * The entry point for the application.
     *
     * @param args the command ilne arguments
     */
    public static void main(String[] args) {

        var tasks = new TaskList(new Database(DB_PATH));

        var frontend = new GuiFrontend(tasks);
        frontend.run();
    }
}
