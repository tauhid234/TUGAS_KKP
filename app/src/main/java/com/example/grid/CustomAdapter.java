package com.example.grid;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.inventory_management_dlh.Home;
import com.example.inventory_management_dlh.Inventory;
import com.example.inventory_management_dlh.MainActivity;
import com.example.inventory_management_dlh.R;


public class CustomAdapter extends BaseAdapter {

    String[] result;
    Context context;
    int[] imageID;
    private static LayoutInflater layoutInflater = null;
    public CustomAdapter(Home home, String[] nameList, int[] imageList) {
        this.context = home;
        result = nameList;
        imageID = imageList;
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
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View row;

        row = layoutInflater.inflate(R.layout.list_grid,null);
        holder.tv = row.findViewById(R.id.textGrid);
        holder.img = row.findViewById(R.id.imgView);

        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageID[position]);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,result[position], Toast.LENGTH_LONG).show();
                if(position==0){
                    Intent intent = new Intent(context.getApplicationContext(), Inventory.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }else if(position == 1){
                    System.out.println("REPORT");
                        if(ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                            sendSMS();
                        }
                }
            }
        });
        return row;
    }

    private void sendSMS(){
            SmsManager smsManager  = SmsManager.getDefault();
            smsManager.sendTextMessage("085695673132",null,"SEND SMS LOCAL ANDROID",null,null);
            Toast.makeText(context,"Message is send",Toast.LENGTH_SHORT).show();

    }
}
