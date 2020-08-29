package duke.dependencies.parser;

import duke.Duke;
import duke.dependencies.dukeexceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void getExecutable() {
    }

    @Test
    void parseAndCheck() {
    }

    @Test
    void test_authenthicateUser_correct_password() {
        Controller c = Controller.initController();
        c.savedUserPw("Chciken nuggets");
        try {
            Parser p = Parser.authenthicateUser("Chciken nuggets");
            assertEquals(1,1);
        } catch (DukeException e) {
            fail();
        }
    }
    @Test
    void test_authenthicateUser_wrong_password() {
        Controller c = Controller.initController();
        c.savedUserPw("Chicken nuggets");
        try {
            Parser p = Parser.authenthicateUser("Chciken nuggets");
            fail();
        } catch (DukeException e) {
            assertEquals(1,1);
        }
    }

}