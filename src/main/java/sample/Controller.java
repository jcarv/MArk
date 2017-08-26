package sample;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class Controller {

    public Button btnFileDialog;

    public void Test() {
        try {
            MLPClassifierLinear.main(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void FileDialog() {
        System.out.println("ola");
        Window stage = btnFileDialog.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(stage);
        System.out.println("ola2");
    }
}
