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


public class ReserveBoxDialogFragment extends DialogFragment {

    private Context context;
    private Box box;

    public ReserveBoxDialogFragment(Context context, Box box) {
        this.context = context;
        this.box = box;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.reserve_box_fragment, null);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText address = view.findViewById(R.id.address);
        EditText dateEnd = view.findViewById(R.id.dateEnd);
        Button reserve = view.findViewById(R.id.reserve);

        reserve.setOnClickListener(v -> {
            box.setEndDate(dateEnd.getText().toString());
            box.setPlace(address.getText().toString());

            URLSendRequest url = new URLSendRequest(MainActivity.SERVER_IP, 5000);
            String r = url.get("updatebox?act=setuser&endDate="+dateEnd.getText().toString()+
                    "&location="+address.getText().toString()+"&startDate="+
                    new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime())
                    +"&userId="+context.getSharedPreferences("appSettings", Context.MODE_PRIVATE).getString("id","")
                    + "&id=" + box.getId());
            dismiss();
        });

        return view;
    }
}
