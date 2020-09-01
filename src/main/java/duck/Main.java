package duck;

import duck.storage.LocalStorage;
import duck.storage.Storage;
import duck.ui.ConsoleUi;
import duck.ui.Ui;


public class Main {
    /**
     * Creates components needed by the bot and runs the bot.
     *
     * @param args Arguments to be supplied to the program.
     */
    public static void main(String[] args) {
        Ui ui = new ConsoleUi();
        Storage storage = new LocalStorage("data/data.ser");
        Duck bot = new Duck(ui, storage);
        bot.run();
    }
}
