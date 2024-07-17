package cinema;

import java.util.HashMap;
import java.util.Map;

public class Statistics {

    private static int purchasedTickets = 0;
    private static int totalIncome = 0;
    private static int availableSeats =0;



    public static void initializeSeats(int totalSeats) {
       availableSeats = totalSeats;
    }

    public static void updateOnPurchase(int price){
        purchasedTickets++;
        totalIncome += price;
        availableSeats--;
    }
    public static void updateOnReturn(int price){
        purchasedTickets--;
        totalIncome -= price;
        availableSeats++;
    }

    public static Map<String, Integer> getStatistics(){
        Map<String, Integer> statistics = new HashMap<>();
        statistics.put("income", totalIncome);
        statistics.put("available", availableSeats);
        statistics.put("purchased", purchasedTickets);
        return statistics;
    }

}
