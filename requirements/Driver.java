import views.Settings;
import views.Table;
import views.Window;

public class Driver {
    public static void main(String[] args) {
        Window window = new Window("Poker", 1200, 800);
        window.setContent(new Settings(window));
    }
}
