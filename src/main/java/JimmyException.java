public class JimmyException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public JimmyException(ErrorMessage error) {
        super(error.getErrorMessage());
    }
}