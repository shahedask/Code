package com.shaheda.assignment.tabs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaheda.assignment.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Screen1Fragment extends TabFragment implements View.OnClickListener {

    private Screen1Callback callbackListener;

    @Override
    public String getTitle() {
        return "Screen1";
    }

    public static Screen1Fragment newInstance() {
        return new Screen1Fragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Screen1Callback) {
            callbackListener = (Screen1Callback) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_screen1, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button1).setOnClickListener(this);
        view.findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (callbackListener != null) {
            switch (v.getId()) {
                case R.id.button1:
                    callbackListener.onClickButton1();
                    break;

                case R.id.button2:
                    callbackListener.onClickButton2();
                    break;
            }
        }
    }

    public interface Screen1Callback {
        void onClickButton1();
        void onClickButton2();
    }
}
