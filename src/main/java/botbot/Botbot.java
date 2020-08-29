package botbot;

import botbot.commands.Command;
import botbot.commands.ExitCommand;
import botbot.exceptions.BotbotException;

public class Botbot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Botbot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.greet();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = ui.getUserInput();
                Command c = Parser.parseCommand(input);
                c.execute(storage, tasks, ui);
                isRunning = !ExitCommand.isExit(c);
            } catch (BotbotException e) {
                ui.printStatus(e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        new Botbot("data/botbot.txt").run();
    }
}