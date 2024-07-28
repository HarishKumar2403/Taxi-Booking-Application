import java.util.*;
class Taxi{
    static int taxi=1;
    int taxiId;
    char currentSpot;
    int freeTime;
    int totalEarings;
    int bookingId;
    List<String> trips;

    Taxi(){
        this.taxiId=taxi++;
        this.currentSpot='A';
        this.freeTime=6;
        this.totalEarings=0;
        this.bookingId=-1; 
        trips=new ArrayList<>();
    }
}