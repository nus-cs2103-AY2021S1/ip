package duke.task;

public enum TaskEnum {

    TODO {
        @Override
        public Task fromCsv(String csv) {
            return ToDo.fromCsv(csv);
        }
    },

    DEADLINE {
        @Override
        public Task fromCsv(String csv) {
            return Deadline.fromCsv(csv);
        }
    },

    EVENT {
        @Override
        public Task fromCsv(String csv) {
            return Event.fromCsv(csv);
        }
    };

    public abstract Task fromCsv(String csv);
}
