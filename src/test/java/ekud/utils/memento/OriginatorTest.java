package ekud.utils.memento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ekud.exceptions.DukeIoException;
import ekud.tasks.Task;
import ekud.tasks.TaskList;
import ekud.utils.Storage;

public class OriginatorTest {
    @Test
    public void test() throws Exception {
        List<Originator.Memento> savedStates = new ArrayList<>();
        TaskList t = new TaskList(new ArrayList<>());

        Storage storageMock = new Storage() {
            @Override
            public void save(ArrayList<Task> tasks) throws DukeIoException {

            }
        };

        t.setObserver(storageMock);


        Originator originator = new Originator();
        t.add(new Task("state1"));
        originator.set(t, "command1");
        t.add(new Task("state2"));
        originator.set(t, "command2");
        savedStates.add(originator.saveToMemento());

        t.add(new Task("state3"));
        originator.set(t, "command3");
        savedStates.add(originator.saveToMemento());

        t.add(new Task("state4"));
        originator.set(t, "command4");

        originator.restoreFromMemento(savedStates.get(1));

        assertEquals(originator.getState().get(2).getDescription(), "state3");
    }
}
