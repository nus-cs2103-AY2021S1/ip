import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.exception.DukeFileNotFoundException;
import duke.exception.DukeIoException;
import duke.exception.InvalidInstructionFormatException;
import duke.exception.InvalidInstructionLengthException;
import duke.exception.InvalidTaskIndexException;
import duke.exception.MissingFieldException;
import duke.exception.TaskDoneException;
import duke.exception.UnknownInstructionException;
import stub.DukeExceptionStub;

public class DukeExceptionTest {
    @Test
    public void mainExceptionTest() {
        DukeException exception = new DukeExceptionStub();
        assertEquals(exception.toString(),
                "DukeException: "
                        + DukeExceptionStub.class.getName() + "\n"
                        + "TESTING" + "\n"
                        + "See \"help\" for more");
        assertEquals(exception.guiString(), "TESTING");
    }

    @Test
    public void fileNotFoundExceptionTest() {
        DukeException exception = new DukeFileNotFoundException("TESTING");
        assertEquals(exception.toString(),
                "DukeException: "
                        + DukeFileNotFoundException.class.getName() + "\n"
                        + "File cannot be found! See the following:\n"
                        + "TESTING" + "\n"
                        + "See \"help\" for more");
    }

    @Test
    public void ioExceptionTest() {
        DukeException exception = new DukeIoException("TESTING");
        assertEquals(exception.toString(),
                "DukeException: "
                        + DukeIoException.class.getName() + "\n"
                        + "IOException:\n"
                        + "TESTING" + "\n"
                        + "See \"help\" for more");
    }

    @Test
    public void invalidInstructionFormatExceptionTest() {
        DukeException exception = new InvalidInstructionFormatException();
        assertEquals(exception.toString(),
                "DukeException: "
                        + InvalidInstructionFormatException.class.getName() + "\n"
                        + "Instruction format is incorrect! Please check!" + "\n"
                        + "See \"help\" for more");
    }

    @Test
    public void invalidInstructionLengthExceptionTest() {
        DukeException exception = new InvalidInstructionLengthException();
        assertEquals(exception.toString(),
                "DukeException: "
                        + InvalidInstructionLengthException.class.getName() + "\n"
                        + "The length of the Instruction is wrong!" + "\n"
                        + "See \"help\" for more");
    }

    @Test
    public void invalidTaskIndexExceptionTest() {
        DukeException exception = new InvalidTaskIndexException();
        assertEquals(exception.toString(),
                "DukeException: "
                        + InvalidTaskIndexException.class.getName() + "\n"
                        + "Task Number is invalid! Please check!" + "\n"
                        + "See \"help\" for more");
    }

    @Test
    public void missingFieldExceptionTest() {
        DukeException exception = new MissingFieldException();
        assertEquals(exception.toString(),
                "DukeException: "
                        + MissingFieldException.class.getName() + "\n"
                        + "One or more of the required fields are missing!" + "\n"
                        + "See \"help\" for more");
    }

    @Test
    public void taskDoneExceptionTest() {
        DukeException exception = new TaskDoneException();
        assertEquals(exception.toString(),
                "DukeException: "
                        + TaskDoneException.class.getName() + "\n"
                        + "The task is already completed!" + "\n"
                        + "See \"help\" for more");
    }

    @Test
    public void unknownInstructionExceptionTest() {
        DukeException exception = new UnknownInstructionException();
        assertEquals(exception.toString(),
                "DukeException: "
                        + UnknownInstructionException.class.getName() + "\n"
                        + "Instruction is unknown! Please check!" + "\n"
                        + "See \"help\" for more");
    }
}
