import duck.Duck;
import duck.LocalStorage;
import duck.Storage;
import duck.Ui;
import duck.ConsoleUi;

public class Main {
    public static void main(String args[]) {
        Ui ui = new ConsoleUi();
        Storage storage = new LocalStorage("data/data.ser");
        Duck bot = new Duck(ui, storage);
        bot.run();
    }
}
