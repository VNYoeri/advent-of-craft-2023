package ci.dependencies;

public enum IntermediateStatus {
    SUCCESS {
        @Override
        public boolean finishedSuccessfully() {
            return true;
        }
    },
    FAILURE {
        @Override
        public boolean failed() {
            return true;
        }
    };

    public boolean finishedSuccessfully() {
        return false;
    }
    public boolean failed() {
        return false;
    }
}
