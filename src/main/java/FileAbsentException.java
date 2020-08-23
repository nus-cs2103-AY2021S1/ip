public class FileAbsentException extends Exception {
    @Override
    public String toString() {
        return "  TIMETABLE.TXT absent, please add it in the text-ui-test folder!";
    }
}
