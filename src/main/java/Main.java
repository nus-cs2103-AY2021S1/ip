import duck.Duck;
import duck.Ui;
import duck.ConsoleUi;

public class Main {
    public static void main(String args[]) {
        Ui ui = new ConsoleUi();
        Duck bot = new Duck(ui);
        bot.run();
    }
}
