/**
 *  My name : Mansour Ahmed Aldahri
 *  My email: Mansour.try@gmail.com
 * section number : Cpcs-204-GA
 *
 * @author Mn9_1
 */
public class Center {

    private int centerID;
    private Practitioner head;
    private int capicity;

    public Center() {
        head = null;
    }

    public Center(int centerID, Practitioner head) {
        this.centerID = centerID;
        this.head = head;
    }

    public Center(int centerID, Practitioner head, int capicity) {
        this.centerID = centerID;
        this.head = head;
        this.capicity = capicity;
    }

    public Center(int capicity) {
        this.capicity = capicity;
    }

    public int getCenterID() {
        return centerID;
    }

    public void setCenterID(int centerID) {
        this.centerID = centerID;
    }

    public Practitioner getHead() {
        return head;
    }

    public void setHead(Practitioner head) {
        this.head = head;
    }

    public int getCapicity() {
        return capicity;
    }

    public void setCapicity(int capicity) {
        this.capicity = capicity;
    }
// in really i really dont need to use this method

    private boolean searchById(Practitioner p, String data) {
        Practitioner helpPtr = p;
        while (helpPtr != null) {
            if (helpPtr.getParctID().equalsIgnoreCase(data)) {
                return true;
            }
            helpPtr = helpPtr.getNext();
        }
        return false;
    }

    public void deleteByID(String data) {
        Practitioner hpot = head;

        if (hpot.getNext().getParctID().equalsIgnoreCase(data)) {

        } else {

            Practitioner helpPtr = head;

            while (helpPtr.getNext() != null) {
                if (helpPtr.getNext().getParctID().equalsIgnoreCase(data)) {

                    helpPtr.setNext(helpPtr.getNext().getNext());
                    break;
                }
                helpPtr = helpPtr.getNext();
            }
        }
    }

    public Practitioner addPractitioner(String parctID, String Fname, String lName, String Status, int center, Practitioner next) { //Receives reference variable and data

        if (head == null) {
            head = new Practitioner(parctID, Fname, lName, Status, center, next);
            return head;
        } else {
            Practitioner helpptr = head;
            while (helpptr.getNext() != null) {
                helpptr = helpptr.getNext();
            }

            Practitioner newnode = new Practitioner(parctID, Fname, lName, Status, center, helpptr.getNext());
            helpptr.setNext(newnode);

        }

        return head;
    }

    public void deletePractitionersBasedOnStatus(String status) {

        Practitioner hpot = head;

        if (hpot.getNext().getParctID().equalsIgnoreCase(status)) {

        } else {

            Practitioner helpPtr = head;

            while (helpPtr.getNext() != null) {
                if (helpPtr.getNext().getStatus().equalsIgnoreCase(status)) {

                    helpPtr.setNext(helpPtr.getNext().getNext());
                    break;
                }
                helpPtr = helpPtr.getNext();
            }
        }
    }

}
