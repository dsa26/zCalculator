import java.util.HashMap;

public class Date {

    public int Minutes;
    public int Hours;
    public int Days;
    public int Months;
    public int Years;
    public TimeZones TimeZone;

    public Date(int mn, int hh, int dd, int mo, int yy, TimeZones tz) {
        this.Minutes = mn;
        this.Hours = hh;
        this.Days = dd;
        this.Months = mo;
        this.Years = yy;
        this.TimeZone = tz;
        if (!checkValidity())
            throw new IllegalArgumentException("Invalid Date parameters");
    }

    public Date(int dd, int mo, int yy, TimeZones tz) {
        this.Minutes = 0;
        this.Hours = 0;
        this.Days = dd;
        this.Months = mo;
        this.Years = yy;
        this.TimeZone = tz;
        if (!checkValidity())
            throw new IllegalArgumentException("Invalid Date parameters");
    }

    public Date(int mn, int hh, TimeZones tz) {
        this.Minutes = mn;
        this.Hours = hh;
        this.Days = 0;
        this.Months = 0;
        this.Years = 0;
        this.TimeZone = tz;
        if (!checkValidity())
            throw new IllegalArgumentException("Invalid Date parameters");
    }

    private boolean checkValidity() {
        if (this.Minutes < 0 || this.Minutes > 59)
            return false;
        if (this.Hours < 0 || this.Hours > 23)
            return false;
        if (this.Days < 0 || this.Days > 31)
            return false;
        if (this.Months < 0 || this.Months > 12)
            return false;
        return true;
    }

    public static class RelativeDate {

        public int Minutes;
        public int Hours;
        public int Days;
        public int Months;
        public int Years;

        public RelativeDate(int mn, int hh, int dd, int mo, int yy) {
            this.Minutes = mn;
            this.Hours = hh;
            this.Days = dd;
            this.Months = mo;
            this.Years = yy;
            if (!checkValidity())
                throw new IllegalArgumentException("Invalid RelativeDate parameters");
        }

        public RelativeDate(int mn, int hh) {
            this.Minutes = mn;
            this.Hours = hh;
            this.Days = 0;
            this.Months = 0;
            this.Years = 0;
            if (!checkValidity())
                throw new IllegalArgumentException("Invalid RelativeDate parameters");
        }

        public RelativeDate(int dd, int mo, int yy) {
            this.Minutes = 0;
            this.Hours = 0;
            this.Days = dd;
            this.Months = mo;
            this.Years = yy;
            if (!checkValidity())
                throw new IllegalArgumentException("Invalid RelativeDate parameters");
        }

        private boolean checkValidity() {
            if (this.Minutes < -59 || this.Minutes > 59)
                return false;
            if (this.Hours < -23 || this.Hours > 23)
                return false;
            if (this.Days < -31 || this.Days > 31)
                return false;
            if (this.Months < -12 || this.Months > 12)
                return false;
            return true;
        }
    }

    public static enum TimeZones {
        AmericaLos_Angeles,
        AmericaChicago,
        AmericaNew_York,
        EuropeLondon,
        AfricaAccra,
        EuropeBerlin,
        EuropeAthens,
        EuropeKyiv,
        AsiaAmman,
        AsiaDubai,
        AsiaKolkata,
        AsiaShanghai,
        AsiaSeoul,
        AustraliaSydney,
    }

    // AI suggested double brace syntax, which I then looked up and understood
    // Used AI to figure out the proper syntax for the declaring an array consisting
    // of custom objects

