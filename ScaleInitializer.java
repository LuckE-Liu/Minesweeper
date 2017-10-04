import java.util.Scanner;

/**
 * 获取地图的大小，地图是一个正方形
 */
class ScaleInitializer {
    private static int scale;//scale > 5 || scale < 11

    private ScaleInitializer() {
        inputTheScale();
    }

    /**
     * 创建地图规模的实例
     * @return 地图规模实例
     */
    public static ScaleInitializer getInitializer() {
        return InitializerInstance.INSTANCE;
    }

    private static class InitializerInstance {
        static final ScaleInitializer INSTANCE = new ScaleInitializer();
    }

    /**
     * 输入一个规模，规模的大小应当大于等于5并且小于等于15.
     */
    private void inputTheScale() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the Scale, press your ENTER to confirm: ");

        try {
            scale = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Input Error, try another time!");
            inputTheScale();
        }//使用try-catch来防止输入的数不是数字

        if(scale < 5 || scale > 15) {
            System.out.println("Scale is too large, should be [5,15]");
            inputTheScale();
        }
    }

    /**
     * 用来返回规模的值
     * @return scale 规模的值
     */
    public static int getScale() {
        return scale;
    }
}