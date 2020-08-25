package duke.command;

import duke.Ui;
import duke.io.Storage;
import duke.io.TaskList;
import duke.task.Task;
import java.util.ArrayList;

public class ListCommand extends Command {

  @Override
  public void execute(TaskList task, Ui ui, Storage storage) {
    ui.showLine();
    ArrayList<Task> taskArrayList = task.taskArrayList;
    System.out.println("Here are the tasks in your list: ");
    for (int i = 0; i < taskArrayList.size(); i++) {
      System.out.printf("%d.%s\n", i + 1, taskArrayList.get(i));
    }
    ui.showLine();
  }

  @Override
  public boolean isExit() {
    return false;
  }
}
