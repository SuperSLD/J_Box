package com.jutter.j_box.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.jutter.j_box.Classes.Box;
import com.jutter.j_box.Classes.URLSendRequest;
import com.jutter.j_box.MainActivity;
import com.jutter.j_box.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CarDialogFragment extends DialogFragment {

    private Context context;
    private Box box;

    public CarDialogFragment(Context context) {
        this.context = context;
        this.box = box;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.car_dialog, null);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText startAdreas = view.findViewById(R.id.start);
        EditText location = view.findViewById(R.id.location);
        EditText code = view.findViewById(R.id.code);
        EditText date = view.findViewById(R.id.date);

        Button reserve = view.findViewById(R.id.reserve);

        reserve.setOnClickListener(v -> {

            URLSendRequest url = new URLSendRequest(MainActivity.SERVER_IP, 5000);
            String r = url.get("car?act=add&date="+date.getText().toString()+
                    "&location="+startAdreas.getText().toString()+
                    "&userId="+context.getSharedPreferences("appSettings", Context.MODE_PRIVATE).getString("id","")
                    + "&code=" + code.getText().toString()) + "&start="+location.getText().toString();
            dismiss();
        });

        return view;
    }
}
