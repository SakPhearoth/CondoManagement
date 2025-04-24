import java.util.Scanner;
import java.util.ArrayList;

public class CondoManagement {
    static int floor, room;
    static String[][] condo;
    static ArrayList<String> history = new ArrayList<>();

    public static void main(String[] args) {
        do {
            Scanner sc = new Scanner(System.in);
            String option;

            // Application menu
            System.out.println("-".repeat(30));
            System.out.println("Condo Management Application");
            System.out.println("-".repeat(30));
            System.out.println("A. Setup Condo");
            System.out.println("B. Display available room");
            System.out.println("C. Room checkin");
            System.out.println("D. Room checkout");
            System.out.println("E. Display check-in/check-out history");
            System.out.println("F. Exit");
            System.out.println("-".repeat(30));

            System.out.print("Enter the option: ");
            option = sc.nextLine().toLowerCase();

            switch (option) {
                case "a": {
                    System.out.println("------- SETUP CONDO -------");
                    System.out.println("-".repeat(30));
                    System.out.print("Enter number of floor: ");
                    floor = sc.nextInt();
                    System.out.print("Enter number of room: ");
                    room = sc.nextInt();

                    condo = new String[floor][room];
                    history.clear();

                    System.out.println("-".repeat(30));
                    System.out.println("=> Condo Setup successfully");
                    System.out.println("-".repeat(30));
                    for (int i = 0; i < floor; i++) {
                        for (int j = 0; j < room; j++) {
                            System.out.print("F" + (i + 1) + "-" + "R" + (j + 1) + "| ");
                        }
                        System.out.println();
                    }
                    break;
                }
                case "b": {
                    System.out.println("------- DISPLAY ROOM STATUS -------");
                    System.out.println("-".repeat(30));

                    if (floor == 0) {
                        System.out.println("*** Please setup condo first! ***");
                    } else {
                        for (int i = 0; i < floor; i++) {
                            for (int j = 0; j < room; j++) {
                                if (condo[i][j] == null) {
                                    System.out.print("F" + (i + 1) + "-" + "R" + (j + 1) + "| ");
                                } else {
                                    System.out.print("F" + (i + 1) + "-R" + (j + 1) + "(" + condo[i][j] + ") | ");
                                }
                            }
                            System.out.println();
                        }
                    }
                    break;
                }
                case "c": {
                    System.out.println("--------- ROOM CHECKIN ---------");
                    System.out.println("-".repeat(30));

                    if (floor == 0) {
                        System.out.println("*** Please setup condo first! ***");
                    } else {
                        System.out.print("Enter floor number (1-" + floor + "): ");
                        int floorNumber = sc.nextInt();
                        System.out.print("Enter room number (1-" + room + "): ");
                        int roomNumber = sc.nextInt();
                        sc.nextLine();

                        if (floorNumber < 1 || floorNumber > floor || roomNumber < 1 || roomNumber > room) {
                            System.out.println("*** Invalid floor or room number! ***");
                        } else {
                            if (condo[floorNumber - 1][roomNumber - 1] == null) {
                                System.out.print("Enter guest name: ");
                                String guestName = sc.nextLine();
                                condo[floorNumber - 1][roomNumber - 1] = guestName;
                                String historyEntry = "CHECK-IN: Guest " + guestName + " checked into F" +
                                        floorNumber + "-R" + roomNumber + " on " +
                                        java.time.LocalDateTime.now();
                                history.add(historyEntry);
                                System.out.println("Room F" + floorNumber + "-R" + roomNumber + " Checkin successfully");
                            } else {
                                System.out.println("Room F" + floorNumber + "-R" + roomNumber + " is already occupied");
                            }
                        }
                    }
                    System.out.println("-".repeat(30));
                    System.out.println("Press any key to continue...");
                    sc.nextLine();
                    break;
                }
                case "d": {
                    System.out.println("--------- ROOM CHECKOUT ---------");
                    System.out.println("-".repeat(30));

                    if (floor == 0) {
                        System.out.println("*** Please setup condo first! ***");
                    } else {
                        System.out.print("Enter guest name to check out: ");
                        String guestName = sc.nextLine();
                        boolean found = false;

                        for (int i = 0; i < floor && !found; i++) {
                            for (int j = 0; j < room; j++) {
                                if (guestName.equalsIgnoreCase(condo[i][j])) {
                                    System.out.println("Guest Name: " + guestName + " checked out from Floor"
                                            + (i + 1) + "-Room" + (j + 1));
                                    String historyEntry = "CHECK-OUT: Guest " + guestName + " checked out from F" +
                                            (i + 1) + "-R" + (j + 1) + " on " +
                                            java.time.LocalDateTime.now();
                                    history.add(historyEntry);
                                    condo[i][j] = null;
                                    System.out.println("=> Room F" + (i + 1) + "-R" + (j + 1) + " Checked out successfully");
                                    found = true;
                                    break;
                                }
                            }
                        }

                        if (!found) {
                            System.out.println("*** Guest " + guestName + " not found in any room! ***");
                        }
                    }
                    System.out.println("-".repeat(30));
                    System.out.println("Press any key to continue...");
                    sc.nextLine();
                    break;
                }
                case "e": {
                    System.out.println("------- CHECK-IN/CHECK-OUT HISTORY -------");
                    System.out.println("-".repeat(30));

                    if (floor == 0) {
                        System.out.println("*** Please setup condo first! ***");
                    } else if (history.isEmpty()) {
                        System.out.println("*** No check-in/check-out history available! ***");
                    } else {
                        System.out.println("History of Check-ins and Check-outs:");
                        for (int i = 0; i < history.size(); i++) {
                            System.out.println((i + 1) + ". " + history.get(i));
                        }
                    }
                    System.out.println("-".repeat(30));
                    System.out.println("Press any key to continue...");
                    sc.nextLine();
                    break;
                }
                case "f": {
                    System.out.println("-".repeat(30));
                    System.out.println("*** Exiting System. Good bye! ***");
                    System.out.println("-".repeat(30));
                    sc.close();
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("-".repeat(30));
                    System.out.println("*** Invalid option! (Choose a, b, c, d, e, or f)*** ");
                    System.out.println("-".repeat(30));
                    break;
                }
            }
        } while (true);
    }
}
