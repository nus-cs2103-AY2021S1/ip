package duck;

import duck.storage.LocalStorage;
import duck.storage.Storage;

public class Main {
    /**
     * Creates components needed by the bot and runs the bot.
     *
     * @param args Arguments to be supplied to the program.
     */
    public static void main(String[] args) {
        Storage storage = new LocalStorage("data/data.ser");
        Duck bot = new Duck(storage);

    }
}
