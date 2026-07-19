package data;

import java.util.Arrays;

public class Data extends AbstractData {
    private static final double[] LIMIT = {0, 40, 100, 180, 280};

    @Override
    public double[][][] POINTS() {
        assert false;
        return new double[0][][];
    }

    @Override
    public int[][][] LAYERS() {
        assert false;
        return new int[0][][];
    }

    protected static double[][][] getPoint(int[][][] rawPoint) {
        // rawPoint[ancestorIndex][layerIndex][itemIndex]
        // return[ancestorIndex][itemIndex][seasonCandle, point, pointLimit]
        // assert [_][x][2] <= [_][x + 1][2]
        // if [_][x][2] == [_][x + 1][2]: assert [_][x][1] == [_][x+1][1] && [_][x][0] <= [_][x][0]
        // elif [_][x][2] > [_][x+1][2]:  assert false
        assert rawPoint != null;
        double[][][] res = new double[LIMIT.length][][];
        for (int ancestorIndex = 0; ancestorIndex < rawPoint.length; ++ancestorIndex) {
            int[][] tree = rawPoint[ancestorIndex];
            assert tree.length == LIMIT.length;
            int totalItem = 0;
            for (int[] ints : tree) {
                totalItem += ints.length;
            }
            res[ancestorIndex] = new double[totalItem][3];
            int idx = 0;
            for (int i = 0; i < tree.length; ++i) {
                int[] sort = tree[i].clone();
                Arrays.sort(sort);
                double pointAverage = (i == tree.length - 1 ? LIMIT[i] : LIMIT[i + 1]) - LIMIT[i];
                pointAverage /= sort.length;
                for (int e : sort) {
                    res[ancestorIndex][idx][0] = e;
                    res[ancestorIndex][idx][1] = pointAverage;
                    res[ancestorIndex][idx][2] = LIMIT[i];
                    idx += 1;
                }
            }
        }
        return res;
    }

    protected static int[][][] getLayer(int[][][] rawPoint) {
        assert rawPoint != null;
        int[][][] res = new int[rawPoint.length][][];
        for (int i = 0; i < rawPoint.length; ++i) {
            res[i] = new int[rawPoint[i].length][2];
            for(int j = 0; j < rawPoint[i].length; ++ j) {
                if(j == rawPoint[i].length - 1) {
                    res[i][j][0] = rawPoint[i][j].length;
                    res[i][j][1] = rawPoint[i][j].length;
                    continue;
                }
                int k = 0;
                while (rawPoint[i][j][k] == 0) k += 1;
                res[i][j][0] = k;
                res[i][j][1] = rawPoint[i][j].length;
            }
        }
        return res;
    }
}
