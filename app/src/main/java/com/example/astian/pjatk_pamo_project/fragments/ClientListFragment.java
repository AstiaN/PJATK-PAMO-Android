package com.example.astian.pjatk_pamo_project.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.example.astian.pjatk_pamo_project.R;
import com.example.astian.pjatk_pamo_project.data.AppDatabase;
import com.example.astian.pjatk_pamo_project.model.Clients;

public class ClientListFragment extends Fragment {

    private OnNewClientButtonClickListener mNewClientButtonClickListener;
    private OnClientClickListener mOnClientClickListener;
    private OnNavigateButtonClickListener mOnNavigateButtonClickListener;


    public interface OnNewClientButtonClickListener {
        void onNewClientButtonClick();
    }

    public interface OnClientClickListener {
        void onClientClick(Clients clients);
    }

    public interface OnNavigateButtonClickListener {
        void onNavigationButtonClicked(Clients clients);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = null;

        if (context instanceof Activity){
            activity = (Activity) context;
        }

        if (activity instanceof OnNewClientButtonClickListener) {
            mNewClientButtonClickListener = (OnNewClientButtonClickListener) activity;
        }

        if (activity instanceof OnClientClickListener) {
            mOnClientClickListener = (OnClientClickListener) activity;
        }

        if (activity instanceof OnNavigateButtonClickListener) {
            mOnNavigateButtonClickListener = (OnNavigateButtonClickListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_list, container, false);
        Context context = getContext();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.new_client_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNewClientButtonClickListener.onNewClientButtonClick();
            }
        });

        List<Clients> clients = getClientes(context);

        RecyclerView.Adapter adapter = new ClientViewAdapter(clients,
                mOnClientClickListener, mOnNavigateButtonClickListener);

        RecyclerView clientListView = (RecyclerView) view.findViewById(R.id.client_list);

        clientListView.setLayoutManager(new LinearLayoutManager(context));
        clientListView.setAdapter(adapter);

        return view;
    }

    public void refreshList() {
        Context context = getActivity();
        List<Clients> clients = getClientes(context);

        RecyclerView clientListView = (RecyclerView) getView().findViewById(R.id.client_list);
        RecyclerView.Adapter adapter = new ClientViewAdapter(clients,
                mOnClientClickListener, mOnNavigateButtonClickListener);
        clientListView.setAdapter(adapter);
    }

    private List<Clients> getClientes(Context context) {
        return AppDatabase.getInstance(context)
                .getClientDao()
                .getAll();
    }
}
