package duke.command;

import static duke.TestUtils.createDoneToDo;

import java.time.LocalDate;

import duke.TaskList;
import duke.task.Task;
import duke.ui.Message;

public class TaskListDouble extends TaskList {
    @Override
    public void add(Task task) {
        // nothing
    }

    @Override
    public Task markAsDone(int position) {
        return createDoneToDo();
    }

    @Override
    public Task delete(int position) {
        return createDoneToDo();
    }

    @Override
    public Message showList() {
        return new Message("No tasks found.");
    }

    @Override
    public Message showList(LocalDate date) {
        return new Message("No tasks found.");
    }

    @Override
    public Message find(String keyword) {
        return new Message("No tasks found.");
    }

}
