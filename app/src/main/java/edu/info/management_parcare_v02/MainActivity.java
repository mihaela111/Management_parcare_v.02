package edu.info.management_parcare_v02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editNume, editPrenume, editNumar_dosar,  editCod_organigrama, editCentru_cost, editNumar_auto_1, editNumar_auto2, editNumar_auto3,editTextId, editCauta;
    Button btnAdaugaNumar;
    Button btnverificaNumar;
    Button btnActualizeazaInformatii;
    Button btnAfiseazaDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DatabaseHelper(this);
        editNume= (EditText)findViewById(R.id.editText_nume);
        editPrenume= (EditText)findViewById(R.id.editText_prenume);
        editNumar_dosar= (EditText)findViewById(R.id.editText_numar_dosar);
        editCod_organigrama= (EditText)findViewById(R.id.editText_cod_organigrama);
        editCentru_cost= (EditText)findViewById(R.id.editText_centru_cost);
        editNumar_auto_1= (EditText)findViewById(R.id.editText_numar_auto_2);
        editNumar_auto2= (EditText)findViewById(R.id.editText_numar_auto_2);
        editNumar_auto3= (EditText)findViewById(R.id.editText_numar_auto_3);
        editTextId=(EditText)findViewById(R.id.editText_id) ;
        editCauta=(EditText) findViewById(R.id.editText_cauta);

        btnAdaugaNumar=(Button)findViewById(R.id.button_adauga_angajat);
        btnverificaNumar= (Button) findViewById(R.id.button_verifica_numar);
        btnActualizeazaInformatii=(Button) findViewById(R.id.button_actualizeaza_informatii);
        btnAfiseazaDb=(Button) findViewById((R.id.button_afiseaza_db));
        AddData();
        viewAll();
        UpdateData();
        verificaNumar();
    }

    public void UpdateData(){
        btnActualizeazaInformatii.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        boolean isUpdate =myDb.updateData(editTextId.getText().toString(),
                                editNume.getText().toString(),
                                editPrenume.getText().toString(),
                                editNumar_dosar.getText().toString(),
                                editCod_organigrama.getText().toString(),
                                editCentru_cost.getText().toString(),
                                editNumar_auto_1.getText().toString(),
                                editNumar_auto2.getText().toString(),
                                editNumar_auto3.getText().toString()
                        );
                        if (isUpdate== true)
                            Toast.makeText(MainActivity.this,"Informatiile au fost actualizate", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Informatiile NU au putut fi actualizate", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void AddData(){
        btnAdaugaNumar.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        boolean isInserted = myDb.insertData(editNume.getText().toString(),
                                editNume.getText().toString(),
                                editPrenume.getText().toString(),
                                editNumar_dosar.getText().toString(),
                                editCod_organigrama.getText().toString(),
                                editCentru_cost.getText().toString(),
                                editNumar_auto_1.getText().toString(),
                                editNumar_auto2.getText().toString(),
                                editNumar_auto3.getText().toString());


                        if (isInserted== true)
                            Toast.makeText(MainActivity.this,"Angajatul a fost adaugat in baza de date", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Angajatul NU a fost adaugat in baza de date", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void verificaNumar(){
        btnverificaNumar.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){ String cautare = editCauta.getText().toString();
                        if(myDb.cautaNumar(cautare)) {
                            showMessage("aplicati instiintare:"+"\n"+" VA RUGAM AFISATI PERMISUL"," "+"\n"+
                                    "daca s-a parcat in loc nepermis aplicati si: "+"\n"+
                                    "NOTIFICARE"+"\n"+" "+"\n"+

                                    "pentru parcare in loc nepermis, cu permisul de parcare afisat "+
                                    "aplicati doar: "+"\n"+"NOTIFICARE");
                        }
                        else{
                            showMessage("aplicati AVERTISMENT","autoturismul nu se afla in baza de date");
                        }


                    }



                }

        );
    }

    public void viewAll(){
        btnAfiseazaDb.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Cursor res= myDb.getAllData();
                        if (res.getCount()==0){
                            // show message
                            showMessage("error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("ID :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("prenume :"+ res.getString(2)+"\n");
                            buffer.append("numar inmatriculare :"+ res.getString(6)+"\n\n");
                        }
                        // show all data
                        showMessage("Data", buffer.toString());

                    }
                }
        );
    }
    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

