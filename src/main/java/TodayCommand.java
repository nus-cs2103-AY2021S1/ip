public class TodayCommand extends Command{
    @Override
    public void execute(TaskList list) {
        list.printTasksToday();
    }
}
