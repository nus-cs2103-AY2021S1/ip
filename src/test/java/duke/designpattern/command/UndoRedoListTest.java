package duke.designpattern.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class UndoRedoListTest {

    private final UndoRedoList list = new UndoRedoList();
    private final ReversibleExecutableStud stud1 = new ReversibleExecutableStud();
    private final ReversibleExecutableStud stud2 = new ReversibleExecutableStud();
    private final ReversibleExecutableStud stud3 = new ReversibleExecutableStud();

    @Test
    void add_null_exceptionThrown() {
        try {
            list.add(null);
            fail();
        } catch (NullPointerException e) {
            // pass, nothing to do
        }
    }

    void oneItemTest(ReversibleExecutableStud stud1) {
        // Case: 1 item
        assertTrue(stud1.isExecuted());

        // Undo
        assertFalse(list.redo()); // Already at latest
        assertTrue(list.undo()); // undo
        assertFalse(stud1.isExecuted());
        assertFalse(list.undo()); // Already at earliest

        // Redo
        assertFalse(list.undo()); // Already at earliest
        assertTrue(list.redo()); // redo
        assertTrue(stud1.isExecuted());
        assertFalse(list.redo()); // Already at latest
    }

    void twoItemTest(ReversibleExecutableStud stud1,
                     ReversibleExecutableStud stud2,
                     ReversibleExecutableStud notInList) {
        // Pre-check
        assertTrue(stud1.isExecuted());
        assertTrue(stud2.isExecuted());
        assertFalse(notInList.isExecuted());

        // Perform Undo
        assertFalse(list.redo()); // Already at latest
        assertTrue(list.undo()); // undo
        assertFalse(stud2.isExecuted());
        assertTrue(list.undo()); // undo
        assertFalse(stud1.isExecuted());
        assertFalse(list.undo()); // Already at earliest

        assertFalse(notInList.isExecuted()); // Check notInList stud is left untouched

        // Perform redo
        assertFalse(list.undo()); // Already at earliest
        assertTrue(list.redo()); // redo
        assertTrue(stud1.isExecuted());
        assertTrue(list.redo()); // redo
        assertTrue(stud2.isExecuted());
        assertFalse(list.redo()); // Already at latest


        assertFalse(notInList.isExecuted()); // Check notInList stud is left untouched
    }

    @Test
    void undo_redo_test() {
        // Case: 0 item
        assertFalse(list.undo());
        assertFalse(list.redo());

        // Case: 1 item
        list.add(stud1);
        oneItemTest(stud1);

        // Case: 2 items
        list.add(stud2);
        // Check stud1 and stud2 is changed, stud3 is left unchanged
        stud3.reverse();
        twoItemTest(stud1, stud2, stud3);
        stud3.execute();

        // Case: 2 items, undo then add
        assertTrue(list.undo()); // undo
        list.add(stud3); // Remove stud2 + add stud3
        // Check stud1 and stud3 is changed, stud2 is left unchanged
        stud2.reverse();
        twoItemTest(stud1, stud3, stud2);
        stud2.execute();
    }

}
