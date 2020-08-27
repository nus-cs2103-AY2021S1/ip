class DoneEmptyBodyException extends EmptyBodyException {
    DoneEmptyBodyException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! Empty done is invalid.";
    }
}