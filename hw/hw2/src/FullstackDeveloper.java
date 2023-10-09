public class FullstackDeveloper extends Developer implements FrontEnd, BackEnd{
    @Override
    public void developPHP() {
        System.out.println("trying to establish server connection");
    }

    @Override
    public void developGUI() {
        System.out.println("busy with server connection main window application");
    }
}
