package com.yashraj.bloodcamp;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class CampList extends AppCompatActivity {
    TextView listviewdata;
    ArrayList list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_list);
        listviewdata=findViewById(R.id.listviewdata);
        list= new ArrayList();
        DatabaseHelper dbhelper = new DatabaseHelper(getApplicationContext());
        Cursor resultset = dbhelper.read_data();
        list.clear();
        if(resultset.getCount()==0)
            Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_SHORT).show();
        else {
            while(resultset.moveToNext())
            {
                list.add(resultset.getString(0)
                        +" "+ "\n"+ "CampName : "+ resultset.getString(1)
                        +" "+"\n"+ "CampAddress : "+ resultset.getString(2)
                        +" "+"\n"+ "PhoneNumber : "+ resultset.getString(3)
                        +" "+"\n");
            }
            ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),
                    android.R.layout.simple_list_item_1,list);
            listviewdata.setText(list.toString());
        }
    }
}



//package com.yashraj.bloodcamp;
//
//import android.database.Cursor;
//import android.os.Bundle;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import java.util.ArrayList;
//
//public class CampList extends AppCompatActivity {
//    ListView lvdata;
//    ArrayList list;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_camp_list);
//
//
//        lvdata=(ListView) findViewById(R.id.listviewdata);
//        list= new ArrayList();
//
//        DatabaseHelper dbhelper =new DatabaseHelper(getApplicationContext());
//        Cursor cursor=dbhelper.getAllData();
//
//        list.clear();
//        if(cursor.getCount()==0) {
//            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            while(cursor.moveToNext())
//            {
//                list.add(cursor.getString(0)+" "+ cursor.getString(1));
//            }
//            ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),
//                    android.R.layout.simple_list_item_1,list);
//            lvdata.setAdapter(adapter);
//        }
//    }
//
//}
