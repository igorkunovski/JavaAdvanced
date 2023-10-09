public class Main {
    public static void main(String[] args) {
        Developer devFront = new FrontendDeveloper();
        devFront.developGUI();
        devFront.developPHP();

        Developer devBack = new BackendDeveloper();
        devBack.developPHP();
        devBack.developGUI();

        Developer devFull = new FullstackDeveloper();
        devFull.developGUI();
        devFull.developPHP();
    }
}