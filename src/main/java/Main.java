import duck.Duck;
import duck.storage.LocalStorage;
import duck.storage.Storage;
import duck.ui.Ui;
import duck.ui.ConsoleUi;

public class Main {
    public static void main(String args[]) {
        Ui ui = new ConsoleUi();
        Storage storage = new LocalStorage("data/data.ser");
        Duck bot = new Duck(ui, storage);
        bot.run();
    }
}
