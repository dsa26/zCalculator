import java.util.HashMap;

public class DateTools {

    public class Date {

        public Date(int yy, int mm, int dd, int hh, int hm, TimeZone tz) {
            this.yy = yy;
            this.mm = mm;
            this.dd = dd;
            this.hh = hh;
            this.hm = hm;
            this.tz = tz;
        }
    }

    public static class RelativeDate {

        public RelativeDate(int mn, int hh, int dd, int mo, int yy) {
            this.mn = mn || 0;
            this.hh = hh || 0;
            this.dd = dd || 0;
            this.mo = mo || 0;
            this.yy = yy || 0;
        }
    }

    public static enum TimeZone {
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
    // Used AI to figure out the proper syntax for the declaring an array consisting of custom objects
    private static final HashMap<TimeZone, RelativeDate[]> offsets = new HashMap<TimeZone, RelativeDate[]>() {
        {
            put(TimeZone.AmericaLos_Angeles, new RelativeDate[]{new RelativeDate(0, -8, 0, 0, 0), new RelativeDate(0, -7, 0, 0, 0)});
            put(TimeZone.AmericaChicago, new RelativeDate[]{new RelativeDate(0, -6, 0, 0, 0), new RelativeDate(0, -5, 0, 0, 0)});
            put(TimeZone.AmericaNew_York, new RelativeDate[]{new RelativeDate(0, -5, 0, 0, 0), new RelativeDate(0, -4, 0, 0, 0)});
            put(TimeZone.EuropeLondon, new RelativeDate[]{new RelativeDate(0, 0, 0, 0, 0), new RelativeDate(0, 1, 0, 0, 0)});
            put(TimeZone.AfricaAccra, new RelativeDate[]{new RelativeDate(0, 0, 0, 0, 0), new RelativeDate(0, 0, 0, 0, 0)});
            put(TimeZone.EuropeBerlin, new RelativeDate[]{new RelativeDate(0, 1, 0, 0, 0), new RelativeDate(0, 2, 0, 0, 0)});
            put(TimeZone.EuropeAthens, new RelativeDate[]{new RelativeDate(0, 2, 0, 0, 0), new RelativeDate(0, 3, 0, 0, 0)});
            put(TimeZone.EuropeKyiv, new RelativeDate[]{new RelativeDate(0, 2, 0, 0, 0), new RelativeDate(0, 3, 0, 0, 0)});
            put(TimeZone.AsiaAmman, new RelativeDate[]{new RelativeDate(0, 3, 0, 0, 0), new RelativeDate(0, 3, 0, 0, 0)});
            put(TimeZone.AsiaDubai, new RelativeDate[]{new RelativeDate(0, 4, 0, 0, 0), new RelativeDate(0, 4, 0, 0, 0)});
            put(TimeZone.AsiaKolkata, new RelativeDate[]{new RelativeDate(30, 5, 0, 0, 0), new RelativeDate(30, 5, 0, 0, 0)});
            put(TimeZone.AsiaShanghai, new RelativeDate[]{new RelativeDate(0, 8, 0, 0, 0), new RelativeDate(0, 8, 0, 0, 0)});
            put(TimeZone.AsiaSeoul, new RelativeDate[]{new RelativeDate(0, 9, 0, 0, 0), new RelativeDate(0, 9, 0, 0, 0)});
            put(TimeZone.AustraliaSydney, new RelativeDate[]{new RelativeDate(0, 10, 0, 0, 0), new RelativeDate(0, 11, 0, 0, 0)});
        }
    };
}
