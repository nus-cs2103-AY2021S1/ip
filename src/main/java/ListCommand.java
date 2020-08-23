public class ListCommand extends Command{
    
    public ListCommand(String input) {
        super(input);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if(tasks.getTasks().size() == 0) {
            System.out.println("\tList is empty! Start adding some tasks");
        } else {
            System.out.println("\t Here are the tasks in your list:");
            for (int i = 1; i <= tasks.getTasks().size(); i++) {
                Task current = tasks.getTasks().get(i - 1);
                System.out.println("\t" + i + "." + current.toString());
            }
        }
    }
    public boolean isExit() {
        return false;
    }
}
