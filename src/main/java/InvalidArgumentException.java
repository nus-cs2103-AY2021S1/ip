import org.omg.CORBA.DynAnyPackage.Invalid;

public class InvalidArgumentException extends Exception {
    InvalidArgumentException() {
        super("Sorry, Duke does not recognize your command!");
    }
    InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
 }