    private static final HashMap<TimeZones, RelativeDate[]> tzOffsets = new HashMap<TimeZones, RelativeDate[]>() {
        {
            put(TimeZones.AmericaLos_Angeles, new RelativeDate[] { new RelativeDate(0, -8), new RelativeDate(0, -7) });
            put(TimeZones.AmericaChicago, new RelativeDate[] { new RelativeDate(0, -6), new RelativeDate(0, -5) });
            put(TimeZones.AmericaNew_York, new RelativeDate[] { new RelativeDate(0, -5), new RelativeDate(0, -4) });
            put(TimeZones.EuropeLondon, new RelativeDate[] { new RelativeDate(0, 0), new RelativeDate(0, 1) });
            put(TimeZones.AfricaAccra, new RelativeDate[] { new RelativeDate(0, 0), new RelativeDate(0, 0) });
            put(TimeZones.EuropeBerlin, new RelativeDate[] { new RelativeDate(0, 1), new RelativeDate(0, 2) });
            put(TimeZones.EuropeAthens, new RelativeDate[] { new RelativeDate(0, 2), new RelativeDate(0, 3) });
            put(TimeZones.EuropeKyiv, new RelativeDate[] { new RelativeDate(0, 2), new RelativeDate(0, 3) });
            put(TimeZones.AsiaAmman, new RelativeDate[] { new RelativeDate(0, 3), new RelativeDate(0, 3) });
            put(TimeZones.AsiaDubai, new RelativeDate[] { new RelativeDate(0, 4), new RelativeDate(0, 4) });
            put(TimeZones.AsiaKolkata, new RelativeDate[] { new RelativeDate(30, 5), new RelativeDate(30, 5) });
            put(TimeZones.AsiaShanghai, new RelativeDate[] { new RelativeDate(0, 8), new RelativeDate(0, 8) });
            put(TimeZones.AsiaSeoul, new RelativeDate[] { new RelativeDate(0, 9), new RelativeDate(0, 9) });
            put(TimeZones.AustraliaSydney, new RelativeDate[] { new RelativeDate(0, 10), new RelativeDate(0, 11) });
        }
    };

    // private static final HashMap<TimeZones, Date[]> tzDST = newHashMap<TimeZones,
    // Date[]>()
    // {
    // {

    // }
    // }
    //
    // This is when I realized that America switches it on SUNDAYS and not on a
    // fixed date, and other countries do (slightly less) weird things too
    // so I gave up on implementing DST support

    private Date fixUTC() {
        RelativeDate offset = tzOffsets.get(this.TimeZone)[1];
        return this.addTime(offset);
    }

    public long unix() {
        Date epoch = new Date(0, 0, 1, 1, 1970, TimeZones.EuropeLondon);
        RelativeDate diff = this.difference(epoch);
        return diff.Minutes * 60 + diff.Hours * 3600 + (diff.Days - 1) * 86400 + diff.Months * 2592000
                + diff.Years * 31536000;
    }

    private static final int[] monthDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    private static int calculateTotalDays(int month) {
        for (int i = 0; i < month - 1; i++) {
            month += monthDays[i];
        }
    }

    public RelativeDate difference(Date secondDate) {
        RelativeDate diff = new RelativeDate(0, 0, 0, 0, 0);
        diff.Minutes = this.Minutes - secondDate.Minutes;
        if (diff.Minutes < 0) {
            diff.Minutes += 60;
            diff.Hours -= 1;
        }
        diff.Hours = this.Hours - secondDate.Hours;
        if (diff.Hours < 0) {
            diff.Hours += 24;
            diff.Days -= 1;
        }
        diff.Days = this.Days - secondDate.Days;
        if (diff.Days < 0) {
            diff.Days += monthDays[this.Months - 2];
            diff.Months -= 1;
        }
        diff.Months = this.Months - secondDate.Months;
        if (diff.Months < 0) {
            diff.Months += 12;
            diff.Years -= 1;
        }
        diff.Years = this.Years - secondDate.Years;
        if (diff.Years < 0) {
            return secondDate.difference(this);
        }
        return diff;
    }

    public Date addTime(RelativeDate diff) {
        Date newDate = this;
        newDate.Minutes += diff.Minutes;
        if (newDate.Minutes >= 60) {
            newDate.Hours += 1;
            newDate.Minutes -= 60;
        }
        newDate.Hours += diff.Hours;
        if (newDate.Hours >= 24) {
            newDate.Days += 1;
            newDate.Hours -= 24;
        }
        newDate.Days += diff.Days;
        if (newDate.Days > monthDays[newDate.Months - 1]) {
            newDate.Months += 1;
            newDate.Days -= monthDays[newDate.Months - 2];
        }
        newDate.Months += diff.Months;
        if (newDate.Months > 12) {
            newDate.Years += 1;
            newDate.Months -= 12;
        }
        newDate.Years += diff.Years;
        return newDate;
    }
}
