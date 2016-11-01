package com.example.abel.activitat1uf1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private EditText editFecha;
    private DatePickerDialog fromDatePickerDialog;

    /**
     * En el metodo onCreate coloco un listener al botón y al editText que contiene el DatePicker.
     * Creo el objeto DatePickerDialog y le pongo un formato de año, mes y dia utilizando Calendar.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer mediaPlayer;
        mediaPlayer = MediaPlayer.create(this,R.raw.halloween);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100,100);
        mediaPlayer.start();
        Button btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(this);
        editFecha = (EditText) findViewById(R.id.editFecha);
        editFecha.setInputType(InputType.TYPE_NULL);
        editFecha.requestFocus();
        editFecha.setOnClickListener(this);
        Calendar cal = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, this, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Creo un objeto Intent que hace referencia al otro layout.
     * Si se clica en el editText que contiene DatePickerDialog mostrara la interfaz para seleccionar la fecha.
     * Si se clica en el boton, se hacen las llamadas a todos los elementos y se envian al otro layout utilizando Intent.
     * En el caso de los checkBoxes, se envia un boolean dependiendo de si esta marcado o no.
     * E iniciamos la otra activity.
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, TextosActivity.class);
        if (v == editFecha) {
            fromDatePickerDialog.show();
        }
        if (v.getId() == R.id.btnCalcular) {
            EditText editNombre = (EditText) findViewById(R.id.editNombre);
            i.putExtra("Nombre", editNombre.getText().toString());
            EditText editFecha = (EditText) findViewById(R.id.editFecha);
            i.putExtra("Fecha", editFecha.getText().toString());
            Spinner spinner_sexo = (Spinner) findViewById(R.id.spinner_sexo);
            i.putExtra("Sexo", spinner_sexo.getSelectedItem().toString());
            CheckBox azucar = (CheckBox) findViewById(R.id.cb1);
            CheckBox droga = (CheckBox) findViewById(R.id.cb2);
            CheckBox colacao = (CheckBox) findViewById(R.id.cb3);
            i.putExtra("Azucar", azucar.isChecked());
            i.putExtra("Droga", droga.isChecked());
            i.putExtra("Colacao", colacao.isChecked());
            startActivity(i);
        }
    }

    /**
     * Este es el metodo que se ha de implementar cuando se implementa DatePickerDialog.OnDateSetListener.
     * Se formatea el Calendar y se escribe en el editText de la fecha.
     * @param view
     * @param año
     * @param mes
     * @param dia
     */
    @Override
    public void onDateSet(DatePicker view, int año, int mes, int dia) {
        Calendar cal = Calendar.getInstance();
        cal.set(año, mes, dia);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        editFecha.setText(dateFormatter.format(cal.getTime()));
    }

}
