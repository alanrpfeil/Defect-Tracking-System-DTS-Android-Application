package com.example.ejgallodts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.type.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.random;

public class SubmissionActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Uri submittedImage;
    Button submitButton;
    ProgressBar loadingSubmission;
    EditText defectName;
    EditText defectImpact;
    EditText defectDescription;
    RadioButton defectIncidentClassFALSE;
    RadioButton defectIncidentClassTRUE;
    EditText defectItemNum;
    EditText defectLotNum;
    EditText defectMaterialGrp;
    EditText defectOrderKey;
    EditText defectPONum;
    EditText defectPrimaryLoc;
    RadioButton defectPriority1;
    RadioButton defectPriority2;
    RadioButton defectPriority3;
    EditText defectProdSuggCause;
    EditText defectRecommend;
    EditText defectSecondaryLoc;
    EditText defectSite;
    EditText defectSubDepartment;
    EditText defectSuppSuggCause;
    EditText defectSupplier;
    EditText defectDepartment;
    CalendarView defectDate;
    ImageButton submitPhoto;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            submittedImage = selectedImage;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        submitButton = findViewById(R.id.submitbutton);
        loadingSubmission = findViewById(R.id.loadsubmit);
        defectName = findViewById(R.id.defectName);
        defectImpact = findViewById(R.id.defectImpact);
        defectDescription = findViewById(R.id.defectDescription);
        defectIncidentClassTRUE = findViewById(R.id.class_yes);
        defectIncidentClassFALSE = findViewById(R.id.class_no);
        defectItemNum = findViewById(R.id.defectItemNum);
        defectLotNum = findViewById(R.id.defectLotNum);
        defectMaterialGrp = findViewById(R.id.defectMaterialGrp);
        defectOrderKey = findViewById(R.id.defectOrderKey);
        defectPONum = findViewById(R.id.defectPONum);
        defectPrimaryLoc = findViewById(R.id.defectPrimaryLoc);
        defectProdSuggCause = findViewById(R.id.defectProdSuggCause);
        defectPriority1 = findViewById(R.id.priority1);
        defectPriority2 = findViewById(R.id.priority2);
        defectPriority3 = findViewById(R.id.priority3);
        defectRecommend = findViewById(R.id.defectRecommend);
        defectSecondaryLoc = findViewById(R.id.defectSecondaryLoc);
        defectSite = findViewById(R.id.defectSite);
        defectSubDepartment = findViewById(R.id.defectSubDepartment);
        defectSuppSuggCause = findViewById(R.id.defectSuppSuggCause);
        defectSupplier = findViewById(R.id.defectSupplier);
        defectDepartment = findViewById(R.id.defectDepartment);
        defectDate = findViewById(R.id.calendarView);
        submitPhoto = findViewById(R.id.imageButton);

        loadingSubmission.setVisibility(View.INVISIBLE);

        submitPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uploadImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(uploadImage, RESULT_LOAD_IMAGE);
            }
        });



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadingSubmission.setVisibility(View.VISIBLE);
                boolean incident_class = false;         //defect case
                long priority = 3;                      //default case

                if (defectName.getText().toString().isEmpty() || defectName.length() > 30) {     //name is longer than button length
                    Toast.makeText(SubmissionActivity.this, "Name Exceeds Character Limit", Toast.LENGTH_SHORT).show();
                } else {

                    long defectDateEnter = defectDate.getDate();

                    if (defectPriority1.isChecked()) {
                        priority = 1;
                    } else if (defectPriority2.isChecked()) {
                        priority = 2;
                    } else {
                        priority = 3;
                    }

                    if (defectIncidentClassTRUE.isChecked()) {
                        incident_class = true;
                    } else {
                        incident_class = false;
                    }

                    boolean overdue;
                    if (defectDateEnter < 0) {
                        overdue = true;
                    } else {
                        overdue = false;
                    }
                    long id = new Random().nextLong();

                    CollectionReference IncidentsRef = db.collection("Incidents");
                    Map<String, Object> newDts = new HashMap<>();
                    newDts.put("defect_impact", defectImpact.getText().toString());
                    newDts.put("defect_name", defectName.getText().toString());
                    newDts.put("department", defectDepartment.getText().toString());
                    newDts.put("description", defectDescription.getText().toString());
                    newDts.put("incident_class", incident_class);
                    newDts.put("incident_date", defectDateEnter);
                    newDts.put("item_num", defectItemNum.getText().toString());
                    newDts.put("lot_num", Long.parseLong(defectLotNum.getText().toString()));
                    newDts.put("material_group", defectMaterialGrp.getText().toString());
                    newDts.put("orderkey", Long.parseLong(defectOrderKey.getText().toString()));
                    newDts.put("overdue", overdue);
                    newDts.put("id", id);
                    newDts.put("po_num", defectPONum.getText().toString());
                    newDts.put("primary_location", defectPrimaryLoc.getText().toString());
                    newDts.put("priority", priority);
                    newDts.put("prod_suggested_cause", defectProdSuggCause.getText().toString());
                    newDts.put("secondary_location", defectSecondaryLoc.getText().toString());
                    newDts.put("site", defectSite.getText().toString());
                    newDts.put("subdepartment", defectSubDepartment.getText().toString());
                    newDts.put("supp_suggested_cause", defectSuppSuggCause.getText().toString());
                    newDts.put("supplier", defectSupplier.getText().toString());
                    newDts.put("url", submittedImage.toString());
                    IncidentsRef.add(newDts);   //sends DTS to Database

                    loadingSubmission.setVisibility(View.INVISIBLE);

                    Intent i = new Intent(SubmissionActivity.this, HomeActivity.class);
                    startActivity(i);
                }
            }
        });



    }
}
