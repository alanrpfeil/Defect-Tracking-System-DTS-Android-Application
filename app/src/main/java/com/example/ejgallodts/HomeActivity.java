package com.example.ejgallodts;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.CaseMap;
import android.nfc.Tag;
import android.os.Bundle;

import com.example.ejgallodts.ui.main.SectionsPagerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    DTS dts1 = new DTS(); DTS dts2 = new DTS(); DTS dts3 = new DTS(); DTS dts4 = new DTS(); DTS dts5 = new DTS(); DTS dts6 = new DTS(); DTS dts7 = new DTS(); DTS dts8 = new DTS(); DTS dts9 = new DTS(); DTS dts10 = new DTS();
    Button DTS_1, DTS_2, DTS_3, DTS_4, DTS_5, DTS_6, DTS_7, DTS_8, DTS_9, DTS_10;
    ImageButton newDTS;
    ScrollView scroll;
    DTS dtsarray[] = new DTS[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        scroll = findViewById(R.id.scrollView);
        newDTS = findViewById(R.id.newDTS);
        TextView title = findViewById(R.id.title);

        Intent i = getIntent();
        boolean isAdmin = i.getBooleanExtra("isAdmin", false);
        if (!isAdmin) {
            title.setText("Defect Tracking System | User");
        }

        final Button buttonArray[] = new Button[10];

        DTS_1 = findViewById(R.id.DTS_1);
        DTS_2 = findViewById(R.id.DTS_2);
        DTS_3 = findViewById(R.id.DTS_3);
        DTS_4 = findViewById(R.id.DTS_4);
        DTS_5 = findViewById(R.id.DTS_5);
        DTS_6 = findViewById(R.id.DTS_6);
        DTS_7 = findViewById(R.id.DTS_7);
        DTS_8 = findViewById(R.id.DTS_8);
        DTS_9 = findViewById(R.id.DTS_9);
        DTS_10 = findViewById(R.id.DTS_10);

        //ten at a time...
        buttonArray[0] = DTS_1;
        buttonArray[1] = DTS_2;
        buttonArray[2] = DTS_3;
        buttonArray[3] = DTS_4;
        buttonArray[4] = DTS_5;
        buttonArray[5] = DTS_6;
        buttonArray[6] = DTS_7;
        buttonArray[7] = DTS_8;
        buttonArray[8] = DTS_9;
        buttonArray[9] = DTS_10;
        dtsarray[0] = dts1;
        dtsarray[1] = dts2;
        dtsarray[2] = dts3;
        dtsarray[3] = dts4;
        dtsarray[4] = dts5;
        dtsarray[5] = dts6;
        dtsarray[6] = dts7;
        dtsarray[7] = dts8;
        dtsarray[8] = dts9;
        dtsarray[9] = dts10;

        for (int j = 0; j < 10; j++) {
            buttonArray[j].setVisibility(View.GONE);
            buttonArray[j].setClickable(false);
        }

        TabItem mainTab = findViewById(R.id.mainTab);
        TabItem priorityTab1 = findViewById(R.id.priorityTab1);
        TabItem priorityTab2 = findViewById(R.id.priorityTab2);
        TabItem priorityTab3 = findViewById(R.id.priorityTab3);
        TabItem overdueTab = findViewById(R.id.overdueTab);


    newDTS.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(HomeActivity.this, SubmissionActivity.class);
            startActivity(i);
        }
    });

    }

}