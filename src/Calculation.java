import data.Data;

import java.util.ArrayList;
import java.util.List;

public class Calculation {
    private List<Solution> results = null;
    private int minimumDays = 0;
    private int initSeasonCandles = 0;
    private int seasonCandlesPerDay = 6;
    private final Data seasonData;

    public Calculation(Data seasonData) {
        this.seasonData = seasonData;
    }

    public Calculation setInitSeasonCandles(int x) {
        assert x >= 0;
        clearResults();
        this.initSeasonCandles = x;
        return this;
    }

    private void clearResults() {
        results = null;
        minimumDays = Integer.MAX_VALUE;
    }

    public Calculation setSeasonCandlesPerDay(int x) {
        assert x >= 0;
        clearResults();
        this.seasonCandlesPerDay = x;
        return this;
    }

    private void calculation(int[][] choice) {
        int daysForAncestor = 0;
        int costSeasonCandles = 0;
        int[] travel = new int[choice.length];
        for (int a = 0; a < 4; ++a) {
            int curItemIndex = 0;
            int ancestors = 0;
            double points = 0;
            for (int idx = 0; idx < seasonData.LAYERS()[a].length; ++idx) {
                int layer = seasonData.LAYERS()[a][idx][1];
                double dif = points - seasonData.POINTS()[a][curItemIndex][2];
                // 最小的x使 -dif/10 <= x
                if (dif < 0) {
                    int t = (int) (Math.ceil(-dif / 10));
                    ancestors += t;
                    points += t * 10;
                }
                for (int c = 0; c < choice[a][idx]; ++c) {
                    int i = curItemIndex + c;
                    costSeasonCandles += (int) seasonData.POINTS()[a][i][0];
                    points += seasonData.POINTS()[a][i][1];
                }
                curItemIndex += layer;
            }
            travel[a] = ancestors;
            daysForAncestor += ancestors;
        }

        int gainedSeasonCandles = initSeasonCandles + daysForAncestor * seasonCandlesPerDay;
        int dif = gainedSeasonCandles - costSeasonCandles;
        int totalDays = daysForAncestor;
        if (dif < 0) {
            totalDays += ceil(-dif, seasonCandlesPerDay);
        }

        if (totalDays <= minimumDays) {
            if (totalDays < minimumDays) results.clear();
            minimumDays = totalDays;
            results.add(new Solution(minimumDays, travel, choice, seasonData));
        }

    }

    private static int ceil(int x, int y) {
        assert x >= 0 && y > 0;
        return (x + y - 1) / y;
    }

    private void fun(int[][] choice, int i, int j) {
        if (i >= seasonData.LAYERS().length) {
            calculation(choice);
            return;
        }
        if (j >= seasonData.LAYERS()[i].length) {
            fun(choice, i + 1, 0);
            return;
        }
        for (int k = seasonData.LAYERS()[i][j][0]; k <= seasonData.LAYERS()[i][j][1]; ++k) {
            choice[i][j] = k;
            fun(choice, i, j + 1);
        }
    }

    public void print() {
        calculation();
        System.out.printf("一共有 %d 个方案，所有方案最少需要 %d 天获得所有季节爱心。\n", results.size(), minimumDays);
        for (int i = 0; i < results.size(); i++) {
            System.out.printf("第 %d 个方案如下：\n", i + 1);
            results.get(i).print();
            System.out.println();
        }
    }

    private void calculation() {
        if (results == null) {
            results = new ArrayList<>();
            int n = seasonData.LAYERS().length;
            int m = seasonData.LAYERS()[0].length;
            int[][] choice = new int[n][m];
            fun(choice, 0, 0);
        }
    }

}
