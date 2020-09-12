package duke;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

class ParserTest {
    private static Parser parser;

    @BeforeAll
    static void initAll() {
        Ui.init();
    }

    @BeforeEach
    void init() {
        parser = new Parser(new TaskList());
    }

    @org.junit.jupiter.api.Test
    void testRunDeadline1() {
        parser.run("deadline return book /by 2019-10-15");
    }

    @org.junit.jupiter.api.Test
    void testRunEvent1() {
        parser.run("event return book /at 2019-10-15");
    }

    @org.junit.jupiter.api.Test
    void testRunEvent2() {
        System.out.println(parser.run("event return book /between 2019-10-15 and 2019-10-16"));
    }

    @org.junit.jupiter.api.Test
    void testRunTodo1() {
        parser.run("todo return book");
    }


}
