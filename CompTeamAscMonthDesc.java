import java.util.Comparator;

public class CompTeamAscMonthDesc implements Comparator<TouristKey> {
    public int compare(TouristKey key1, TouristKey key2){
        int month1 = key1.getId();
        int month2 = key2.getId();

        if(month1 < month2) return 1;
        if(month1 > month2) return -1;

        return 0;
    }
}
