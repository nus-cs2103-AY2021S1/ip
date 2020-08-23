package Exceptions;
public class FileAbsentException extends DukeException {
    @Override
    public String toString() {
        return "  TIMETABLE.TXT absent, please add it in the text-ui-test folder!";
    }
}
