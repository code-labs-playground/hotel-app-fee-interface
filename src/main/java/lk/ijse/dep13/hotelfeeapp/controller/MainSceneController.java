package lk.ijse.dep13.hotelfeeapp.controller;

import javafx.scene.control.DateCell;
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

    private static final BigDecimal PERDAYCOST = new BigDecimal("10000.00");

    public void initialize() {
        spnCount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 0));
        checkOut.setDisable(checkIn.getValue() == null);

        LocalDate today = LocalDate.now();
        // CheckIn Validation
        checkIn.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(today.plusDays(1))) {
                    setDisable(true);
                }
            }
        });
        LocalDate checkInDate = checkIn.getValue();

        // CheckOut Validation
        checkIn.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                checkOut.setDisable(false);
            }
        });

        checkOut.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (checkIn.getValue() != null) {
                    if (date.isBefore(checkIn.getValue().plusDays(1)) || date.isAfter(checkIn.getValue().plusDays(4))) {
                        setDisable(true);
                    }
                } else {
                    setDisable(true);
                }
            }
        });


        txtTotal.setText("0.00");
    }
}
