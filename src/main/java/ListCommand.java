public class ListCommand implements Command {
    Storage store;
    public ListCommand(Storage store) {
        this.store = store;
    }

    @Override
    public void execute() {
        store.list();
    }
}
