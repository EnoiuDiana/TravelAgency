package model;

public enum UserType {
    REGULAR() {
        @Override
        public String toString() {
            return "REGULAR";
        }
    },
    ADMIN () {
        @Override
        public String toString() {
            return "ADMIN";
        }
    };
    @Override
    public abstract String toString();
}
