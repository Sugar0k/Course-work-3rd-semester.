public class TouristKey implements Comparable<TouristKey> {
    private final static String TKEY_FORMAT_STRING = "Шифр маршрута: %3d  Страна: %1s  Стоимость: %2d";
    private int id, cost;
    private String name;

    public TouristKey() {
        this.id = 0;
        this.name = " ";
        this.cost = 0;
    }

    public TouristKey(int id, String name, int cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public void setId(int id) {this.id = id;}
    public void setTeamCode(String name) {this.name = name;}
    public void setCost(int cost) {this.cost = cost;}

    public int getId() {return id;}
    public String getTeamCode() {return name;}
    public int getCost() {return cost;}
    
    public String toString(){
        return String.format(TKEY_FORMAT_STRING, id, name, cost);
    }

    public boolean equals (Object ob){
        if(ob == this) return true;
        if(getClass() != ob.getClass()) return false;
        
        TouristKey specialtyKey = (TouristKey) ob;
        return (id == specialtyKey.getId()) && (name.equals(specialtyKey.getTeamCode()));
    }

    public int hashCode(){
        return 7*(new Integer(id)).hashCode() +
                11 * name.hashCode() +
                13 * (new Integer(cost).hashCode());
    }

    public int compareTo(TouristKey specialtyKey){
        if(id < specialtyKey.getId()) return -1;
        if(id > specialtyKey.getId()) return 1;
        /*
        if(name.compareTo(specialtyKey.getTeamCode()) < 0) return -1;
        if(name.compareTo(specialtyKey.getTeamCode()) > 0) return 1;
        */
        return 0;
    }

}
