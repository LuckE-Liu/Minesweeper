public class Controller {
    public static void main(String[] args) {
        ScaleInitializer.getInitializer();
        MapIntializer.getMapInstance();
        MineInitializer.getMine();

        System.out.println("Choose a point with input it's x&y, then input the type of the point.");
        System.out.println("x&y start from 1");
        System.out.println("type 1 = safe");
        System.out.println("type 2 = mine");
        System.out.println("type 3 = not sure");
        System.out.println("type 4 = cancel your choice");
        System.out.println("EXAMPLE : 1 1 2, means x = 1, y = 1. I think this is a mine.");
        System.out.println("EXAMPLE : 1 1 4, means x = 1, y = 1. I think this is not a mine.");

        SweepControler.getSweepControlerInstance();
        SweepControler.printRecentlyMineMap();

        do {
            SweepControler.chooseOnePoint();
        } while(SweepControler.getMineLeft()!= 0 && SweepControler.getPrintMineLeft() != 0);

        System.out.println("YOU WIN!");
    }
}