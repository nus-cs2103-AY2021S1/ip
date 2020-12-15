package duke.command;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import duke.exception.InvalidArgumentException;
import duke.misc.Ui;
import duke.task.Task;
import duke.task.TaskList;


public class DeleteCommand extends Command {
    public DeleteCommand(List<String> input) {
        super(input);
    }

    @Override
    public String run(TaskList taskList) throws InvalidArgumentException {
        assert input.size() >= 2: "DeleteCommand.run(): input must have at least 2 words";

        List<String> indices = new ArrayList<>(input);
        indices.remove(0);
        Integer[] indicesArray = indices.stream()
                .map(s -> Integer.parseInt(s))
                .distinct()
                .sorted(Comparator.reverseOrder())
                .toArray(Integer[]::new);
        for (int i : indicesArray) {
            if (i <= 0 || i > taskList.count()) {
                throw new InvalidArgumentException("Out of range indices");
            }
        }

        List<String> listOfTask = new ArrayList<>();

        for (int index : indicesArray) {
            listOfTask.add(taskList.getTask(index).toString());
            taskList.removeTask(index);

        }
        return Ui.answerDelete(listOfTask, taskList.count());
    }
}
