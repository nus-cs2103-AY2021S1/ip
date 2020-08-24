public class DeadlineCommand extends Command {
    public boolean execute(){
        ui.askDeadlineName();
        String deadlineName = sc.nextLine();
        ui.askDeadlineDate();
        String deadlineDate = sc.nextLine();
        tm.add(new Deadline(deadlineName, deadlineDate));
        return true;
    }
}