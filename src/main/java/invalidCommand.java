public class invalidCommand extends Exception{
    private String errorMessage;
    public invalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }
}
