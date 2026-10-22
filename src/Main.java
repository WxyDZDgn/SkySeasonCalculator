import data.Pearl;

public class Main {
    public static void main(String[] args) {
        Calculation calculation = new Calculation(new Pearl()).
                setInitSeasonCandles(46).
                setSeasonCandlesPerDay(6);
        calculation.print();
    }
}
