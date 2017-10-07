import java.util.Scanner;

/**
 * 扫雷控制器
 */
class SweepControler {

    private static int scale = ScaleInitializer.getScale();
    private static String[][] mineMap = new String[scale + 1][scale + 1];
    private static int mineLeft = MineInitializer.getTotalMine();
    private static int printMineLeft = MineInitializer.getTotalMine();

    private SweepControler() {
        for (int i = 0; i <= scale; i++) {
            for (int j = 0; j <= scale; j++) {
                if (i == 0 && j == 0) {
                    mineMap[i][j] = Integer.toString(0);
                } else if (j == 0){
                    mineMap[i][j] = Integer.toString(i);
                } else if (i == 0){
                    mineMap[i][j] = Integer.toString(j);
                } else {
                    mineMap[i][j] =" ";
                }
            }
        }
    }//除了一个值全为" "的游戏地图，还提供了一个坐标轴方便游戏

    /**
     * 创建控制器的实例
     * @return 控制器的实例
     */
    public static SweepControler getSweepControlerInstance() {
        return SweepControlerInstance.INSTANCE;
    }

    private static class SweepControlerInstance {
        static final SweepControler INSTANCE = new SweepControler();
    }

    /**
     * 在控制台输出一张反应当前游戏进度的地图
     */
    public static void printRecentlyMineMap() {
        for(int i = 0; i <= scale; i++) {
            for (int j = 0; j <= scale; j++) {
                if ( j == scale) {
                    System.out.println("[" + mineMap[i][j] + "]");
                } else {
                    System.out.print("[" + mineMap[i][j] + "]");
                }
            }
        }
    }

    /**
     * 使用该函数，使玩家输入一个点的坐标并判断当前点的类型
     */
    public static void chooseOnePoint() {
        int[] thePoint = choosePointType();

        manageWithThePoint(thePoint);
        printRecentlyMineMap();

        System.out.println(printMineLeft+" mines left");
    }

    private static int[] choosePointType()  {
        int[] input = new int[3];
        Scanner scanner = new Scanner(System.in);

        try {
            for (int i = 0; i < 3; i++) {
                input[i] = scanner.nextInt();
            }
        } catch (Exception e) {
            System.out.println("请输入正确的坐标格式");
            input = choosePointType();
        }

        scanner.nextLine();
        return input;
    }

    private static boolean thisIsMine(int x, int y) {
        if (MapIntializer.getMap(x - 1, y - 1).equals("*")) {
            return true;
        }
        return false;
    }

    private static void manageWithThePoint(int[] thePoint) {
        if (thePoint[0] > scale || thePoint[0] < 1 || thePoint[1] > scale || thePoint[1] < 1 || thePoint[2] >4 || thePoint[2] < 1){
            System.out.println("Coordinate or type ERROR, please input again.");
        } else if (thePoint[2] == 1) {
            youThinkThePointIsSafe(thePoint[0], thePoint[1]);
        } else if (thePoint[2] == 2) {
            youThinkThePointIsMine(thePoint[0], thePoint[1]);
        } else if (thePoint[2] == 3) {
            youAreNotSureYet(thePoint[0], thePoint[1]);
        } else if (thePoint[2] == 4) {
            youChangedYouMind(thePoint[0], thePoint[1]);
        }
    }

    private static void youThinkThePointIsSafe(int x, int y) {
        boolean thisIsMine = thisIsMine(x, y);

        if(thisIsMine) {
            mineMap[x][y] = "*";
            printRecentlyMineMap();
            System.out.println("YOU LOSE");
            System.exit(0);
        } else {
            mineMap[x][y] = MapIntializer.getMap(x - 1, y - 1);
            openSafeArea(x, y);
        }
    }

    private static void youThinkThePointIsMine(int x, int y) {
        boolean thisIsMine = thisIsMine(x, y);

        if(thisIsMine && mineMap[x][y].equals(" ")) {
            mineLeft = mineLeft - 1;
            printMineLeft = printMineLeft - 1;
            mineMap[x][y] = "M";
        } else if(!thisIsMine && mineMap[x][y].equals(" ")) {
            printMineLeft = printMineLeft - 1;
            mineMap[x][y] = "M";
        } else {
            System.out.println("Coordinate or type ERROR, please input again");
        }
    }

