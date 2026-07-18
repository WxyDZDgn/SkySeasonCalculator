package data;

public class DearVanGogh extends Data {
    public final static double[][][] POINTS = {{
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
    public final static int[][] LAYERS = {
            {1, 2, 2, 1, -1},
            {1, 1, 3, 2, -1},
            {1, 2, 2, 1, -1},
            {1, 1, 3, 2, -1}
    };

    @Override
    public double[][][] POINTS() {
        return DearVanGogh.POINTS;
    }

    @Override
    public int[][] LAYERS() {
        return DearVanGogh.LAYERS;
    }
}