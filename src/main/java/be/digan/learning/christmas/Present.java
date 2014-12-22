package be.digan.learning.christmas;

/**
 * Created by david on 12/22/14.
 */
public class Present {
    String what;
    String forWhom;

    public String getForWhom() {
        return forWhom;
    }

    public String getWhat() {
        return what;
    }

    public Present(String what, String forWhom) {
        this.what = what;
        this.forWhom = forWhom;
    }
}
