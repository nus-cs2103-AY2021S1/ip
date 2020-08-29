package duke.dependencies.parser;

import duke.Duke;
import duke.dependencies.dukeexceptions.DukeException;
import duke.dependencies.executable.CommandType;
import duke.dependencies.executable.Executable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void getExecutable() {
    }

    @Test
    void test_parseAndCheck_valid_command() {
        try {
            Executable e = Parser.parseAndCheck("todo run").getExecutable();
            Executable g = Parser.parseAndCheck("clear data").getExecutable();

            assertAll(()-> assertEquals(g.getType(), CommandType.AUTHCHECK),
                    () -> assertEquals(e.getType(), CommandType.ADD));
        } catch (DukeException e) {
            fail();
        }
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