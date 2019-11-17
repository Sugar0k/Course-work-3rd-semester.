import java.util.Comparator;

public class CompTeamAscMonthDesc implements Comparator<TouristKey> {
    public int compare(TouristKey key1, TouristKey key2){
        int code1 = key1.getCost();
        int code2 = key2.getCost();
        
        if(code1 > code2) return -1;
        return 1;
    }
}
