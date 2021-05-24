package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<String> notas;

    public MyAdapter(Context context, int layout, List<String> notas) {
        this.context = context;
        this.layout = layout;
        this.notas = notas;
    }

    @Override
    public int getCount() {
        return this.notas.size();
    }

    @Override
    public Object getItem(int position) {
        return this.notas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //View v = convertView;

        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            //convertView = layoutInflater.inflate(R.layout.list_item, null);
            convertView = layoutInflater.inflate(R.layout.grid_item, null);
            holder = new ViewHolder();
            holder.notaTextView = convertView.findViewById(R.id.textViewGrid);
            convertView.setTag(holder);
            //v = layoutInflater.inflate(R.layout.list_item, null);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        String notaActual = notas.get(position);
        holder.notaTextView.setText(notaActual);

        return convertView;
    }

    static class ViewHolder {
        public TextView notaTextView;
    }
}
