package app.shears.mvp.cores.enums;

public enum Frequency {
    ONCE("Не повторяется"),
    DAILY("Ежедневно"),
    WEEKLY("Еженедельно"),
    MONTHLY("Ежемесячно");

    private String name;

    Frequency(String name) {
        this.name = name;
    }

    public static Frequency getDefault() {
        return ONCE;
    }

    public String getName() {
        return name;
    }

}
