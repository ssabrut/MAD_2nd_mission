package model;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.warhol.mad_2nd_mission.R;

public class LoadingDialog {

    Activity activity;
    AlertDialog alertDialog;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    public void setAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        builder.setView(layoutInflater.inflate(R.layout.activity_loading_dialog, null));
        alertDialog = builder.create();
        alertDialog.show();
    }

    public void dismissDialog() {
        alertDialog.dismiss();
    }
}
