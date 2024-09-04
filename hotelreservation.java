import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String type;
    private boolean isAvailable;
    private double price;

    public Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.isAvailable = true;
        this.price = price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " [" + type + "], Price: $" + price + ", Available: " + isAvailable;
    }
}

class Reservation {
    private String guestName;
    private Room room;

    public Reservation(String guestName, Room room) {
        this.guestName = guestName;
        this.room = room;
        room.setAvailable(false);
    }

    public String getGuestName() {
        return guestName;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Reservation for " + guestName + " in " + room;
    }
}

class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void showAvailableRooms() {
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room);
            }
        }
    }

    public void makeReservation(String guestName, int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                Reservation reservation = new Reservation(guestName, room);
                reservations.add(reservation);
                System.out.println("Reservation successful for " + guestName + " in room " + roomNumber);
                return;
            }
        }
        System.out.println("Sorry, room " + "roomNumber is not available or does not exist");
    }

    public void viewReservations() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        
        // Adding some rooms to the hotel
        hotel.addRoom(new Room(101, "Single", 100));
        hotel.addRoom(new Room(102, "Double", 150));
        hotel.addRoom(new Room(103, "Suite", 200));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Hotel Reservation System");
            System.out.println("1. Show Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hotel.showAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    scanner.nextLine();  // To consume the newline character left by nextInt()
                    String name = scanner.nextLine();
                    System.out.print("Enter room number to reserve: ");
                    int roomNumber = scanner.nextInt();
                    hotel.makeReservation(name, roomNumber);
                    break;
                case 3:
                    hotel.viewReservations();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}