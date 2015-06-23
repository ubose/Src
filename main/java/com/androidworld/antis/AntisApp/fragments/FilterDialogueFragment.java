package com.androidworld.antis.AntisApp.fragments;

import android.app.AlertDialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.androidworld.antis.AntisApp.R;
import com.androidworld.antis.AntisApp.utilities.ApplicationUtilities;

/**
 * Created by utbose on 6/22/2015.
 */
public class FilterDialogueFragment extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setStyle(DialogFragment.STYLE_NO_FRAME, R.style.filter_dialog_theme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View root = inflater.inflate(R.layout.base_filter_dialogue_layout, container, false);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        //safety check
        if (getDialog() == null) {
            return;
        }

        Window window = getDialog().getWindow();

        if(window == null) {
            return;
        }

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom( getDialog().getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.TOP | Gravity.LEFT);
        WindowManager.LayoutParams params = window.getAttributes();
        params.y = ApplicationUtilities.dpToPixels(100);
        window.setAttributes(params);
        getDialog().setCanceledOnTouchOutside(true);
    }
}
