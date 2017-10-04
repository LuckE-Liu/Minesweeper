import java.util.Scanner;

/**
 * 为之前创建好的地图布雷
 */
class MineInitializer {
    private static int scale = ScaleInitializer.getScale();
    private static int totalMine;

    private MineInitializer(){
        totalMine = getNumberOfMines();
        Mine(totalMine);
    }

    /**
     * 创建为地图布雷器的实例
     * @return 布雷器的实例
     */
    public static MineInitializer getMine(){
        return GetInstance.INSTANCE;
    }

    private static class GetInstance {
        static final MineInitializer INSTANCE = new MineInitializer();
    }

    private void Mine(int totalMine) {
        int count = 0;

        while (count < totalMine) {
            int x = (int) (Math.random() * scale);
            int y = (int) (Math.random() * scale);

            if (legalLocation(x, y)) {
                placeMine(x, y);
                plusAroundTheMine(x, y);
                count = count + 1;
            }
        }
    }

    private static int getNumberOfMines() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many mine in this map（input should more than or equals " + scale + "and less than or equals" + scale * 2 + "）:");
        int totalMine = scanner.nextInt();
        scanner.nextLine();

        if (totalMine <= scale * 2 || totalMine >= scale) {
            System.out.println("There are " + totalMine +"  mines in the map.");
        } else {
            System.out.println("input number is too large or too small，please input again!");
            getNumberOfMines();
        }
        return totalMine;
    }

    private boolean legalLocation(int x, int y) {
        if (notMineYet(x, y) && notBeyondBounds(x, y)) {
            return true;
        }
        return false;
    }

    private  boolean notMineYet(int x, int y){
        if (!MapIntializer.getMap(x, y).equals("*") ) {//刚开始写成了==0,造成了死循环
            return true;
        }
        return  false;
    }

    private boolean notBeyondBounds(int x, int y) {
        if (x <ScaleInitializer.getScale() && y < ScaleInitializer.getScale()) {
            return true;
        }
        return false;
    }

    private void placeMine(int x, int y) {
        MapIntializer.setMap(x, y, "*");
    }

    private void plusAroundTheMine(int x, int y) {
        //x - 1 , y -1
        if ((y - 1) >= 0 && (x - 1) >= 0 && MapIntializer.getMap(x - 1 , y - 1) != "*")
            MapIntializer.setMap(x - 1, y - 1, String.valueOf(Integer.valueOf(MapIntializer.getMap(x - 1, y - 1))+ 1));
        //x , y - 1
        if ((y - 1) >= 0 && MapIntializer.getMap(x, y - 1) != "*")
            MapIntializer.setMap(x, y - 1,  String.valueOf(Integer.valueOf(MapIntializer.getMap(x, y - 1)) + 1));
        // x + 1, y - 1
        if ((y - 1) >= 0 && (x + 1) < scale && MapIntializer.getMap(x + 1 , y - 1) != "*")
            MapIntializer.setMap(x + 1, y - 1,  String.valueOf(Integer.valueOf(MapIntializer.getMap(x + 1, y - 1)) + 1));
        // x - 1, y
        if ((x - 1) >= 0 && MapIntializer.getMap(x - 1 , y) != "*")
            MapIntializer.setMap(x - 1, y,  String.valueOf(Integer.valueOf(MapIntializer.getMap(x - 1, y)) + 1));
        //x + 1 , y
        if ((x + 1) < scale && MapIntializer.getMap(x + 1 , y) != "*")
            MapIntializer.setMap(x + 1, y,  String.valueOf(Integer.valueOf(MapIntializer.getMap(x + 1, y)) + 1));
        //x - 1, y + 1
        if ((x - 1) >= 0 && (y + 1) < scale && MapIntializer.getMap(x - 1 , y + 1) != "*")
            MapIntializer.setMap(x - 1, y + 1,  String.valueOf(Integer.valueOf(MapIntializer.getMap(x - 1, y + 1)) + 1));
        //x y + 1
        if ((y + 1) < scale && MapIntializer.getMap(x , y + 1) != "*")
            MapIntializer.setMap(x, y + 1,  String.valueOf(Integer.valueOf(MapIntializer.getMap(x, y + 1)) + 1));
        //x + 1, y + 1
        if ((y + 1) < scale && (x + 1) < scale && MapIntializer.getMap(x + 1 , y + 1) != "*")
            MapIntializer.setMap(x + 1, y + 1,  String.valueOf(Integer.valueOf(MapIntializer.getMap(x + 1, y + 1)) + 1));
    }

    /**
     * 获得总的地雷数的值
     * @return 总地雷数的值
     */
    public static int getTotalMine() {
        return totalMine;
    }
}