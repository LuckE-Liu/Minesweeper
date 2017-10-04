/**
 * 根据之前获得的规模大小，初始化游戏地图，并在游戏过程中提供地图中各个点情况。
 */
class MapIntializer {

    private static String[][] map = new String[ScaleInitializer.getScale()][ScaleInitializer.getScale()];

    private MapIntializer() {
        for (int i = 0; i < ScaleInitializer.getScale(); i++) {
            for (int j = 0; j < ScaleInitializer.getScale(); j++) {
                map[i][j] = "0";
            }
        }
    }

    /**
     * 创建一个地图实例
     * @return 地图实例
     */
    public static MapIntializer getMapInstance() {
        return MapInstance.mapIntializer;
    }

    private static class MapInstance {
        private static final MapIntializer mapIntializer = new MapIntializer();
    }

    /**
     * 获取地图上坐标为(X,Y)的点
     * @param x X坐标
     * @param y Y坐标
     * @return (X,Y)对应的地图上的值
     */
    public static String getMap(int x, int y) {
        return map[x][y];
    }

    /**
     * 该函数仅为布雷器使用，不应该在其它任何地方调用！
     * 为地图上坐标为(X,Y)的点赋值
     * @param x X坐标
     * @param y Y坐标
     * @param ele 要赋的值
     */
    public static void setMap(int x, int y, String ele) {
        MapIntializer.map[x][y] = ele;
    }
}