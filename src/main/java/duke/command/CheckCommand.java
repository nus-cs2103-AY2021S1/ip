package duke.command;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Task;


public class CheckCommand extends Command {
    private final String target;

    public CheckCommand(String detail) {
        this.target = detail.trim();
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        AtomicInteger count = new AtomicInteger(1);
        Predicate<Task> checkDate =  x -> x.getDate().equals(this.target);
        Function<Task, String> taskString = x -> ui.showTask(count.getAndIncrement() ,x);
        BinaryOperator<String> accumulator = (x,y) -> (x + "\n" + y);

        String output = list.getList().stream()
                                      .filter(checkDate)
                                      .map(taskString)
                                      .reduce(accumulator)
                                      .orElse("empty");

        return output.equals("empty")? ui.showNothingFound() : ui.showCheck() +
                "\n" + output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
