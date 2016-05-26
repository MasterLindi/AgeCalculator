package at.mvb.tools.calculation;


public class DiffDate {
    private final long years;
    private final long months;
    private final long days;

    public DiffDate(long years, long months, long days) {
        this.years = years;
        this.months = months;
        this.days = days;
    }

    public long getYears() {
        return years;
    }

    public long getMonths() {
        return months;
    }

    public long getDays() {
        return days;
    }

    @Override
    public String toString() {
        return String.format("Differenz: %s Jahre, %s Monate, %s Tage", this.getYears(), this.getMonths(), this.getDays());
    }
}
