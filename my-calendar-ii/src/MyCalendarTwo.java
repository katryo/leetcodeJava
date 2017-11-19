import java.awt.print.Book;
import java.util.*;

public class MyCalendarTwo {
    private List<Booking> bookings;
    private List<Booking> doubleBookings;

    public MyCalendarTwo() {
        bookings = new LinkedList<Booking>();
        doubleBookings = new LinkedList<Booking>();
    }

    public boolean book(int start, int end) {
        Booking b = new Booking(start, end);

        for (Booking dBooking: doubleBookings) {
            if (dBooking.doesConflicts(b)) {
                return false;
            }
        }
        for (Booking booking: bookings) {
            if (booking.doesConflicts(b)) {
                doubleBookings.add(booking.getDoubleBooking(b));
            }
        }
        bookings.add(b);
        return true;
    }

    class Booking {
        private int start;
        private int end;

        Booking(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean doesConflicts(Booking b) {
            if (start < b.end && b.start < end) {
                return true;
            }
            return false;
        }

        public Booking getDoubleBooking(Booking b) {
            int s = Math.max(start, b.start);
            int e = Math.min(end, b.end);
            return new Booking(s, e);
        }

    }

    public static void main(String[] args) {
        MyCalendarTwo mc = new MyCalendarTwo();
        System.out.println(mc.book(10, 20));
        System.out.println(mc.book(50, 60));
        System.out.println(mc.book(10, 40));
        System.out.println(mc.book(5, 15));
        System.out.println(mc.book(5, 10));
        System.out.println(mc.book(25, 55));
    }
}
