import data.DearVanGogh;

public class Main {
    public static void main(String[] args) {
        Calculation calculation = new Calculation(new DearVanGogh()).
                setInitSeasonCandles(46).
                setSeasonCandlesPerDay(6);
        calculation.print();
    }
}
