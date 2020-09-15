package ekud.utils.memento;

import org.junit.jupiter.api.Test;
import ekud.tasks.Task;
import ekud.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;

public class OriginatorTest {
    @Test
    public void test() throws Exception {
        List<Originator.Memento> savedStates = new ArrayList<>();
        TaskList t = new TaskList(new ArrayList<>());

        t.setObserver(null);

        Originator originator = new Originator();
        t.add(new Task("state1"));
        originator.set(t, "command1");
        t.add(new Task("state2"));
        originator.set(t, "command2");
        savedStates.add(originator.saveToMemento());

        t.add(new Task("state3"));
        originator.set(t, "command3");
        // We can request multiple mementos, and choose which one to roll back to.
        savedStates.add(originator.saveToMemento());

        t.add(new Task("state4"));
        originator.set(t, "command4");

        originator.restoreFromMemento(savedStates.get(1));
    }
}
