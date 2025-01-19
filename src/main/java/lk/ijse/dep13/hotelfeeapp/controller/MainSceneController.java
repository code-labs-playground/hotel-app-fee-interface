package lk.ijse.dep13.hotelfeeapp.controller;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.text.Text;

public class MainSceneController {
    public DatePicker checkIn;
    public DatePicker checkOut;
    public Spinner<?> spnCount;
    public Text txtTotal;

    public void initialize() {
        checkIn = new DatePicker();
        checkOut = new DatePicker();
        spnCount = new Spinner();
        txtTotal.setText("0.00");
    }
}