    private static void youAreNotSureYet(int x, int y) {
        if(mineMap[x][y].equals(" ")) {
            mineMap[x][y] = "?";
        } else {
            System.out.println("Coordinate or type ERROR, please input again");
        }
    }

    private static void youChangedYouMind(int x, int y) {
        if (!mineMap[x][y].equals("?") && !mineMap[x][y].equals("M")) {
            System.out.println("You hava not decided, yet or this place is a safe.");
        } else if (MapIntializer.getMap(x - 1, y - 1).equals("M") && mineMap[x][y].equals("M")){
            mineLeft = mineLeft + 1;
            printMineLeft = printMineLeft + 1;
            mineMap[x][y] = " ";
        } else if (!MapIntializer.getMap(x - 1, y - 1).equals("M") && mineMap[x][y].equals("M")) {
            printMineLeft = printMineLeft + 1;
            mineMap[x][y] = " ";
        } else if (mineMap[x][y].equals("?")) {
            mineMap[x][y] = " ";
        }
    }

    private static void openSafeArea(int x, int y) {
        if (!MapIntializer.getMap(x - 1, y - 1).equals("*")) {
            openSafeAreaUp(x, y);
            openSafeAreaDown(x, y);
            openSafeAreaLeft(x, y);
            openSafeAreaRight(x, y);
        }
    }

    private static void openSafeAreaUp(int x, int y) {
        if(x - 1 > 0 && MapIntializer.getMap(x - 2, y - 1).equals("0") && mineMap[x - 1][y].equals(" ")) {
            mineMap[x - 1][y] = "0";
            openSafeArea(x - 1, y);
        } else if (x - 1 > 0 && !MapIntializer.getMap(x - 2, y - 1).equals("*") && mineMap[x - 1][y].equals(" ")) {
            mineMap[x - 1][y] = MapIntializer.getMap(x - 2, y - 1);
        }
    }

    private static void openSafeAreaDown(int x, int y) {
        if(x + 1 < scale + 1 && MapIntializer.getMap(x, y - 1).equals("0") && mineMap[x + 1][y].equals(" ")) {
            mineMap[x + 1][y] = "0";
            openSafeArea(x + 1, y);
        } else if (x + 1 < scale + 1 && !MapIntializer.getMap(x , y - 1).equals("*") && mineMap[x + 1][y].equals(" ")) {
            mineMap[x + 1][y] = MapIntializer.getMap(x, y - 1);
        }
    }

    private static void openSafeAreaLeft(int x, int y) {
        if( y - 1 > 0 && MapIntializer.getMap(x - 1, y - 2).equals("0") && mineMap[x][y - 1].equals(" ")) {
            mineMap[x][y - 1] = "0";
            openSafeArea(x, y - 1);
        } else if (y - 1 > 0 && !MapIntializer.getMap(x - 1, y - 2).equals("*" ) && mineMap[x][y - 1].equals(" ")) {
            mineMap[x][y - 1] = MapIntializer.getMap(x - 1, y - 2);
        }
    }

    private  static void openSafeAreaRight(int x, int y) {
        if( y + 1 < scale + 1 && MapIntializer.getMap(x - 1, y).equals("0") && mineMap[x][y + 1].equals(" ")) {
            mineMap[x][y + 1] = "0";
            openSafeArea(x, y + 1);
        } else if (y + 1 < scale + 1 && !MapIntializer.getMap(x - 1, y).equals("*") && mineMap[x][y + 1].equals(" ")) {
            mineMap[x][y + 1] = MapIntializer.getMap(x - 1, y);
        }
    }

    /**
     * 获取当前的游戏进度，即剩余需要排的地雷数
     * @return 剩余的地雷数
     */
    public static int getMineLeft() {
        return mineLeft;
    }

    public static int getPrintMineLeft() {
        return printMineLeft;
    }
}