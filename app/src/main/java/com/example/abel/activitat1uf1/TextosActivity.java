package com.example.abel.activitat1uf1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Abel on 30/10/2016.
 */

public class TextosActivity extends AppCompatActivity {

    /**
     * Creo un objeto Intent para recibir datos.
     * LLamo a todos los elementos del layout y guardo en variables los datos recibidos del anterior layout utilizando Intent.
     * Escribo el titulo y la fecha utilizando strings guardados en strings.xml y concatenandole las variables que contienen los
     * datos recibidos por Intent.
     * Guardo en la variable edad el resultado del metodo edad().
     * Escribo la edad con la que murio utilizando un string de strings.xml y concatenandole la variable edad.
     * Llamo al metodo textos() al que le paso el objeto Intent.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textos);
        Intent i = getIntent();
        TextView textViewTitulo = (TextView) findViewById(R.id.textViewTitulo);
        TextView textViewFecha = (TextView) findViewById(R.id.textViewFecha);
        TextView textViewMuerte = (TextView) findViewById(R.id.textViewMuerte);
        String nombre = i.getStringExtra("Nombre");
        String fecha = i.getStringExtra("Fecha");
        textViewTitulo.setText(getResources().getString(R.string.textViewTitulo) + " " + nombre);
        textViewFecha.setText(getResources().getString(R.string.textViewFecha) + " " + fecha);
        int edad = edad();
        textViewMuerte.setText(getResources().getString(R.string.textViewMuerte) + " " + edad + " " + getResources().getString(R.string.edad));
        textos(i);
    }

    /**
     * Metodo que devuelve un numero entre 1 y 120.
     * @return la edad resultante.
     */
    public int edad() {
        Random r = new Random();
        int edad = r.nextInt(121 - 1) + 1;
        return edad;
    }

    /**
     * Guardo en variables los resultados boolean de los checkBoxes y el sexo recibidos por Intent.
     * Guardo en dos arrays los textos de muerte escritos en un array en strings.xml, y los sexos escritos en un array de strings.xml.
     * Tambien guardo en 2 arrays los textos que se mostraran aleatoriamente (de hombre y mujer), que estan guardados en arrays
     * en strings.xml.
     * Hago una llamada al TextView que contendra los textos de muerte.
     * Y empieza el tratamiento de los textos.
     * @param i
     */
    public void textos(Intent i) {
        String sexo = i.getStringExtra("Sexo");
        boolean azucar = i.getBooleanExtra("Azucar", false);
        boolean droga = i.getBooleanExtra("Droga", false);
        boolean colacao = i.getBooleanExtra("Colacao", false);

        String[] textos = getResources().getStringArray(R.array.textos);
        String[] sexos = getResources().getStringArray(R.array.sexo);
        String[] aleHombres = getResources().getStringArray(R.array.aleHombres);
        String[] aleMujeres = getResources().getStringArray(R.array.aleMujeres);
        TextView textViewTexto = (TextView) findViewById(R.id.textViewTexto);
        //Si el sexo equivale a hombre...
        if (sexo.equals(sexos[0])) {
            if (azucar && droga && colacao) { //Si estan todos los checkBoxes marcados...
                textViewTexto.setText(textos[0]); //Escribo la primera posicion del array de textos.
            } else if (azucar == false && droga == false && colacao == false) { //Si no hay ninguno marcado...
                textViewTexto.setText(textos[1]); //Escribo la primera posicion
            } else { //Si solo hay uno o dos marcados los elijo aleatoriamente del array de aleatorios de hombres.
                Random r = new Random();
                int pos = r.nextInt(aleHombres.length - 0) + 0;
                textViewTexto.setText(aleHombres[pos]);
            }

            //Aqui es lo mismo, solo que entra si el sexo equivale a mujer, las posiciones del array de textos cambian, y los
            //aleatorios utilizan el array de aleatorios de mujeres.
        } else if (sexo.equals(sexos[1])) {
            if (azucar && droga && colacao) {
                textViewTexto.setText(textos[2]);
            } else if (azucar == false && droga == false && colacao == false) {
                textViewTexto.setText(textos[3]);
            } else {
                Random r = new Random();
                int pos = r.nextInt(aleMujeres.length - 0) + 0;
                textViewTexto.setText(aleMujeres[pos]);
            }
        }
    }

}






