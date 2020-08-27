class InvalidDateException extends Exception {
    InvalidDateException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! Date is invalid.";
    }
}