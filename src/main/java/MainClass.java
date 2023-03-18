import controller.MainController;
import view.InputView;
import view.QueuesView;


public class MainClass {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        QueuesView queuesView = new QueuesView();
        MainController mainController = new MainController(inputView, queuesView);
    }
}
