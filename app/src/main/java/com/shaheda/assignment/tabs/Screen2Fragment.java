package com.shaheda.assignment.tabs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaheda.assignment.MainActivity;
import com.shaheda.assignment.R;
import com.shaheda.assignment.tabs.adapters.ImageListAdapter;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Screen2Fragment extends TabFragment {

    private ImageListAdapter imageListAdapter;
    private WeakReference<MainActivity> mainActivityWeakReference;

    @Override
    public String getTitle() {
        return "Screen2";
    }

    public static Screen2Fragment newInstance() {
        return new Screen2Fragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivityWeakReference = new WeakReference<>((MainActivity) context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_screen2, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        imageListAdapter = new ImageListAdapter(mainActivityWeakReference.get().getImagesList());
        recyclerView.setAdapter(imageListAdapter);
    }

    public void refresh() {
        imageListAdapter.setImageUrls(mainActivityWeakReference.get().getImagesList());
    }
}
