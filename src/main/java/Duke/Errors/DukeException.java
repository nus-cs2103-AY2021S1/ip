package Duke.Errors;

/**
 * this is a DukeException class which is the parents class of all the other exceptions in this package.
 * this is never intiilized and therefore is an abstract class and used for polymorphism.
 */
abstract public class DukeException extends Exception {
    /**
     * overrides getMessage of an error
     *
     * @return string of the exception
     */
    public String getMessage(){
            return toString();
    }
}

