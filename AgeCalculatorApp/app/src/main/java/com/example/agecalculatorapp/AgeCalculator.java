package com.example.agecalculatorapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class AgeCalculator extends AppCompatActivity {

    LinearLayout birthDateLayout;
    LinearLayout currentDateLayout;
    TextView birthTv;
    TextView todayTv;
    TextView yearTv;
    TextView monthTv;
    TextView dayTv;

    String todaysDateStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_age_calculator);

        birthDateLayout = findViewById(R.id.birthDateLinearLayout);
        currentDateLayout = findViewById(R.id.currentDateLinearLayout);
        birthTv = findViewById(R.id.inputBirthDate);
        todayTv = findViewById(R.id.currentDateTxt);
        yearTv = findViewById(R.id.resYear);
        monthTv = findViewById(R.id.resMonth);
        dayTv = findViewById(R.id.resDay);

        LocalDate today = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
        todaysDateStr = today.toString(dtf);

        birthTv.setText(todaysDateStr);
        todayTv.setText(todaysDateStr);

        birthDateLayout.setOnClickListener(v -> showBirthCalendar());
        currentDateLayout.setOnClickListener(v -> showCurrentCalendar());
        findViewById(R.id.calculateAge).setOnClickListener(v -> calculateAge());
    }

    public void showBirthCalendar() {
        LocalDate now = LocalDate.now();
        new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {
            String selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year);
            birthTv.setText(selectedDate);
        }, now.getYear(), now.getMonthOfYear() - 1, now.getDayOfMonth()).show();
    }

    public void showCurrentCalendar() {
        LocalDate now = LocalDate.now();
        new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {
            String selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year);
            todayTv.setText(selectedDate);
        }, now.getYear(), now.getMonthOfYear() - 1, now.getDayOfMonth()).show();
    }

    public void calculateAge() {
        String birthDateStr = birthTv.getText().toString();
        String currentDateStr = todayTv.getText().toString();

        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");

        try {
            LocalDate birthDate = dtf.parseLocalDate(birthDateStr);
            LocalDate currentDate = dtf.parseLocalDate(currentDateStr);
            LocalDate todaysDate = LocalDate.now(); // Using LocalDate.now() directly

            if (birthDate.isAfter(currentDate)) {
                Snackbar.make(findViewById(android.R.id.content), "Birth date cannot be after the current date.", Snackbar.LENGTH_SHORT).show();
                return;
            }

            if (currentDate.isAfter(todaysDate)) {
                Snackbar.make(findViewById(android.R.id.content), "Current date cannot be after today's date.", Snackbar.LENGTH_SHORT).show();
                return;
            }

            Period periodYearsMonths = new Period(birthDate, currentDate);
            int years = periodYearsMonths.getYears();
            int months = periodYearsMonths.getMonths();
            int days = 0;

            if (currentDate.getDayOfMonth() < birthDate.getDayOfMonth()) {
                LocalDate birthEndOfMonth = birthDate.dayOfMonth().withMaximumValue();
                Period periodToEndOfBirthMonth = new Period(birthDate, birthEndOfMonth);
                days += periodToEndOfBirthMonth.getDays();
                days += currentDate.getDayOfMonth();
                months--;
                if (months < 0) {
                    years--;
                    months += 12;
                }
            } else {
                days = currentDate.getDayOfMonth() - birthDate.getDayOfMonth();
            }

            yearTv.setText(String.valueOf(years));
            monthTv.setText(String.valueOf(months));
            dayTv.setText(String.valueOf(days));

        } catch (IllegalArgumentException e) {
            Snackbar.make(findViewById(android.R.id.content), "Invalid date format", Snackbar.LENGTH_SHORT).show();
            yearTv.setText("--");
            monthTv.setText("--");
            dayTv.setText("--");
        }
    }
}