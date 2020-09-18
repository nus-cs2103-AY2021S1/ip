package sparrow.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import sparrow.data.task.Deadline;
import sparrow.data.task.Event;
import sparrow.data.task.TaskList;
import sparrow.data.task.Todo;
import sparrow.data.trivia.VocabList;
import sparrow.data.trivia.Vocabulary;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

public class ListCommandTest {

    @Test
    public void execute() {
        Todo todo = new Todo("I am a todo");
        Deadline deadline = new Deadline("I am a deadline", LocalDate.now());
        Event event = new Event("I am an event", LocalDate.now());

        Vocabulary v1 = new Vocabulary("buccaneer");
        Vocabulary v2 = new Vocabulary("ship");
        Vocabulary v3 = new Vocabulary("kraken");

        TaskList tasks = new TaskList();
        tasks.addTask(todo);
        tasks.addTask(deadline);
        tasks.addTask(event);

        VocabList vocabList = new VocabList();
        vocabList.addVocab(v1);
        vocabList.addVocab(v2);
        vocabList.addVocab(v3);

        Ui ui = new Ui();
        Storage storage = new Storage();

        String taskResult = String.format(ListCommand.TASK_MESSAGE_SUCCESS, ui.taskListToString(tasks.getTasks()));
        ListCommand showTask = new ListCommand("tasks");
        assertEquals(showTask.execute(tasks, vocabList, ui, storage), taskResult);

        String vocabResult = String.format(ListCommand.VOCAB_MESSAGE_SUCCESS,
                ui.vocabListToString(vocabList.getVocabList()));
        ListCommand showVocab = new ListCommand("vocab");
        assertEquals(showVocab.execute(tasks, vocabList, ui, storage), vocabResult);

        ListCommand wrongCommand = new ListCommand("task");
        assertEquals(wrongCommand.execute(tasks, vocabList, ui, storage), ListCommand.HELP_MESSAGE);

    }
}
