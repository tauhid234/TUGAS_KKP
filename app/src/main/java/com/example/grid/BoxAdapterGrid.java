package com.example.grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inventory_management_dlh.Home;
import com.example.inventory_management_dlh.R;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

public class BoxAdapterGrid extends BaseAdapter {

    String[] result;
    Context context;
    int[] bgID;

    private static LayoutInflater layoutInflater = null;

    public BoxAdapterGrid(Home home, String[] content, int[] bg){
        this.context = home;
        this.bgID = bg;
        this.result = content;
        layoutInflater = (LayoutInflater) context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder{
        TextView txt;
        ImageView img;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder hold = new Holder();
        View v;
        v = layoutInflater.inflate(R.layout.boxgrid_view, null);
        hold.txt = v.findViewById(R.id.konten);
        hold.img = v.findViewById(R.id.boxView);

        hold.txt.setText(result[position]);
        hold.img.setImageResource(bgID[position]);
        return v;
    }
}
