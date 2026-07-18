import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static List<Solution> results = new ArrayList<>();
    static int minimumDays = 40000;

    static void calculation(int[][] choice) {
        int daysForAncestor = 0;
        int costSeasonCandles = 0;
        int[] travel = new int[choice.length];
        for (int a = 0; a < 4; ++a) {
            int curItemIndex = 0;
            int ancestors = 0;
            double points = 0;
            for (int idx = 0; idx < Data.LAYERS[a].length; ++idx) {
                int layer = Data.LAYERS[a][idx];
                double dif = points - Data.POINTS[a][curItemIndex][2];
                // 最小的x使 -dif/10 <= x
                if (dif < 0) {
                    int t = (int) (Math.ceil(-dif / 10));
                    ancestors += t;
                    points += t * 10;
                }
                for (int c = 0; c < choice[a][idx]; ++c) {
                    int i = curItemIndex + c;
                    costSeasonCandles += (int) Data.POINTS[a][i][0];
                    points += Data.POINTS[a][i][1];
                }
                curItemIndex += layer;
            }
            travel[a] = ancestors;
            daysForAncestor += ancestors;
        }

        int gainedSeasonCandles = Data.INIT_SEASON_CANDLES + daysForAncestor * Data.SEASON_CANDLES_PER_DAY;
        int dif = gainedSeasonCandles - costSeasonCandles;
        int totalDays = daysForAncestor;
        if (dif < 0) {
            totalDays += ceil(-dif, Data.SEASON_CANDLES_PER_DAY);
        }

        if (totalDays <= minimumDays) {
            if (totalDays < minimumDays) results.clear();
            minimumDays = totalDays;
            results.add(new Solution(minimumDays, travel, choice));
        }

    }

    static int ceil(int x, int y) {
        assert x >= 0 && y > 0;
        return (x + y - 1) / y;
    }

    static void fun(int[][] choice, int i, int j) {
        if (i >= Data.LAYERS.length) {
            calculation(choice);
            return;
        }
        if (j >= Data.LAYERS[i].length) {
            fun(choice, i + 1, 0);
            return;
        }
        if (Data.LAYERS[i][j] < 0) {
            choice[i][j] = 1;
            fun(choice, i, j + 1);
        } else {
            for (int k = 0; k <= Data.LAYERS[i][j]; ++k) {
                choice[i][j] = k;
                fun(choice, i, j + 1);
            }
        }
    }

    public static void main(String[] args) {
        int[][] choice = Arrays.stream(Data.LAYERS).map(int[]::clone).toArray(int[][]::new);
        fun(choice, 0, 0);
        System.out.printf("一共有 %d 个方案，所有方案最少需要 %d 天获得所有季节爱心。\n", results.size(), minimumDays);
        for (int i = 0; i < results.size(); i++) {
            System.out.printf("第 %d 个方案如下：\n", i + 1);
            results.get(i).print();
            System.out.println();
        }

    }

}

class Data {

    final static double[][][] POINTS = {{
            {4, 40, 0},
            {6, 30, 40},
            {18, 30, 40},
            {10, 40, 100},
            {22, 40, 100},
            {26, 100, 180},
            {3, 0, 280}
    }, {
            {12, 40, 0},
            {6, 60, 40},
            {8, (double) 80 / 3, 100},
            {22, (double) 80 / 3, 100},
            {22, (double) 80 / 3, 100},
            {12, 50, 180},
            {26, 50, 180},
            {3, 0, 280}
    }, {
            {4, 40, 0},
            {8, 30, 40},
            {18, 30, 40},
            {8, 40, 100},
            {22, 40, 100},
            {26, 100, 180},
            {3, 0, 280}
    }, {
            {12, 40, 0},
            {6, 30, 40},
            {18, 30, 40},
            {10, (double) 80 / 3, 100},
            {22, (double) 80 / 3, 100},
            {22, (double) 80 / 3, 100},
            {10, 100, 180},
            {3, 0, 280}
    }};
    final static int INIT_SEASON_CANDLES = 46;
    final static int SEASON_CANDLES_PER_DAY = 6;
    final static int[][] LAYERS = {
            {1, 2, 2, 1, -1},
            {1, 1, 3, 2, -1},
            {1, 2, 2, 1, -1},
            {1, 1, 3, 2, -1}
    };
}

class Solution {
    int totalDays;
    int[] travelSoltion;
    int[][] purchaseChoice;

    Solution(int totalDays, int[] travelSoltion, int[][] purchaseChoice) {
        this.totalDays = totalDays;
        this.travelSoltion = travelSoltion.clone();
        this.purchaseChoice = Arrays.stream(purchaseChoice).map(int[]::clone).toArray(int[][]::new);
    }

    void print(boolean printNumOfDays) {
        if (printNumOfDays)
            System.out.printf("方案最少需要 %d 天获得所有季节爱心。\n", totalDays);
        assert travelSoltion.length == purchaseChoice.length;
        for (int i = 0; i < travelSoltion.length; ++i) {
            if (travelSoltion[i] <= 0) System.out.printf("第 %d 位先祖不需要加深友谊；\n", i + 1);
            else System.out.printf("第 %d 位先祖需要加深友谊 %d 次：\n", i + 1, travelSoltion[i]);

            for (int j = 0; j < purchaseChoice[i].length; ++j) {
                if (purchaseChoice[i][j] <= 0) System.out.printf("不需要兑换第 %d 层的任何节点；\n", j + 1);
                else if (purchaseChoice[i][j] >= Data.LAYERS[i][j])
                    System.out.printf("兑换第 %d 层的所有节点；\n", j + 1);
                else System.out.printf("兑换第 %d 层花费季蜡最少的 %d 个节点；\n", j + 1, purchaseChoice[i][j]);
            }
        }
    }

    void print() {
        print(false);
    }
}
