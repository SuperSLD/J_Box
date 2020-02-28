package com.jutter.j_box.Fragments;

import android.content.Context;
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
import com.jutter.j_box.R;


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
        EditText address = view.findViewById(R.id.address);
        EditText dateEnd = view.findViewById(R.id.dateEnd);
        Button reserve = view.findViewById(R.id.reserve);

        reserve.setOnClickListener(v -> {
            box.setEndDate(dateEnd.getText().toString());
            box.setPlace(address.getText().toString());
        });

        return view;
    }
}
