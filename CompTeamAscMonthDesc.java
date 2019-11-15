import java.util.Comparator;

public class CompTeamAscMonthDesc implements Comparator<TouristKey> {
    public int compare(TouristKey key1, TouristKey key2){
        String codeT1= key1.getTeamCode();
        String codeT2 = key2.getTeamCode();


        if(codeT1.compareTo(codeT2) < 0) return 1;
        if(codeT1.compareTo(codeT2) > 0) return -1;


        return 0;
    }
}
