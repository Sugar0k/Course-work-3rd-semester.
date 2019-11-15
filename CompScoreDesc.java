import java.util.Comparator;
import java.util.Map;

public class CompScoreDesc implements Comparator<TouristKey> {
    Map<TouristKey, Integer> journal;
    public CompScoreDesc(Map<TouristKey, Integer> journal){
        this.journal = journal;
    }

    public int compare (TouristKey key1, TouristKey key2){
        int amount1 = journal.get(key1);
        int amount2 = journal.get(key2);
        if(amount1 > amount2) return -1;
        return 1;
    }
}
