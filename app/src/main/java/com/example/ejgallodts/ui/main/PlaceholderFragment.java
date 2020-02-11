package com.example.ejgallodts.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.ejgallodts.DTS;
import com.example.ejgallodts.HomeActivity;
import com.example.ejgallodts.R;
import com.example.ejgallodts.SubmissionActivity;
import com.example.ejgallodts.ViewingActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * A fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    DTS dts1 = new DTS(); DTS dts2 = new DTS(); DTS dts3 = new DTS(); DTS dts4 = new DTS(); DTS dts5 = new DTS(); DTS dts6 = new DTS(); DTS dts7 = new DTS(); DTS dts8 = new DTS(); DTS dts9 = new DTS(); DTS dts10 = new DTS();
    Button DTS_1, DTS_2, DTS_3, DTS_4, DTS_5, DTS_6, DTS_7, DTS_8, DTS_9, DTS_10;
    ImageButton newDTS;
    ScrollView scroll;
    DTS dtsarray[] = new DTS[10];
    private PageViewModel pageViewModel;
    boolean isAdmin;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.section_label);
        super.onCreate(savedInstanceState);
        scroll = root.findViewById(R.id.scrollView);
        final ProgressBar loadDTS = root.findViewById(R.id.loadingDTS);
        newDTS = root.findViewById(R.id.newDTS);

        final Button buttonArray[] = new Button[10];

        Intent temp = getActivity().getIntent();
        isAdmin = temp.getBooleanExtra("isAdmin", false);

        DTS_1 = root.findViewById(R.id.DTS_1);
        DTS_2 = root.findViewById(R.id.DTS_2);
        DTS_3 = root.findViewById(R.id.DTS_3);
        DTS_4 = root.findViewById(R.id.DTS_4);
        DTS_5 = root.findViewById(R.id.DTS_5);
        DTS_6 = root.findViewById(R.id.DTS_6);
        DTS_7 = root.findViewById(R.id.DTS_7);
        DTS_8 = root.findViewById(R.id.DTS_8);
        DTS_9 = root.findViewById(R.id.DTS_9);
        DTS_10 = root.findViewById(R.id.DTS_10);
        // DTS_6 = findViewById(R.id.DTS_6);
        // DTS_7 = findViewById(R.id.DTS_7);
        // DTS_8 = findViewById(R.id.DTS_8);
        // DTS_9 = findViewById(R.id.DTS_9);
        // DTS_10 = findViewById(R.id.DTS_10);

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
        for (int i = 0; i < 10; i++) {
            buttonArray[i].setVisibility(View.GONE);
        }
        loadDTS.setVisibility(View.VISIBLE);


        if (pageViewModel.getIndex() == 1) {    //Main Tab///////////////////////////////////////////////

            db.collection("Incidents")
                    .orderBy("incident_date")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                int i = 0;
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    if (i == 10) {
                                        break;
                                    }
                                    buttonArray[i].setClickable(true);
                                    dtsarray[i].setDefect_name(document.getString("defect_name"));
                                    dtsarray[i].setPriority((long) document.get("priority"));
                                    dtsarray[i].setId(document.getLong("id"));
                                    dtsarray[i].setIncident_date(document.getLong("incident_date"));
                                    dtsarray[i].setDefect_impact(document.getString("defect_impact"));
                                    dtsarray[i].setDepartment(document.getString("department"));
                                    dtsarray[i].setDescription(document.getString("description"));
                                    dtsarray[i].setIncident_class(document.getBoolean("incident_class"));
                                    dtsarray[i].setItem_num(document.getString("item_num"));
                                    dtsarray[i].setPo_num(document.getString("po_num"));
                                    dtsarray[i].setMaterial_group(document.getString("material_group"));
                                    dtsarray[i].setLot_num(document.getLong("lot_num"));
                                    dtsarray[i].setOrderkey(document.getLong("orderkey"));
                                    dtsarray[i].setProd_suggested_cause(document.getString("prod_suggested_casue"));
                                    dtsarray[i].setSupp_suggested_cause(document.getString("supp_suggested_cause"));
                                    dtsarray[i].setPrimary_location(document.getString("primary_location"));
                                    dtsarray[i].setSecondary_location(document.getString("secondary_location"));
                                    dtsarray[i].setSite(document.getString("site"));
                                    dtsarray[i].setSupplier(document.getString("supplier"));
                                    dtsarray[i].setSubdepartment(document.getString("subdepartment"));

                                    String dts1text = "Name: " + dtsarray[i].getDefect_name() + " | Priority: " + dtsarray[i].getPriority();
                                    buttonArray[i].setText(dts1text);
                                    buttonArray[i].setVisibility(View.VISIBLE);
                                    if (dtsarray[i].getPriority() == 1) {
                                        buttonArray[i].setBackgroundColor(0xC8C44848);
                                    } else if (dtsarray[i].getPriority() == 2) {
                                        buttonArray[i].setBackgroundColor(0xC8D86E26);
                                    } else if (dtsarray[i].getPriority() == 3) {
                                        buttonArray[i].setBackgroundColor(0xC8DAC439);
                                    }
                                    i++;

                                }
                                loadDTS.setVisibility(View.GONE);
                            } else {
                                Log.d("TAG", "Error getting documents: ", task.getException());
                            }
                        }
                    });
        } else if (pageViewModel.getIndex() == 2) { //Priority 1 Tab///////////////////////////////////////////////
            db.collection("Incidents")
                    .whereEqualTo("priority", 1)
                    .orderBy("incident_date")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                int i = 0;
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    if (i == 10) {
                                        break;
                                    }
                                    buttonArray[i].setClickable(true);
                                    dtsarray[i].setDefect_name(document.getString("defect_name"));
                                    dtsarray[i].setPriority((long) document.get("priority"));
                                    dtsarray[i].setId(document.getLong("id"));
                                    dtsarray[i].setIncident_date(document.getLong("incident_date"));
                                    dtsarray[i].setDefect_impact(document.getString("defect_impact"));
                                    dtsarray[i].setDepartment(document.getString("department"));
                                    dtsarray[i].setDescription(document.getString("description"));
                                    dtsarray[i].setIncident_class(document.getBoolean("incident_class"));
                                    dtsarray[i].setItem_num(document.getString("item_num"));
                                    dtsarray[i].setPo_num(document.getString("po_num"));
                                    dtsarray[i].setMaterial_group(document.getString("material_group"));
                                    dtsarray[i].setLot_num(document.getLong("lot_num"));
                                    dtsarray[i].setOrderkey(document.getLong("orderkey"));
                                    dtsarray[i].setProd_suggested_cause(document.getString("prod_suggested_casue"));
                                    dtsarray[i].setSupp_suggested_cause(document.getString("supp_suggested_cause"));
                                    dtsarray[i].setPrimary_location(document.getString("primary_location"));
                                    dtsarray[i].setSecondary_location(document.getString("secondary_location"));
                                    dtsarray[i].setSite(document.getString("site"));
                                    dtsarray[i].setSupplier(document.getString("supplier"));
                                    dtsarray[i].setSubdepartment(document.getString("subdepartment"));
                                    String dts1text = "Name: " + dtsarray[i].getDefect_name() + " | Priority: " + dtsarray[i].getPriority();
                                    buttonArray[i].setText(dts1text);
                                    buttonArray[i].setVisibility(View.VISIBLE);
                                    if (dtsarray[i].getPriority() == 1) {
                                        buttonArray[i].setBackgroundColor(0xC8C44848);
                                    } else if (dtsarray[i].getPriority() == 2) {
                                        buttonArray[i].setBackgroundColor(0xC8D86E26);
                                    } else if (dtsarray[i].getPriority() == 3) {
                                        buttonArray[i].setBackgroundColor(0xC8DAC439);
                                    }
                                    i++;

                                }
                                loadDTS.setVisibility(View.GONE);
                            } else {
                                Log.d("TAG", "Error getting documents: ", task.getException());
                            }
                        }
                    });
        } else if (pageViewModel.getIndex() == 3) { //Priority 2 Tab///////////////////////////////////////////////
            db.collection("Incidents")
                    .whereEqualTo("priority", 2)
                    .orderBy("incident_date")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                int i = 0;
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    if (i == 10) {
                                        break;
                                    }
                                    buttonArray[i].setClickable(true);
                                    dtsarray[i].setDefect_name(document.getString("defect_name"));
                                    dtsarray[i].setPriority((long) document.get("priority"));
                                    dtsarray[i].setId(document.getLong("id"));
                                    dtsarray[i].setIncident_date(document.getLong("incident_date"));
                                    dtsarray[i].setDefect_impact(document.getString("defect_impact"));
                                    dtsarray[i].setDepartment(document.getString("department"));
                                    dtsarray[i].setDescription(document.getString("description"));
                                    dtsarray[i].setIncident_class(document.getBoolean("incident_class"));
                                    dtsarray[i].setItem_num(document.getString("item_num"));
                                    dtsarray[i].setPo_num(document.getString("po_num"));
                                    dtsarray[i].setMaterial_group(document.getString("material_group"));
                                    dtsarray[i].setLot_num(document.getLong("lot_num"));
                                    dtsarray[i].setOrderkey(document.getLong("orderkey"));
                                    dtsarray[i].setProd_suggested_cause(document.getString("prod_suggested_casue"));
                                    dtsarray[i].setSupp_suggested_cause(document.getString("supp_suggested_cause"));
                                    dtsarray[i].setPrimary_location(document.getString("primary_location"));
                                    dtsarray[i].setSecondary_location(document.getString("secondary_location"));
                                    dtsarray[i].setSite(document.getString("site"));
                                    dtsarray[i].setSupplier(document.getString("supplier"));
                                    dtsarray[i].setSubdepartment(document.getString("subdepartment"));
                                    String dts1text = "Name: " + dtsarray[i].getDefect_name() + " | Priority: " + dtsarray[i].getPriority();
                                    buttonArray[i].setText(dts1text);
                                    buttonArray[i].setVisibility(View.VISIBLE);
                                    if (dtsarray[i].getPriority() == 1) {
                                        buttonArray[i].setBackgroundColor(0xC8C44848);
                                    } else if (dtsarray[i].getPriority() == 2) {
                                        buttonArray[i].setBackgroundColor(0xC8D86E26);
                                    } else if (dtsarray[i].getPriority() == 3) {
                                        buttonArray[i].setBackgroundColor(0xC8DAC439);
                                    }
                                    i++;

                                }
                                loadDTS.setVisibility(View.GONE);
                            } else {
                                Log.d("TAG", "Error getting documents: ", task.getException());
                            }
                        }
                    });
        } else if (pageViewModel.getIndex() == 4) { //Priority 3 Tab///////////////////////////////////////////////
            db.collection("Incidents")
                    .whereEqualTo("priority", 3)
                    .orderBy("incident_date")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                int i = 0;
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    if (i == 10) {
                                        break;
                                    }
                                    buttonArray[i].setClickable(true);
                                    dtsarray[i].setDefect_name(document.getString("defect_name"));
                                    dtsarray[i].setPriority((long) document.get("priority"));
                                    dtsarray[i].setId(document.getLong("id"));
                                    dtsarray[i].setIncident_date(document.getLong("incident_date"));
                                    dtsarray[i].setDefect_impact(document.getString("defect_impact"));
                                    dtsarray[i].setDepartment(document.getString("department"));
                                    dtsarray[i].setDescription(document.getString("description"));
                                    dtsarray[i].setIncident_class(document.getBoolean("incident_class"));
                                    dtsarray[i].setItem_num(document.getString("item_num"));
                                    dtsarray[i].setPo_num(document.getString("po_num"));
                                    dtsarray[i].setMaterial_group(document.getString("material_group"));
                                    dtsarray[i].setLot_num(document.getLong("lot_num"));
                                    dtsarray[i].setOrderkey(document.getLong("orderkey"));
                                    dtsarray[i].setProd_suggested_cause(document.getString("prod_suggested_casue"));
                                    dtsarray[i].setSupp_suggested_cause(document.getString("supp_suggested_cause"));
                                    dtsarray[i].setPrimary_location(document.getString("primary_location"));
                                    dtsarray[i].setSecondary_location(document.getString("secondary_location"));
                                    dtsarray[i].setSite(document.getString("site"));
                                    dtsarray[i].setSupplier(document.getString("supplier"));
                                    dtsarray[i].setSubdepartment(document.getString("subdepartment"));
                                    String dts1text = "Name: " + dtsarray[i].getDefect_name() + " | Priority: " + dtsarray[i].getPriority();
                                    buttonArray[i].setText(dts1text);
                                    buttonArray[i].setVisibility(View.VISIBLE);
                                    if (dtsarray[i].getPriority() == 1) {
                                        buttonArray[i].setBackgroundColor(0xC8C44848);
                                    } else if (dtsarray[i].getPriority() == 2) {
                                        buttonArray[i].setBackgroundColor(0xC8D86E26);
                                    } else if (dtsarray[i].getPriority() == 3) {
                                        buttonArray[i].setBackgroundColor(0xC8DAC439);
                                    }
                                    i++;

                                }
                                loadDTS.setVisibility(View.GONE);
                            } else {
                                Log.d("TAG", "Error getting documents: ", task.getException());
                            }
                        }
                    });
        } else if (pageViewModel.getIndex() == 5) { //Overdue Tab///////////////////////////////////////////////
            db.collection("Incidents")
                    .whereEqualTo("overdue", true)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                int i = 0;
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    if (i == 10) {
                                        break;
                                    }
                                    buttonArray[i].setClickable(true);
                                    dtsarray[i].setDefect_name(document.getString("defect_name"));
                                    dtsarray[i].setPriority((long) document.get("priority"));
                                    dtsarray[i].setId(document.getLong("id"));
                                    dtsarray[i].setIncident_date(document.getLong("incident_date"));
                                    dtsarray[i].setDefect_impact(document.getString("defect_impact"));
                                    dtsarray[i].setDepartment(document.getString("department"));
                                    dtsarray[i].setDescription(document.getString("description"));
                                    dtsarray[i].setIncident_class(document.getBoolean("incident_class"));
                                    dtsarray[i].setItem_num(document.getString("item_num"));
                                    dtsarray[i].setPo_num(document.getString("po_num"));
                                    dtsarray[i].setMaterial_group(document.getString("material_group"));
                                    dtsarray[i].setLot_num(document.getLong("lot_num"));
                                    dtsarray[i].setOrderkey(document.getLong("orderkey"));
                                    dtsarray[i].setProd_suggested_cause(document.getString("prod_suggested_casue"));
                                    dtsarray[i].setSupp_suggested_cause(document.getString("supp_suggested_cause"));
                                    dtsarray[i].setPrimary_location(document.getString("primary_location"));
                                    dtsarray[i].setSecondary_location(document.getString("secondary_location"));
                                    dtsarray[i].setSite(document.getString("site"));
                                    dtsarray[i].setSupplier(document.getString("supplier"));
                                    dtsarray[i].setSubdepartment(document.getString("subdepartment"));
                                    String dts1text = "Name: " + dtsarray[i].getDefect_name() + " | Priority: " + dtsarray[i].getPriority();
                                    buttonArray[i].setText(dts1text);
                                    buttonArray[i].setVisibility(View.VISIBLE);
                                    if (dtsarray[i].getPriority() == 1) {
                                        buttonArray[i].setBackgroundColor(0xC8C44848);
                                    } else if (dtsarray[i].getPriority() == 2) {
                                        buttonArray[i].setBackgroundColor(0xC8D86E26);
                                    } else if (dtsarray[i].getPriority() == 3) {
                                        buttonArray[i].setBackgroundColor(0xC8DAC439);
                                    }
                                    i++;

                                }
                                loadDTS.setVisibility(View.GONE);
                            } else {
                                Log.d("TAG", "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }

        buttonArray[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id;
                id = dtsarray[0].getId();
                Intent intent = new Intent(getActivity(), ViewingActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("isAdmin", isAdmin);
                startActivity(intent);
            }
        });

        buttonArray[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id;
                id = dtsarray[1].getId();
                Intent intent = new Intent(getActivity(), ViewingActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("isAdmin", isAdmin);
                startActivity(intent);
            }
        });

        buttonArray[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id;
                id = dtsarray[2].getId();
                Intent intent = new Intent(getActivity(), ViewingActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("isAdmin", isAdmin);
                startActivity(intent);
            }
        });

        buttonArray[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id;
                id = dtsarray[3].getId();
                Intent intent = new Intent(getActivity(), ViewingActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("isAdmin", isAdmin);
                startActivity(intent);
            }
        });

        buttonArray[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id;
                id = dtsarray[4].getId();
                Intent intent = new Intent(getActivity(), ViewingActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("isAdmin", isAdmin);
                startActivity(intent);
            }
        });

        buttonArray[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id;
                id = dtsarray[5].getId();
                Intent intent = new Intent(getActivity(), ViewingActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("isAdmin", isAdmin);
                startActivity(intent);
            }
        });

        buttonArray[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id;
                id = dtsarray[6].getId();
                Intent intent = new Intent(getActivity(), ViewingActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("isAdmin", isAdmin);
                startActivity(intent);
            }
        });

        buttonArray[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id;
                id = dtsarray[7].getId();
                Intent intent = new Intent(getActivity(), ViewingActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("isAdmin", isAdmin);
                startActivity(intent);
            }
        });

        buttonArray[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id;
                id = dtsarray[8].getId();
                Intent intent = new Intent(getActivity(), ViewingActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("isAdmin", isAdmin);
                startActivity(intent);
            }
        });

        buttonArray[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id;
                id = dtsarray[9].getId();
                Intent intent = new Intent(getActivity(), ViewingActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("isAdmin", isAdmin);
                startActivity(intent);
            }
        });


        return root;
    }
}