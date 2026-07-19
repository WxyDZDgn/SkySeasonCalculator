package data;

public class DearVanGogh extends Data {
    public final static int[][][] rawPoints = {{
            {0, 4}, {6, 18}, {10, 22}, {26}, {3}
    }, {
            {0, 12}, {6}, {22, 22, 8}, {26, 12}, {3}
    }, {
            {0, 4}, {18, 8}, {22, 8}, {26}, {3}
    }, {
            {0, 12}, {18, 6}, {22, 22, 10}, {10}, {3}
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