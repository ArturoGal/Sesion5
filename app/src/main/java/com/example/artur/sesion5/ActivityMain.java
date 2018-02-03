package com.example.artur.sesion5;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityMain extends AppCompatActivity {
    Alumno alumno;
    AutoCompleteTextView textView;
    Spinner spinner;
    RadioButton radioButton;
    EditText nombre, telefono;
    CheckBox deporte;

    public class Alumno{
        AutoCompleteTextView textView;
        Spinner spinner;
        RadioButton radioButton;
        EditText nombre, telefono;
        CheckBox deporte;

        public Alumno(AutoCompleteTextView textView, Spinner spinner, RadioButton radioButton, EditText nombre, EditText telefono, CheckBox deporte) {
            this.textView = textView;
            this.spinner = spinner;
            this.radioButton = radioButton;
            this.nombre = nombre;
            this.telefono = telefono;
            this.deporte = deporte;
        }

        public String toString() {
            return "Nombre: " + nombre.getText() +
                    "\nTeléfono: " + telefono.getText() +
                    "\nEscolaridad: " + spinner.getSelectedItem().toString() +
                    "\nGénero: " + radioButton.getText() +
                    (textView.getText().length() > 0 ? "\nLibro Favorito: " + textView.getText() : "") +
                    "\nPractica Deporte: " + (deporte.isChecked()? "Sí" : "No");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText) findViewById(R.id.nombre);
        telefono = (EditText) findViewById(R.id.telefono);
        deporte = (CheckBox) findViewById(R.id.deporte);
        radioButton = (RadioButton) findViewById(R.id.femenino);


        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.escolaridad, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        textView = findViewById(R.id.activity_main_libros);
        String[] libros = getResources().getStringArray(R.array.libros);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, libros);
        textView.setAdapter(adapter2);
    }
    public void clear(){
        textView.setText("");
        textView.setText("");
        radioButton = (RadioButton) findViewById(R.id.femenino);
        radioButton.setChecked(true);
        spinner.setSelection(0);
        nombre.setText("");
        telefono.setText("");
        deporte.setChecked(false);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.femenino:
                if (checked)
                   radioButton = (RadioButton)findViewById(R.id.femenino);
                    break;
            case R.id.masculino:
                if (checked)
                    radioButton = (RadioButton)findViewById(R.id.masculino);
                    break;
        }
    }

    public void onButtonClicked(View view) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage((CharSequence) "¿Desea limpiar el contenido?");
        alertDialog.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clear();
            }
        });
        alertDialog.create();
        alertDialog.show();
    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Alumno alumno = new Alumno(textView, spinner, radioButton, nombre, telefono, deporte);
        if(alumno.nombre.getText().length() > 0 && alumno.telefono.getText().length() > 0) {
            Toast.makeText(this, alumno.toString(), Toast.LENGTH_LONG).show();
            clear();
        }
        else Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show();
        return true;
    }

    public class SpinnerActivity extends Activity
                                implements AdapterView.OnItemSelectedListener{
       @Override
       public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       }

       @Override
       public void onNothingSelected(AdapterView<?> parent) {
       }
   }
}
