
/**
 * My name : Mansour Ahmed Aldahri
 *  My email: Mansour.try@gmail.com
 *  ID : 2036538
 * section number : Cpcs-204-GA
 * title : KAU Vaccination Centers Management System
 * date : 07/10/2021
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainProgram {

    static int centerindex = 0;
    static int numcenter = 0;
    static int maxcap = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("intialInformation.txt");
        File output = new File("output.txt");
        File input2 = new File("commands.txt");
        Practitioner helpptr;
        Practitioner hpo;
        int u = 0;
        int g = 0;
        //cheack if the file exist
        if (!(input.exists())) {
            System.out.println("File (input) not Found !!");
            System.exit(0);

        }
        if (!(input2.exists())) {
            System.out.println("File(input2) not Found !!");
            System.exit(0);

        }
        //make a pen to write 

        PrintWriter pen = new PrintWriter(output);

        // make to reader to read from files
        Scanner read = new Scanner(input);
        Scanner read2 = new Scanner(input2);

        // make linked list 
        int size = read.nextInt();
        Center[] center = new Center[size];
        // make array to put the centers
        String array[] = new String[center.length];

        Practitioner head = null;
        String command = null;
        do {
            command = read2.next();

            if (command.equalsIgnoreCase("STARTUP")) {
                pen.print("	Welcome to the KAU Vaccination Centers Management System\n"
                        + "       ---------------------------------------------------------");

                //store the data
                for (int i = 0; i < center.length; i++) {
                    center[centerindex] = new Center(centerindex + 1, head, read.nextInt());
                    centerindex++;
                }
                pen.println("\nThe vaccination centers are:");

                // print the centers names
                for (int i = 0; i < center.length; i++) {
                    array[i] = read.next();
                    pen.println("  " + array[i].replaceAll("_", " "));

                }

                // nested loop to add practitioner
                for (int i = 0; i < centerindex; i++) {
                    numcenter++;
                    for (int j = 0; j < center[i].getCapicity(); j++) {

                        center[i].addPractitioner(read.next(), read.next(), read.next(), "Exist", numcenter, head);
                    }
                }
                //to know which capicity has high capicity
                for (int i = 0; i < center.length; i++) {
                    if (maxcap < center[i].getCapicity()) {
                        maxcap = center[i].getCapicity();
                    }
                }

                pen.flush();
            }

            if (command.equalsIgnoreCase("DISPLAY_ALL_CENTERS")) {
                pen.print("\n"
                        + "The first distribution for health practitioners among the vaccination centers \n"
                        + "===================================================================================================\n\n");

                // using method display, to display all practioner information
                display(center, pen, array, maxcap);

                pen.print("===================================================================================================\n\n");

                pen.flush();
            }

            if (command.equalsIgnoreCase("NUM_PRACTIONERS")) {
                //this method to know how many practoinor in every center
                int num_p = read2.nextInt();
                int co = 0;
                helpptr = center[num_p - 1].getHead();
                while (helpptr != null) {
                    co++;
                    helpptr = helpptr.getNext();

                }
                pen.println("Number of practitioners in center " + num_p + ":" + " " + co);
                pen.print("===================================================================================================\n\n");

                pen.flush();
            }

            if (command.equalsIgnoreCase("DISPLAY_ALL_BASED_ON_STATUS")) {
                //we need help pointer to traversing
                Practitioner helppr;

                String sts = read2.next();

                // we need to counter to see if no one in list in spasfic status 
                int cr = 0;
                for (int i = 0; i < maxcap; i++) {

                    for (int j = 0; j < center.length; j++) {
                        int count = 0;
                        helppr = center[j].getHead();
                        while (count != i) {
                            if (helppr != null) {
                                if ((helppr.getStatus().equalsIgnoreCase(sts))) {
                                    cr++;
                                }

                                helppr = helppr.getNext();
                            }
                            count++;
                        }

                        if (helppr != null) {

                        } else {

                        }

                    }

                    pen.flush();
                }
                if (cr == 0) {
                    pen.print("Not found any practitioners of the status  " + sts);
                    pen.print("\n===================================================================================================\n\n");
                } else {
                    pen.print("	The practitioners of status " + sts + " are\n        -------------------------------------\n\n");
                    display(center, pen, array, maxcap);
                    pen.print("===================================================================================================\n\n");

                }

                pen.flush();
            }
            if (command.equalsIgnoreCase("DISPLAY_BASED_ON_STATUS")) {

                int co = 0;
                String sts = read2.next();
                int centerid = read2.nextInt();
                for (int i = 0; i < centerid; i++) {
                    Practitioner helppr = center[centerid - 1].getHead();
                    for (int j = 0; j < center[centerid - 1].getCapicity(); j++) {

                        if (helppr != null) {

                            if (helppr.getStatus().equalsIgnoreCase(sts)) {
                                co++;
                            }

                            helppr = helppr.getNext();
                        }
                    }

                }
                if (co == 0) {

                    pen.println("Not found any practitioners of the status " + sts + " in center " + centerid);
                    pen.print("===================================================================================================\n\n");
                } else {
                    Practitioner hpot = center[centerid - 1].getHead();

                    pen.println("\tThe practitioners of status " + sts + " in center " + centerid + " are\n"
                            + "        -------------------------------------------------\n\n\t\t\t" + array[centerid - 1] + "\n\n-----------------------------------------------------");
                    while (hpot != null) {

                        pen.println("\t\t" + hpot.getParctID() + " " + hpot.getFname() + " " + hpot.getlName() + " , " + hpot.getStatus());
                        hpot = hpot.getNext();
                    }
                    pen.println("=======================================================\n");

                }

            }
            pen.flush();
            if (command.equalsIgnoreCase("LEAVE_THE_JOB")) {
                Practitioner hpot;
                String pr_id = read2.next();
                for (int i = 0; i < maxcap; i++) {

                    for (Center center1 : center) {
                        int count = 0;
                        hpot = center1.getHead();
                        while (count != i) {
                            if (hpot != null) {
                                if (hpot.getParctID().equalsIgnoreCase(pr_id)) {

                                    hpot.setStatus("Left");
                                }
                                hpot = hpot.getNext();
                            }
                            count++;
                        }

                        if (hpot != null) {

                        } else {

                        }
                    }

                }
                Practitioner hpott = center[0].getHead();
                pen.println("The practitioner of id " + pr_id + " is Left\n");
                pen.println("\tThe practitioners of center " + hpott.getCenter() + " are\n"
                        + "        -------------------------------------------------\n\n\t\t\t" + array[hpott.getCenter() - 1] + "\n\n-----------------------------------------------------");
                while (hpott != null) {

                    pen.println("\t\t" + hpott.getParctID() + " " + hpott.getFname() + " " + hpott.getlName() + " , " + hpott.getStatus());
                    hpott = hpott.getNext();
                }
                pen.println("=======================================================\n");
                pen.flush();
            }
            if (command.equalsIgnoreCase("MOVE")) {

                Practitioner hpot;

                int cent = 0;
                //prt is practioner id we want to searching for
                String prt = read2.next();
                //ce is center we want to move practioner to
                int ce = read2.nextInt();

                for (int i = 0; i < maxcap; i++) {

                    for (int j = 0; j < center.length; j++) {

                        int count = 0;
                        hpot = center[j].getHead();

                        while (count != i) {
                            if (hpot != null) {

                                hpot = hpot.getNext();

                            }

                            count++;

                        }

                        if (hpot != null) {

                            if (hpot.getParctID().equalsIgnoreCase(prt)) {
                                center[ce - 1].addPractitioner(prt, hpot.getFname(), hpot.getlName(), "Moved", ce, null);
                                center[ce - 1].setCapicity(center[ce - 1].getCapicity() + 1);
                                center[hpot.getCenter() - 1].deleteByID(prt);
                                center[hpot.getCenter() - 1].setCapicity(center[hpot.getCenter() - 1].getCapicity() - 1);

                                break;

                            }

                        } else {

                        }

                    }

                }
                pen.println("\tThe Practitioner of id " + prt + " is moved to center " + ce);
                pen.println("\t------------------------------------------------");

                display(center, pen, array, maxcap);
                pen.println("===================================================================================================\n");

            }
            pen.flush();
            // int num_pr;
            if (command.equalsIgnoreCase("DISPLAY")) {
                //num_pr number of center we want to display

                int num_pr = read2.nextInt();
                pen.println("\tThe practitioners of center " + num_pr + " are");
                pen.print("\t-------------------------------------------------\n\n");
                pen.print("\t\t\t" + array[num_pr - 1] + "\n\n");
                pen.println("-----------------------------------------------------");
                helpptr = center[num_pr - 1].getHead();
                while (helpptr != null) {
                    pen.println("\t\t" + helpptr.toString());
                    helpptr = helpptr.getNext();

                }

                pen.print("=======================================================\n\n");

                pen.flush();

            }
            if (command.equalsIgnoreCase("REMOVE_ALL_LEFT_PRACTITIONERS")) {
                pen.print("\tAll left Practitioners are moved to new linked list\n");
                pen.print("\t---------------------------------------------------\n");
                Practitioner hpot;
                // we make new linked list to store all left practioner in it
                Center fourth = new Center();

                for (int i = 0; i < center[0].getCapicity(); i++) {

                    for (int j = 0; j < center.length; j++) {

                        int count = 0;
                        hpot = center[j].getHead();

                        while (count != i) {
                            if (hpot != null) {

                                hpot = hpot.getNext();

                            }
                            count++;
                        }

                        if (hpot != null) {
                            if (hpot.getStatus().equalsIgnoreCase("Left")) {
                                fourth.addPractitioner(hpot.getParctID(), hpot.getFname(), hpot.getlName(), hpot.getStatus(), 4, null);
                                center[hpot.getCenter() - 1].deletePractitionersBasedOnStatus("Left");

                                pen.print("" + "   " + hpot.getParctID() + " " + hpot.getFname() + " " + hpot.getlName() + " , " + hpot.getStatus() + "  \n");

                                break;
                            }

                        } else {

                        }

                    }

                }
                pen.println("\t\n\tThe remaining practitioners After remove the practitioners of status left ");
                pen.println("\t------------------------------------------------------------------------");

                display(center, pen, array, maxcap);
                pen.println("===================================================================================================\n");

            }
            if (command.equalsIgnoreCase("DELETE_CENTER")) {
                Practitioner hpot;
                // dele is the number of center we want to delete 
                int dele = read2.nextInt();

                for (int i = 0; i < maxcap; i++) {

                    for (int j = 0; j < center.length; j++) {

                        int count = 0;
                        hpot = center[j].getHead();

                        while (count != i) {
                            if (hpot != null) {

                                hpot = hpot.getNext();

                            }

                            count++;

                        }

                        if (hpot != null) {
                            if (hpot.getCenter() == dele) {
                                center[dele - 1].setHead(null);

                            }

                        } else {

                        }
                    }

                }
                pen.println("  			Center " + dele + " is Closed");
                pen.println("===================================================================================================");
                //  display(center, pen, array);
            }
            if (command.equalsIgnoreCase("MERGE")) {
                Practitioner hpot;
                // in merge i create also new linked list and add all practioner in there 
                pen.print("\n"
                        + "			The remaing centers ar merged\n"
                        + "\n"
                        + "--------------------------------------------------------------------------------------------------\n");

                Center[] merge = new Center[center[1].getCapicity() + center[2].getCapicity()];
                for (int i = 0; i < merge.length; i++) {
                    merge[i] = new Center(center.length + 1, head);
                }
                for (int i = 1; i < center.length; i++) {
                    hpot = center[i].getHead();
                    for (int j = 0; j < center[i].getCapicity(); j++) {
                        while (hpot != null) {

                            merge[j].addPractitioner(hpot.getParctID(), hpot.getFname(), hpot.getlName(), hpot.getStatus(), center.length, head);//hpot

                            pen.println(hpot.toString());
                            hpot = hpot.getNext();

                        }

                    }

                }
                pen.println("===================================================================================================");

            }

        } while (!command.equalsIgnoreCase("QUIT"));
        pen.print("\n"
                + "			-------------------------------------\n"
                + "	   	       |	     Good Bye                 |\n"
                + "                        -------------------------------------      ");
        pen.flush();
        pen.close();

    }

    public static void display(Center[] center, PrintWriter pen, String[] array, int maxcap) {
        pen.print("       ");
// this for to print names of centers 
        for (int i = 0; i < center.length; i++) {

            if (center[i] != null) {
                pen.print(array[i].replaceAll("_", " ") + "		  ");
            }
        }

        pen.println("\n\n--------------------------------------------------------------------------------------------------");
// this part to print all practioner information 
        Practitioner hpot;
// we make neasted loop to reach in every practioner and control with it 

        for (int i = 0; i < maxcap; i++) {

            for (int j = 0; j < center.length; j++) {

                int count = 0;
                hpot = center[j].getHead();
                while (count != i) {
                    if (hpot != null) {
                        hpot = hpot.getNext();
                    }
                    count++;
                }
                if (hpot != null) {

                    String name = hpot.getFname() + " " + hpot.getlName() + ",";
                    pen.printf("%-5s%-18s%1s%-6s\t", hpot.getParctID(), name, "", hpot.getStatus());
                } else {
                    pen.print("");
                }
                pen.print("");
            }
            pen.print("\n");
        }
    }

}
