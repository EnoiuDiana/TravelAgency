package model;

public enum Status {
    NOT_BOOKED(){
        @Override
        public String toString() {
            return "NOT BOOKED";
        }
    },
    IN_PROGRESS(){
        @Override
        public String toString() {
            return "IN PROGRESS";
        }
    },
    BOOKED(){
        @Override
        public String toString() {
            return "BOOKED";
        }
    };

    @Override
    public abstract String toString();
}
