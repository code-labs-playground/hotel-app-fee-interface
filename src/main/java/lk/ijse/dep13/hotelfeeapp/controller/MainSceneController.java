package lk.ijse.dep13.hotelfeeapp.controller;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MainSceneController {
    public DatePicker checkIn;
    public DatePicker checkOut;
    public Spinner<Integer> spnCount;
    public Text txtTotal;

    private static final BigDecimal PERDAYCOST = new BigDecimal("10000");

    public void initialize() {
        spnCount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 0));

        LocalDate checkInDate = checkIn.getValue();
        checkIn.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);

        });

        txtTotal.setText("0.00");
    }
}
