package rogue.logic.directives;

import rogue.model.argument.Action;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionTest {
    @Test
    public void getAction_validAction_success() {
        assertEquals(Action.getAction("help"), Action.HELP);
        assertEquals(Action.getAction("event"), Action.ADD_EVENT);
        assertEquals(Action.getAction("todo"), Action.ADD_TODO);
        assertEquals(Action.getAction("deadline"), Action.ADD_DEADLINE);
        assertEquals(Action.getAction("list"), Action.LIST);
        assertEquals(Action.getAction("done"), Action.MARK_AS_DONE);
        assertEquals(Action.getAction("delete"), Action.DELETE);
        assertEquals(Action.getAction("find"), Action.FIND);
    }

    @Test
    public void getAction_invalidAction_returnInvalid() {
        assertEquals(Action.getAction(""), Action.INVALID);
        assertEquals(Action.getAction("test"), Action.INVALID);
        assertEquals(Action.getAction("123"), Action.INVALID);
    }
}
