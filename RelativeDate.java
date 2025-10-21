public class RelativeDate {
    public int Minutes;
    public int Hours;
    public int Days;
    public int Months;
    public int Years;
    protected int Unix;

    /*
     * Overloaded constructors
     */

    public RelativeDate(int mn, int hh, int dd, int mo, int yy) {
        this.Minutes = mn;
        this.Hours = hh;
        this.Days = dd;
        this.Months = mo;
        this.Years = yy;
        if (!checkValidity())
            throw new IllegalArgumentException("Invalid Date parameters");
        calculateUnix();
    }

    public RelativeDate(String mn, String hh, String dd, String mo, String yy) {
        this(Integer.parseInt(mn), Integer.parseInt(hh), Integer.parseInt(dd), Integer.parseInt(mo),
                Integer.parseInt(yy));
    }

    public RelativeDate(int dd, int mo, int yy) {
        this(0, 0, dd, mo, yy);
    }

    public RelativeDate(int mn, int hh) {
        this(mn, hh, 0, 0, 0);
    }

    public RelativeDate(int unix) {
        this.Unix = unix;
        calculateComponents();
    }

    public RelativeDate(String unix) {
        this(Integer.parseInt(unix));
    }

    /*
     * Private utility functions that help convert between Unix and Component Date
     * forms
     */

    protected void calculateComponents() {
        int days = this.Unix / 60 / 60 / 24;
        this.Minutes = this.Unix / 60 % 60;
        this.Hours = this.Unix / 60 / 60 % 24;
        int totalDays = 0;
        int yy = 0;
        while (true) {
            this.Years = yy;
            if ((this.Years + 1970) % 4 == 0 && (this.Years + 1970) % 100 != 0 && totalDays + 366 <= days) {
                totalDays += 366;
            } else if (totalDays + 365 <= days) { // To ensure that the totalDays used is accurate for future
                                                  // calculations
                totalDays += 365;
            } else { // Not enough days left for a whole year
                break;
            }
            yy += 1;
        }
        days = days - totalDays;
        this.Months = 0;
        while (monthUnix[this.Months + 1] <= days) {
            if ((this.Years + 1970) % 4 == 0 && (this.Years + 1970) % 100 != 0 && this.Months == 2) {
                days -= 1; // Accounting for leap years
            }
            this.Months++;
        }
        days = days - monthUnix[this.Months];
        this.Days = days;
    }

    protected void calculateUnix() { // Leap years are not fully accurate since it's not known what year the relative
                                     // date is taken from -- Adding 2 years is beneficial, however, since a lot of
                                     // the time it will be taken from 1970, and 1972 is a leap year
        this.Unix = (this.Years) * 365 * 24 * 60 * 60 + ((this.Years + 2) / 4) * 24 * 60 * 60
                - ((this.Years + 70) / 100) * 24 * 60 * 60 + monthUnix[this.Months] * 24 * 60 * 60
                + this.Days * 24 * 60 * 60
                + this.Hours * 60 * 60 + this.Minutes * 60;
    }

    protected static final int[] monthUnix = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 }; // Total dates
                                                                                                        // completed by
                                                                                                        // previous
                                                                                                        // months at the
                                                                                                        // start of each
                                                                                                        // month

    /*
     * Private utility function to verify input validity
     */

    protected boolean checkValidity() {
        if (this.Minutes < 0 || this.Minutes > 59)
            return false;
        if (this.Hours < 0 || this.Hours > 23)
            return false;
        if (this.Days < 0 || this.Days > 31)
            return false;
        if (this.Months < 0 || this.Months > 12)
            return false;
        if (this.Years < 0)
            return false;
        return true;
    }

    public int getUnix() {
        return this.Unix;
    }
}
