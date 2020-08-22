

public class EmptyArgumentException {
    String errorMessage;
    EmptyArgumentException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
