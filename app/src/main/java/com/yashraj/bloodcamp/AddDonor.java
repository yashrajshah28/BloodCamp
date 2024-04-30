//==========Notification code============

package com.yashraj.bloodcamp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AddDonor extends AppCompatActivity {

    private EditText DName, DEmail, DPhone, DAddress, DCity, DPincode, DBloodGroup, DGender;
    private Button btnAddDonor;
    private SQLiteDatabase sqLiteDatabase;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donor);

        DName = findViewById(R.id.DName);
        DEmail = findViewById(R.id.DEmail);
        DPhone = findViewById(R.id.DPhone);
        DAddress = findViewById(R.id.DAddress);
        DCity = findViewById(R.id.DCity);
        DPincode = findViewById(R.id.DPincode);
        DBloodGroup = findViewById(R.id.DBloodGroup);
        DGender = findViewById(R.id.DGender);
        btnAddDonor = findViewById(R.id.btnAddDonor);

        dbHelper = new DatabaseHelper(this);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        btnAddDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDonorAndNotify(); // Call method to add donor and show notification
            }
        });
    }

    private void addDonorAndNotify() {
        String DNameStr = DName.getText().toString().trim();
        String DEmailStr = DEmail.getText().toString().trim();
        String DPhoneStr = DPhone.getText().toString().trim();
        String DAddressStr = DAddress.getText().toString().trim();
        String DCityStr = DCity.getText().toString().trim();
        String DPincodeStr = DPincode.getText().toString().trim();
        String DBloodGroupStr = DBloodGroup.getText().toString().trim();
        String DGenderStr = DGender.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put("DName", DNameStr);
        values.put("DEmail", DEmailStr);
        values.put("DPhone", DPhoneStr);
        values.put("DAddress", DAddressStr);
        values.put("DCity", DCityStr);
        values.put("DPincode", DPincodeStr);
        values.put("DBloodGroup", DBloodGroupStr);
        values.put("DGender", DGenderStr);

        long newRowId = sqLiteDatabase.insert("donor", null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Donor Details Saved!", Toast.LENGTH_SHORT).show();
            showNotification(); // Show notification if donor added successfully
            finish(); // Optionally finish this activity
        } else {
            Toast.makeText(this, "Failed to Save Donor Details!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showNotification() {
        // Create a notification channel (for Android Oreo and higher)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "Donor_Channel";
            CharSequence channelName = "Donor Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // Create the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Donor_Channel")
                .setSmallIcon(R.drawable.iconnotify)
                .setContentTitle("New Donor Arrived")
                .setContentText("A new donor has arrived. Check donor details.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(1, builder.build());
    }
}
//==========Notification code============


//    package com.yashraj.bloodcamp;
//
//    import android.annotation.SuppressLint;
//    import android.content.ContentValues;
//    import android.database.sqlite.SQLiteDatabase;
//    import android.os.Bundle;
//    import android.view.View;
//    import android.widget.ArrayAdapter;
//    import android.widget.Button;
//    import android.widget.EditText;
//    import android.widget.Spinner;
//    import android.widget.Toast;
//
//    import androidx.activity.EdgeToEdge;
//    import androidx.appcompat.app.AppCompatActivity;
//    import androidx.core.graphics.Insets;
//    import androidx.core.view.ViewCompat;
//    import androidx.core.view.WindowInsetsCompat;
//
//    import java.util.ArrayList;
//    import java.util.Arrays;
//    import java.util.List;
//
//    public class AddDonor extends AppCompatActivity {
//
//        private Spinner SBloodGroup;
//        private EditText DName, DEmail, DPhone, DAddress, DCity, DPincode, RGgender;
//        private Button btnAddDonor;
//        SQLiteDatabase sqLiteDatabase;
//        DatabaseHelper dbHelper;
//
//
//        @SuppressLint({"MissingInflatedId", "WrongViewCast"})
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_add_donor);
//
//            DName = findViewById(R.id.DName);
//            DEmail = findViewById(R.id.DEmail);
//            DPhone = findViewById(R.id.DPhone);
//            DAddress = findViewById(R.id.DAddress);
//            DCity = findViewById(R.id.DCity);
//            DPincode = findViewById(R.id.DPincode);
//            RGgender = findViewById(R.id.RGgender);
//            btnAddDonor = findViewById(R.id.btnAddDonor);
//
//            SBloodGroup = findViewById(R.id.SBloodGroup);
//            dbHelper = new DatabaseHelper(this);
//            sqLiteDatabase = dbHelper.getWritableDatabase();
//
//            // Create a list of blood group options
//            List<String> bloodGroups = new ArrayList<>();
//            bloodGroups.add("Select your Blood Group");
//            bloodGroups.add("A+");
//            bloodGroups.add("A-");
//            bloodGroups.add("B+");
//            bloodGroups.add("B-");
//            bloodGroups.add("AB+");
//            bloodGroups.add("AB-");
//            bloodGroups.add("O+");
//            bloodGroups.add("O-");
//
//    //         Create an ArrayAdapter using the string array and a default spinner layout
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                    this,
//                    android.R.layout.simple_spinner_item,
//                    bloodGroups);
//
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//            // Apply the adapter to the spinner
//            SBloodGroup.setAdapter(adapter);
//
//            btnAddDonor.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    donor(); // Call registerUser method to validate fields and proceed to login if successful
//                }
//            });
//
//        }
//        private void donor(){
//            String DNameStr = DName.getText().toString().trim();
//            String DEmailStr = DEmail.getText().toString().trim();
//            String DPhoneStr = DPhone.getText().toString().trim();
//            String DAddressStr = DAddress.getText().toString().trim();
//            String DCityStr = DCity.getText().toString().trim();
//            String DPincodeStr = DPincode.getText().toString().trim();
//    //        String SBloodGroupStr = SBloodGroup.getText().toString().trim();
//    //        String SBloodGroupStr = String.valueOf(SBloodGroup.getTextDirection());
//    //        String RGgenderStr = RGgender.getText().toString().trim();
//
//            // Insert data into database
//            ContentValues values = new ContentValues();
//            values.put("DName", DNameStr);
//            values.put("DEmail", DEmailStr);
//            values.put("DPhone", DPhoneStr);
//            values.put("DAddress", DAddressStr);
//            values.put("DCity", DCityStr);
//            values.put("DPincode", DPincodeStr);
//    //        values.put("SBloodGroup",SBloodGroupStr);
//    //        values.put("RGgender", RGgenderStr);
//
//
//            long newRowId = sqLiteDatabase.insert("donor", null, values);
//
//            if (newRowId != 0) {
//                Toast.makeText(this, "Donor Details Save!", Toast.LENGTH_SHORT).show();
//                finish(); // Optionally finish this activity
//            } else {
//                Toast.makeText(this, "Not Save!", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }






