package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AppActivity extends AppCompatActivity {
    private GridView gridView;
    private MyAdapter myAdapter;
    private int contador=0;
    private List<String> notas;
    TextInputEditText textInput;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        notas = new ArrayList<String>();

        gridView = findViewById(R.id.gridView);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AppActivity.this, "clic en "+notas.get(position), Toast.LENGTH_LONG).show();
            }
        });




        myAdapter = new MyAdapter(this, R.layout.grid_item, notas);
        gridView.setAdapter(myAdapter);

        registerForContextMenu(gridView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_item:
                //agregamos un nuevo nombre
                if(this.notas.isEmpty() == true){
                    this.notas.add("nota1");
                }else {
                    textInput = (TextInputEditText) findViewById(R.id.textViewGrid);
                    text = textInput.getText().toString();
                    this.notas.add(text);
                }


                //Notificamos al adaptador del cambio producido
                this.myAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.notas.get(info.position));
        inflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.delete_item:
                //eliminamos el item clickeado
                this.notas.remove(info.position);
                //notificamos al adapter del cambio
                this.myAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}