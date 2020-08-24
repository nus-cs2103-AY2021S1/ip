class NoDataException  extends Exception {
    public NoDataException() {
        super("No data file found, a new data file created!");
    }
}