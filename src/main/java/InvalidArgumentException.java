import org.omg.CORBA.DynAnyPackage.Invalid;

public class InvalidArgumentException{
    String errorMessage;
    InvalidArgumentException() {
        this.errorMessage = "Sorry, Duke does not recognize your command!";
    }
    InvalidArgumentException(String errorMessage) {
        this.errorMessage  = errorMessage;
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
