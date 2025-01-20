package lk.ijse.dep13.hotelfeeapp.controller;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicReference;

public class MainSceneController {
    public DatePicker checkIn;
    public DatePicker checkOut;
    public Spinner<Integer> spnCount;
    public Text txtTotal;

    private static final BigDecimal PERDAYCOST = new BigDecimal("10000.00");

    public void initialize() {
        spnCount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 0));
        spnCount.valueProperty().addListener((observable, oldValue, newValue) -> {
            getTotal();
        });

        checkOut.setDisable(checkIn.getValue() == null);
        txtTotal.setText("0.00");

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

        // CheckOut Validation
        checkIn.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                checkOut.setDisable(false);
                getTotal();
            }
        });

        checkOut.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (checkIn.getValue() != null) {
                    if (date.isBefore(checkIn.getValue().plusDays(1)) || date.isAfter(checkIn.getValue().plusDays(6))) {
                        setDisable(true);
                    }
                } else {
                    setDisable(true);
                }
            }
        });

        checkOut.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                getTotal();
            }
        });
    }

    private void getTotal() {
        LocalDate checkInDate = checkIn.getValue();
        LocalDate checkOutDate = checkOut.getValue();
        long count = spnCount.getValue();

        if (checkInDate != null && checkOutDate != null) {
            long dayCount = ChronoUnit.DAYS.between(checkInDate, checkOutDate);

            BigDecimal total = PERDAYCOST.multiply(new BigDecimal(dayCount)).multiply(new BigDecimal(count));
            txtTotal.setText(String.format("%.2f", total));
        } else {
            txtTotal.setText("0.00");
        }
    }
}
