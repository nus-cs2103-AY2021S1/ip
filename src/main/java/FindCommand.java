package main.java;

public class FindCommand extends Command {

    private String keyWord;

    FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws BobException {
        ui.findKeyWord(tasks.contains(keyWord));
    }
}
