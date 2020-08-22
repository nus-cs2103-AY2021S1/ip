public class ListCommand implements Command {

    public void execute(String command, Storage s, Ui ui) {
        s.printAll();
    }

}
