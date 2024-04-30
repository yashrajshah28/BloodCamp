package com.yashraj.bloodcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class Donor extends AppCompatActivity {
    ListView lvdata;
    ArrayList list;

    TextView listviewdonor;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);

        listviewdonor=findViewById(R.id.listviewdonor);
        DatabaseHelper dbhelper = new DatabaseHelper(getApplicationContext());

        Cursor cursor=dbhelper.read_data_donor();
        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_SHORT).show();
        }

        StringBuilder builder=new StringBuilder();

        while (cursor.moveToNext()){
            builder.append(" " + cursor.getString(0)+"\n");
            builder.append("DName : " + cursor.getString(1)+"\n");
            builder.append("DEmail : " + cursor.getString(2)+"\n");
            builder.append("DPhone : " + cursor.getString(3)+"\n");
            builder.append("DAddress : " + cursor.getString(4)+"\n");
            builder.append("DCity : " + cursor.getString(5)+"\n");
            builder.append("DPincode : " + cursor.getString(6)+"\n");
            builder.append("DBloodGroup : " + cursor.getString(7)+"\n");
            builder.append("DGender : " + cursor.getString(8)+"\n");
//            builder.append("SBloodGroup : " + cursor.getString(7)+"\n");
//            builder.append("Gender : " + cursor.getString(8)+"\n");
        }

        listviewdonor.setText(builder.toString());
    }
}



//package com.yashraj.bloodcamp;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.database.Cursor;
//import android.os.Bundle;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.Toast;
//import java.util.ArrayList;
//
//public class Donor extends AppCompatActivity {
//    ListView lvdata;
//
//    ArrayList list;
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_donor);
//        lvdata=(ListView) findViewById(R.id.listviewdonor);
//        list= new ArrayList();
//        DatabaseHelper dbhelper = new DatabaseHelper(getApplicationContext());
//        Cursor resultset = dbhelper.read_data_donor();
//        list.clear();
//        if(resultset.getCount()==0)
//            Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_SHORT).show();
//        else {
//            while(resultset.moveToNext())
//            {
//                list.add(resultset.getString(0)+" "+ "\n"+ "DName : "+ resultset.getString(1)+" "+"\n"+ "DEmail : "+ resultset.getString(2)+" "+"\n"+ "DPhone : "+ resultset.getString(3)+" "+"\n"+ "DAddress : "+ resultset.getString(4)+" "+"\n"+ "DCity : "+ resultset.getString(5)+" "+"\n"+ "DPincode : "+ resultset.getString(6));
////                list.add(resultset.getString(0)+" "+ "\n"+ "DName : "+ resultset.getString(1)+" "+"\n"+ "DEmail : "+ resultset.getString(2)+" "+"\n"+ "DPhone : "+ resultset.getString(3)+" "+"\n"+ "DAddress : "+ resultset.getString(4)+" "+"\n"+ "DCity : "+ resultset.getString(5)+" "+"\n"+ "DPincode : "+ resultset.getString(6)+" "+"\n"+ "SBloodGroup : "+ resultset.getString(7)+" "+"\n"+ "RGgender : "+ resultset.getString(8));
//            }
//            ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),
//                    android.R.layout.simple_list_item_1,list);
//            lvdata.setAdapter(adapter);
//        }
//    }
//}