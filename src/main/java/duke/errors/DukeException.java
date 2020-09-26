package duke.errors;

/**
 * this is a DukeException class which is the parents class of all the other exceptions in this package.
 * this is never initialized and therefore is an abstract class and used for polymorphism.
 */
public abstract class DukeException extends Exception {
    /**
     * overrides getMessage of an error
     *
     * @return string of the exception
     */
    public String getMessage() {
        return toString();
    }
}

