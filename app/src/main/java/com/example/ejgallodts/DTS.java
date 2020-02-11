package com.example.ejgallodts;

import android.net.Uri;

import com.google.type.Date;

public class DTS {

    String defect_impact;
    String defect_name;
    String department;
    String description;
    long id;
    boolean incident_class;
    long incident_date;
    String item_num;
    long lot_num;
    String material_group;
    long orderkey;
    boolean overdue;
    String po_num;
    String primary_location;
    long priority;
    String prod_suggested_cause;
    String recommendation;
    String secondary_location;
    String site;
    String subdepartment;
    String supp_suggested_cause;
    String supplier;
    Uri imageUri;

public void DTS(){
        defect_impact = "N/A";
        defect_name = "N/A";
        department = "N/A";
        description = "N/A";
        id = -1;
        incident_class = false;
        incident_date = -1;
        item_num = "N/A";
        lot_num = -1;
        material_group = "N/A";
        orderkey = -1;
        overdue = false;
        po_num = "N/A";
        primary_location = "N/A";
        priority = 4;
        prod_suggested_cause = "N/A";
        recommendation = "N/A";
        secondary_location = "N/A";
        site = "N/A";
        subdepartment = "N/A";
        supp_suggested_cause = "N/A";
        supplier = "N/A";
        imageUri = null;
    }

    public void DTS(String def_im, String def_name, String dep, String desc, long ID, boolean inc_class, long inc_date, String itemNum, long lotNum, String mat_group, long orderKey, boolean overDue, String ponum, String prim_loc, long priornum, String prod_sugg, String rec, String sec_loc, String Site, String subdept, String supp_sugg, String supp, Uri imageUri){
        defect_impact = def_im;
        defect_name = def_name;
        department = dep;
        description = desc;
        id = ID;
        incident_class = inc_class;
        incident_date = inc_date;
        item_num = itemNum;
        lot_num = lotNum;
        material_group = mat_group;
        orderkey = orderKey;
        overdue = overDue;
        po_num = ponum;
        primary_location = prim_loc;
        priority = priornum;
        prod_suggested_cause = prod_sugg;
        recommendation = rec;
        secondary_location = sec_loc;
        site = Site;
        subdepartment = subdept;
        supp_suggested_cause = supp_sugg;
        supplier = supp;
        this.imageUri = imageUri;
    }

    // Setters ////////////////////////////////////////////////////////////////////////////////////

    public void setDefect_impact(String def_im) {
        defect_impact = def_im;
    }

    public void setDefect_name(String def_name) {
        defect_name = def_name;
    }

    public void setDepartment(String dept) {
        department = dept;
    }

    public void setDescription(String desc) {
        description = desc;
    }

    public void setId(long ID) {
        id = ID;
    }

    public void setIncident_class(boolean inc_class) {
        incident_class = inc_class;
    }

    public void setIncident_date(long inc_date) {
        incident_date = inc_date;
    }

    public void setItem_num(String itemNum) {
        item_num = itemNum;
    }

    public void setLot_num(long lotNum) {
        lot_num = lotNum;
    }

    public void setMaterial_group(String mat_group) {
        material_group = mat_group;
    }

    public void setOrderkey(long orderKey) {
        orderkey = orderKey;
    }

    public void setOverdue(boolean overDue) {
        overdue = overDue;
    }

    public void setPo_num(String ponum) {
        po_num = ponum;
    }

    public void setPrimary_location(String prim_loc) {
        primary_location = prim_loc;
    }

    public void setPriority(long prior) {
        priority = prior;
    }

    public void setProd_suggested_cause(String prod_sugg) {
        prod_suggested_cause = prod_sugg;
    }

    public void setRecommendation(String rec) {
        recommendation = rec;
    }

    public void setSecondary_location(String sec_loc) {
        secondary_location = sec_loc;
    }

    public void setSite(String Site) {
        site = Site;
    }

    public void setSubdepartment(String subDept) {
        subdepartment = subDept;
    }

    public void setSupp_suggested_cause(String prod_sugg) {
        prod_suggested_cause = prod_sugg;
    }

    public void setSupplier(String supp) {
        supplier = supp;
    }

    public void setImageUri(Uri imageUri) {this.imageUri = imageUri;}

    // Getters ////////////////////////////////////////////////////////////////////////////////////

    public String getDefect_impact() {
        return defect_impact;
    }

    public String getDefect_name() {
        return defect_name;
    }

    public String getDepartment() {
        return department;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public boolean getIncident_class() {
        return incident_class;
    }

    public long getIncident_date() {
        return incident_date;
    }

    public String getItem_num() {
        return item_num;
    }

    public long getLot_num() {
        return lot_num;
    }

    public String getMaterial_group() {
        return material_group;
    }

    public long getOrderkey() {
        return orderkey;
    }

    public boolean getOverdue() {
        return overdue;
    }

    public String getPo_num() {
        return po_num;
    }

    public String getPrimary_location() {
        return primary_location;
    }

    public long getPriority() {
        return priority;
    }

    public String getProd_suggested_cause() {
        return prod_suggested_cause;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public String getSecondary_location() {
     return secondary_location;
    }

    public String getSite() {
        return site;
    }

    public String getSubdepartment() {
        return subdepartment;
    }

    public String getSupp_suggested_cause() {
        return supp_suggested_cause;
    }

    public String getSupplier() {
        return supplier;
    }

    public Uri getImageUri() { return imageUri; }

}
