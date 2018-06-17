package com.example.astian.pjatk_pamo_project.fragments;


import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.astian.pjatk_pamo_project.R;
import com.example.astian.pjatk_pamo_project.model.Clients;
import com.example.astian.pjatk_pamo_project.services.ClientService;

public class ClientEditFragment extends Fragment {
    private static final String ARG_CLIENT = "CLIENT";

    private Clients mClients;
    private EditText mNameEdit;
    private EditText mSurNameEdit;
    private EditText mCountryEdit;
    private EditText mCityEdit;
    private EditText mStreetEdit;
    private EditText mHouseNumberEdit;
    private EditText mApartmentEdit;
    private Button mDeleteButton;

    private OnClientSavedListener mOnClientSavedListener;

    public interface OnClientSavedListener {
        void onClientSaved();
    }

    public ClientEditFragment() {
        // Required empty public constructor
    }

    public static ClientEditFragment newInstance(Clients clients) {

        ClientEditFragment fragment = new ClientEditFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CLIENT, clients);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mClients = (Clients) getArguments().getSerializable(ARG_CLIENT);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = null;

        if (context instanceof Activity){
            activity = (Activity) context;
        }

        if (activity instanceof OnClientSavedListener) {
            mOnClientSavedListener = (OnClientSavedListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_client_edit, container, false);

        mNameEdit = view.findViewById(R.id.client_edit_name);
        mSurNameEdit = view.findViewById(R.id.client_edit_surname);
        mCountryEdit = view.findViewById(R.id.client_edit_country);
        mCityEdit = view.findViewById(R.id.client_edit_city);
        mStreetEdit = view.findViewById(R.id.client_edit_street);
        mHouseNumberEdit = view.findViewById(R.id.client_edit_house_number);
        mApartmentEdit = view.findViewById(R.id.client_edit_apartment);
        mDeleteButton = view.findViewById(R.id.delete_client_btn);

        if (mClients == null) {
            mClients = new Clients();
        }

        setupForm();

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        Button saveBtn = view.findViewById(R.id.save_client_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clients clients = buildClient();
                saveClient(clients);
                mOnClientSavedListener.onClientSaved();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            ClientService.getInstance().delete(mClients, getContext());
            mOnClientSavedListener.onClientSaved();
        }
    }

    public void editClient(Clients clients) {
        mClients = clients;
        setupForm();
    }

    private void setupForm() {
        mNameEdit.setText(mClients.getName());
        mSurNameEdit.setText(mClients.getSurname());
        mCountryEdit.setText(mClients.getCountry());
        mCityEdit.setText(mClients.getCity());
        mStreetEdit.setText(mClients.getStreet());
        mHouseNumberEdit.setText(mClients.getHouseNumber());

        mDeleteButton.setEnabled(mClients.getId() > 0 );
    }

    private Clients buildClient() {
        String name = mNameEdit.getText().toString();
        String surname = mSurNameEdit.getText().toString();
        String country = mCountryEdit.getText().toString();
        String city = mCityEdit.getText().toString();
        String street = mStreetEdit.getText().toString();
        String houseNumber = mHouseNumberEdit.getText().toString();
        String apartment = mApartmentEdit.getText().toString();

        Clients clients = mClients
                .setNameFluent(name)
                .setSurnameFluent(surname)
                .setCountryFluent(country)
                .setCityFluent(city)
                .setStreetFluent(street)
                .setHouseNumberFluent(houseNumber)
                .setApartmentFluent(apartment);

        return clients;
    }

    private void saveClient(Clients clients) {
        ClientService.getInstance().save(clients, getContext());
    }

    private void showDialog() {
        DialogFragment dialogFragment = new DeleteClientDialogFragment();
        dialogFragment.setTargetFragment(this, 0);
        dialogFragment.show(getFragmentManager(), "delete");
    }
}
