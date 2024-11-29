public enum FormTypes {
    POSITIVE("Положительная"),
    NEGATIVE("Отрицательная"),
    NONE("Неопределенная");

    private String rusName;

    FormTypes(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return rusName;
    }
}
