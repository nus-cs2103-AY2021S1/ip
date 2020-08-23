class NoDataFileException  extends Exception {
    public NoDataFileException() {
        super("No data file found, a new data file created!");
    }
}
