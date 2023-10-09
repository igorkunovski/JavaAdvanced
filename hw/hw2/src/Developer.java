public abstract class Developer implements FrontEnd, BackEnd{

    @Override
    public void developPHP() {
        System.out.println("I can't do this work! I am backend developer");
    }

    @Override
    public void developGUI() {
        System.out.println("I can't do this work! I am frontend developer");
    }
}
