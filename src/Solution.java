import data.Data;

import java.util.Arrays;

public class Solution {
    int totalDays;
    int[] travelSoltion;
    int[][] purchaseChoice;
    Data data;

    Solution(int totalDays, int[] travelSoltion, int[][] purchaseChoice, Data data) {
        this.data = data;
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
                else if (purchaseChoice[i][j] >= data.LAYERS()[i][j])
                    System.out.printf("兑换第 %d 层的所有节点；\n", j + 1);
                else System.out.printf("兑换第 %d 层花费季蜡最少的 %d 个节点；\n", j + 1, purchaseChoice[i][j]);
            }
        }
    }

    void print() {
        print(false);
    }
}
