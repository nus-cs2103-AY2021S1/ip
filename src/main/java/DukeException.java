class DukeException extends Exception {
    private String error;

    DukeException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return String.format("OOPS! %s", error);
    }
}
