import java.util.Comparator;

public class CompIdAscTeamCodeDesc implements Comparator<TouristKey> {
    public int compare(TouristKey key1, TouristKey key2){
        String code1 = key1.getTeamCode();
        String code2 = key2.getTeamCode();
        
        if(code1.compareTo(code2) < 0) return 1;
        if(code1.compareTo(code2) > 0) return -1;
        return 0;
    }
}

