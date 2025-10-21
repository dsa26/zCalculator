public class Date extends RelativeDate {
    // int Unix can only represent until 2038 -- have to use long after that

    /*
     * Overloaded constructors
     */

    public Date(int mn, int hh, int dd, int mo, int yy) {
        super(mn, hh, dd, mo, yy); // Since relativeDate already has constructors, we can use the same ones
        // The only difference is the year, which is handled by the calculateComponents
        // and calculateUnix methods
        // We do have to redefine constructors, however, since Java only automatically
        // calls constructors when they take no arguments
    }

    public Date(String mn, String hh, String dd, String mo, String yy) {
        super(mn, hh, dd, mo, yy);
    }

    public Date(int dd, int mo, int yy) {
        super(dd, mo, yy);
    }

    public Date(int mn, int hh) {
        super(mn, hh);
    }

    public Date(int unix) {
        super(unix);
    }

    public Date(String unix) {
        super(unix);
    }

    /*
     * Private utility functions that help convert between Unix and Component Date
     * forms
     */

    @Override
    protected void calculateComponents() {
        super.calculateComponents();
        this.Years += 1970;
    }

    @Override
    protected void calculateUnix() { // Initially used super method by changing this.Years, but found it easier to
                                     // handle Leap
        this.Years -= 1970;
        super.calculateUnix(); // A more accurate method would require using 1900 and 1968 for last leap
                               // year occurance before 1970
        this.Years += 1970;
    }

    /*
     * Private utility function to verify input validity
     */

    protected boolean checkValidity() {
        this.Days -= 1;
        this.Months -= 1; // Months are valid from 00 to 11, not 01 to 12
        this.Years -= 1970;
        boolean validity = super.checkValidity();
        this.Days += 1;
        this.Months += 1;
        this.Years += 1970;
        return validity;
    }

    public Date add(RelativeDate difference) {
        return new Date(this.getUnix() + difference.getUnix());
    }

    public RelativeDate difference(Date secondDate) {
        return new RelativeDate(Math.abs(this.getUnix() - secondDate.getUnix()));
    }
}
