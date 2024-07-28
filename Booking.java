import java.util.*;
class Booking{
    static int customerId=1;
    public static List<Taxi> createTaxis(int size){
        List<Taxi> freeTaxis = new ArrayList<>();
        for(int i=1;i<=size;i++){
            Taxi obj = new Taxi();
            freeTaxis.add(obj);

        }
        return freeTaxis;
    } 

    public static List<Taxi> getFreeTaxi(List<Taxi> freeTaxis,char pickupPoint,int pickupTime){
        List<Taxi> AvailableTaxis = new ArrayList<>();
        for(Taxi i:freeTaxis){
            if(i.freeTime<=pickupTime && (Math.abs((i.currentSpot-'0')-(pickupPoint-'0')))<=pickupTime-i.freeTime){
                AvailableTaxis.add(i);
            }
        }
        return AvailableTaxis;
    }

    public static void bookTaxi(char pickupPoint,char dropPoint,int pickupTime,List<Taxi> AvailableTaxis){
        
        // Collections.sort(AvailableTaxis,(a,b)->a.totalEarings-b.totalEarings); Use when every taxi should be allocated based on lower earnings
        for(Taxi i:AvailableTaxis){
            String trip="";
            System.out.println("Taxi -"+i.taxiId+" Alloted");
            
            i.freeTime=pickupTime+(Math.abs((pickupPoint-'0')-(dropPoint-'0')));
            i.totalEarings+=((Math.abs((pickupPoint-'0')-(dropPoint-'0'))*15)-5)*10+100;
            i.currentSpot=dropPoint;
            int tripAmount = ((Math.abs((pickupPoint-'0')-(dropPoint-'0'))*15)-5)*10+100;
            trip = pickupPoint+"      "+dropPoint+"      "+pickupTime+"           "+i.freeTime+"        "+tripAmount;
            i.bookingId=customerId++;
            i.trips.add(trip);
            return;
        }
    }
    public static void main(String[] args){
        List<Taxi> freeTaxis = createTaxis(4);
        Scanner sc = new Scanner(System.in);
        
        boolean loop = true;
        System.out.println(" Taxi Booking Application");
        while(loop){
            System.out.println(" 1.Book Taxi \n 2.Print Taxi \n 3.Trip Details \n 4.Exit");
           
            int choices = sc.nextInt();
            switch (choices) {
                case 1:{
                    System.out.println("Enter Pickup Point: ");
                    char pickupPoint = sc.next().charAt(0);
                    System.out.println("Enter Drop Point: ");
                    char dropPoint = sc.next().charAt(0);
                    System.out.println("Enter Pickup Time: ");
                    int pickupTime = sc.nextInt();

                    if((pickupPoint>='A' && pickupPoint<='F')&&(dropPoint>='A' && dropPoint<='F')&&(pickupPoint!=dropPoint)){
                        List<Taxi> AvailableTaxis = getFreeTaxi(freeTaxis,pickupPoint,pickupTime);
                        if(AvailableTaxis.size()==0){
                            System.out.println("No Taxis Available");
                            return;
                        }
                        else{
                            
                            bookTaxi(pickupPoint,dropPoint,pickupTime,AvailableTaxis);
                        }
                        
                    }
                    else{
                        System.out.println("Enter Valid Pickup and Drop Points [A,B,C,D,E,F] ");
                        return;
                    }
                }
                break;
                case 2:{
                    System.out.println("--------------------------------------------------");
                    System.out.println(" TaxiId "+" "+" Total Earnings "+" Current Spot "+" Free Time ");
                    System.out.println("--------------------------------------------------");
                            for(Taxi i:freeTaxis){
                                System.out.println("Taxi - "+i.taxiId+"        "+i.totalEarings+"             "+i.currentSpot+"            "+i.freeTime);
                            } 
                }
                break;
                case 3:{
                    System.out.println("---------------------------------------------------------------------");
                    System.out.println(" BookingID "+" "+" CustomerID "+" "+" From "+" "+" To "+" "+" PickupTime "+" "+" DropTime "+" "+" Amount");
                    System.out.println("---------------------------------------------------------------------");
                            for(Taxi i:freeTaxis){
                               
                                System.out.println("Taxi - "+i.taxiId+" Total Earnings: Rs. "+i.totalEarings);
                                System.out.println();
                                for(String trip:i.trips){
                                System.out.println("      "+i.bookingId+"           "+i.bookingId+"        "+trip);
                                }
                                System.out.println();
                                
                        }
                }
                break;
                case 4:{
                    loop =false;
                    System.out.println("Thanks For Using Our App");
                }
                break;
            
                default:
                break;
            }
        }
    }
}