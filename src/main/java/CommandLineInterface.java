import java.util.Date;
import java.util.Scanner;

/**
 * Ui that interacts with user through CommandLineInterface.
 */
public class CommandLineInterface extends Ui {

    private final Scanner sc;

    public CommandLineInterface() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads Scanner input.
     *
     * @return String representing scanner input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    @Override
    public void showError(String error) {
        System.out.println(getError(error));
    }

    @Override
    public void showWelcome() {
        System.out.println(getWelcome());
    }

    @Override
    public void showGoodbye() {
        System.out.println(getGoodbye());
    }

    /**
     * Displays all the tasks, based on date if date is not null.
     *
     * @param tasks Tasks to display.
     * @param date Date to filter tasks by.
     */
    @Override
    public void showTaskList(TaskList tasks, Date date, String keyWord) {
        System.out.println(getTaskList(tasks, date, keyWord));
    }

    @Override
    public void showDoneTask(Task task) {
        System.out.println(getDoneTask(task));
    }

    @Override
    public void showDeletedTask(Task task, TaskList taskList) {
        System.out.println(getDeletedTask(task, taskList));
    }

    @Override
    public void showAddTask(Task task, TaskList taskList) {
        System.out.println(getAddTask(task, taskList));
    }

}
