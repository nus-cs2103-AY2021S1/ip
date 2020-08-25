public class ListCommand extends Command {

    @Override
    public void execute(Tasklist tasklist, UserInterface ui) throws DukeListException {
        if (tasklist.getTaskSize() != 0) {
            ui.listTask();
            for (int i = 0; i < tasklist.getTaskSize(); i++) {
                ui.printTask(i + 1, tasklist.get(i).toString());
            }
        } else {
            throw new DukeListException("Your list is empty.");
        }
    }
}
