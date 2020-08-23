public class WrongDateFormatException extends DukeException {
    WrongDateFormatException(String errorMessage) {
        super(errorMessage);
    }

    WrongDateFormatException(){
        this("Task not saved due to wrong format. ꉂ `o´ ) Please specify the date in the following format: \n" +
                "yyyy-mm-dd i.e. 2020-01-01");
    }
}