//  ======================== not save========= above code is working^=======

//package com.yashraj.bloodcamp;
//
//import android.annotation.SuppressLint;
//import android.content.ContentValues;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.RadioGroup;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AddDonor extends AppCompatActivity {
//
//    private EditText DName, DEmail, DPhone, DAddress, DCity, DPincode;
//    private Button btnAddDonor;
////    private RadioGroup RGgender; // Add RadioGroup
//    private Spinner SBloodGroup;
//    private SQLiteDatabase sqLiteDatabase;
//    private DatabaseHelper dbHelper;
//
//    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_donor);
//
//        DName = findViewById(R.id.DName);
//        DEmail = findViewById(R.id.DEmail);
//        DPhone = findViewById(R.id.DPhone);
//        DAddress = findViewById(R.id.DAddress);
//        DCity = findViewById(R.id.DCity);
//        DPincode = findViewById(R.id.DPincode);
//        btnAddDonor = findViewById(R.id.btnAddDonor);
////        SBloodGroup = findViewById(R.id.SBloodGroup); // Uncomment this line
////        RGgender = findViewById(R.id.RGgender); // Initialize RadioGroup
//
//        dbHelper = new DatabaseHelper(this);
//        sqLiteDatabase = dbHelper.getWritableDatabase();
//
//        // Create a list of blood group options
//        List<String> bloodGroups = new ArrayList<>();
//        bloodGroups.add("Select your Blood Group");
//        bloodGroups.add("A+");
//        bloodGroups.add("A-");
//        bloodGroups.add("B+");
//        bloodGroups.add("B-");
//        bloodGroups.add("AB+");
//        bloodGroups.add("AB-");
//        bloodGroups.add("O+");
//        bloodGroups.add("O-");
//
//        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_spinner_item,
//                bloodGroups);
//
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // Apply the adapter to the spinner
//        SBloodGroup.setAdapter(adapter);
//
//        btnAddDonor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                donor(); // Call donor method to validate fields and add donor details
//            }
//        });
//    }
//
//    private void donor() {
//        String DNameStr = DName.getText().toString().trim();
//        String DEmailStr = DEmail.getText().toString().trim();
//        String DPhoneStr = DPhone.getText().toString().trim();
//        String DAddressStr = DAddress.getText().toString().trim();
//        String DCityStr = DCity.getText().toString().trim();
//        String DPincodeStr = DPincode.getText().toString().trim();
////        String SBloodGroupStr = SBloodGroup.getSelectedItem().toString(); // Get selected item from spinner
////        String gender = getSelectedGender(); // Get selected gender from RadioGroup
//
//
//        // Insert data into database
//        ContentValues values = new ContentValues();
//        values.put("DName", DNameStr);
//        values.put("DEmail", DEmailStr);
//        values.put("DPhone", DPhoneStr);
//        values.put("DAddress", DAddressStr);
//        values.put("DCity", DCityStr);
//        values.put("DPincode", DPincodeStr);
////        values.put("SBloodGroup", SBloodGroupStr); // Add spinner value to ContentValues
////        values.put("Gender", gender); // Add gender to ContentValues
//
//        long newRowId = sqLiteDatabase.insert("donor", null, values);
//
//        if (newRowId != -1) {
//            Toast.makeText(this, "Donor Details Saved!", Toast.LENGTH_SHORT).show();
//            finish(); // Optionally finish this activity
//        } else {
//            Toast.makeText(this, "Not Saved!", Toast.LENGTH_SHORT).show();
//        }
//    }
////    private String getSelectedGender() {
////        int selectedId = RGgender.getCheckedRadioButtonId();
////        if (selectedId == R.id.radioBtnMale) {
////            return "Male";
////        } else if (selectedId == R.id.radioBtnFemale) {
////            return "Female";
////        } else if (selectedId == R.id.radioBtnOther) {
////            return "Other";
////        } else {
////            return ""; // Return default value or handle the case when no option is selected
////        }
////    }
//}
//


















    //package com.yashraj.bloodcamp;
    //
    //import android.content.ContentValues;
    //import android.database.sqlite.SQLiteDatabase;
    //import android.os.Bundle;
    //import android.view.View;
    //import android.widget.ArrayAdapter;
    //import android.widget.Button;
    //import android.widget.EditText;
    //import android.widget.Spinner;
    //import android.widget.Toast;
    //
    //import androidx.activity.EdgeToEdge;
    //import androidx.appcompat.app.AppCompatActivity;
    //import androidx.core.graphics.Insets;
    //import androidx.core.view.ViewCompat;
    //import androidx.core.view.WindowInsetsCompat;
    //
    //import java.util.ArrayList;
    //import java.util.List;
    //
    //public class AddDonor extends AppCompatActivity {
    //
    //    private Spinner spinnerBloodGroup;
    //    private EditText editTextName, editTextEmail, editTextPhone, editTextAddress, editTextCity, editTextPincode;
    //    private Button btnAddDonor;
    //    SQLiteDatabase sqLiteDatabase;
    //    DatabaseHelper dbHelper;
    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_add_donor);
    //
    //        editTextName = findViewById(R.id.editTextName);
    //        editTextEmail = findViewById(R.id.editTextEmail);
    //        editTextPhone = findViewById(R.id.editTextPhone);
    //        editTextAddress = findViewById(R.id.editTextAddress);
    //        editTextCity = findViewById(R.id.editTextCity);
    //        editTextPincode = findViewById(R.id.editTextPincode);
    //        btnAddDonor = findViewById(R.id.btnAddDonor);
    //
    //        spinnerBloodGroup = findViewById(R.id.spinnerBloodGroup);
    //        dbHelper = new DatabaseHelper(this);
    //        sqLiteDatabase = dbHelper.getWritableDatabase();
    //
    //        // Create a list of blood group options
    //        List<String> bloodGroups = new ArrayList<>();
    //        bloodGroups.add("Select your Blood Group");
    //        bloodGroups.add("A+");
    //        bloodGroups.add("A-");
    //        bloodGroups.add("B+");
    //        bloodGroups.add("B-");
    //        bloodGroups.add("AB+");
    //        bloodGroups.add("AB-");
    //        bloodGroups.add("O+");
    //        bloodGroups.add("O-");
    //
    //        // Create an ArrayAdapter using the string array and a default spinner layout
    //        ArrayAdapter<String> adapter = new ArrayAdapter<>(
    //                this,
    //                android.R.layout.simple_spinner_item,
    //                bloodGroups);
    //
    //        // Specify the layout to use when the list of choices appears
    //        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    //
    //        // Apply the adapter to the spinner
    //        spinnerBloodGroup.setAdapter(adapter);
    //
    //        btnAddDonor.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                donor(); // Call registerUser method to validate fields and proceed to login if successful
    //            }
    //        });
    //
    //    }
    //    private void donor(){
    //        String editTextNameStr = editTextName.getText().toString().trim();
    //        String editTextEmailStr = editTextEmail.getText().toString().trim();
    //        String editTextPhoneStr = editTextPhone.getText().toString().trim();
    //        String editTextAddressStr = editTextAddress.getText().toString().trim();
    //        String editTextCityStr = editTextCity.getText().toString().trim();
    //        String editTextPincodeStr = editTextPincode.getText().toString().trim();
    //
    //        // Insert data into database
    //        ContentValues values = new ContentValues();
    //        values.put("editTextName", editTextNameStr);
    //        values.put("editTextEmail", editTextEmailStr);
    //        values.put("editTextPhone", editTextPhoneStr);
    //        values.put("editTextAddress", editTextAddressStr);
    //        values.put("editTextCity", editTextCityStr);
    //        values.put("editTextPincode", editTextPincodeStr);
    //
    //
    //        long newRowId = sqLiteDatabase.insert("donor", null, values);
    //
    //        if (newRowId != 0) {
    //            Toast.makeText(this, "Donor Details Save", Toast.LENGTH_SHORT).show();
    //
    //            finish(); // Optionally finish this activity
    //        } else {
    //            Toast.makeText(this, "Not Save", Toast.LENGTH_SHORT).show();
    //        }
    //    }
    //}