import java.util.*;

public class MyCalendar {
    private List<Booking> bookings;

    public MyCalendar() {
        bookings = new LinkedList<Booking>();
    }

    public boolean book(int start, int end) {
        Booking b = new Booking(start, end);
        for (Booking booking: bookings) {
            if (booking.doesConflicts(b)) {
                return false;
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

    }

    public static void main(String[] args) {
        MyCalendar mc = new MyCalendar();
        System.out.println(mc.book(10, 20));
        System.out.println(mc.book(15, 25));
        System.out.println(mc.book(20, 30));
        System.out.println(mc.book(2, 10));
        System.out.println(mc.book(9, 10));
    }
}
