package data;

public class Pearl extends Data {
    public final static int[][][] rawPoints = {{
            {0, 4}, {18, 7}, {28, 8}, {32}, {3}
    }, {
            {0, 4}, {18, 7}, {28, 8}, {32}, {3}
    }, {
            {0, 4}, {18, 7}, {28, 24}, {12}, {3}
    }, {
            {0, 4}, {18}, {8, 10}, {32}, {3}
    }};
    public static double[][][] POINTS = null;
    public static int[][][] LAYERS = null;

    @Override
    public double[][][] POINTS() {
        if (POINTS == null) POINTS = Data.getPoint(rawPoints);
        return POINTS;
    }

    @Override
    public int[][][] LAYERS() {
        if (LAYERS == null) LAYERS = Data.getLayer(rawPoints);
        return LAYERS;
    }
}