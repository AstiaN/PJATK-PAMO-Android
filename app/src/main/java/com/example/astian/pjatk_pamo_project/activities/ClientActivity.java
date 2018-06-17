package com.example.astian.pjatk_pamo_project.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.example.astian.pjatk_pamo_project.R;
import com.example.astian.pjatk_pamo_project.fragments.ClientEditFragment;
import com.example.astian.pjatk_pamo_project.fragments.ClientListFragment;
import com.example.astian.pjatk_pamo_project.model.Clients;
import com.example.astian.pjatk_pamo_project.services.NavigationService;

public class ClientActivity extends AppCompatActivity implements ClientListFragment.OnNewClientButtonClickListener,
        ClientListFragment.OnClientClickListener,
        ClientListFragment.OnNavigateButtonClickListener,
        ClientEditFragment.OnClientSavedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean verticalOrientation = findViewById(R.id.main_vertical_container) != null;
        if (verticalOrientation) {

            if (savedInstanceState != null) {
                return;
            }

            ClientListFragment clientListFragment = new ClientListFragment();
            clientListFragment.setArguments(getIntent().getExtras());

            ClientEditFragment clientEditFragment = (ClientEditFragment)
                    getFragmentManager().findFragmentById(R.id.client_edit_fragment);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.add(R.id.main_vertical_container, clientListFragment);

            if (clientEditFragment != null) {
                transaction.add(R.id.client_edit_fragment, clientEditFragment);
            }

            transaction.commit();
        }
    }

    @Override
    public void onClientSaved() {
        getFragmentManager().popBackStackImmediate();

        boolean verticalOrientation = findViewById(R.id.main_vertical_container) != null;

        ClientListFragment listFragment;

        if(verticalOrientation) {
            listFragment = (ClientListFragment)
                    getFragmentManager().findFragmentById(R.id.main_vertical_container);

        } else {
            listFragment = (ClientListFragment)
                    getFragmentManager().findFragmentById(R.id.client_list_fragment);
        }

        listFragment.refreshList();
    }

    @Override
    public void onNewClientButtonClick() {
        loadClientEditFragment(new Clients());
    }

    @Override
    public void onClientClick(Clients clients) {
        loadClientEditFragment(clients);
    }

    private void loadClientEditFragment(Clients clients) {
        ClientEditFragment clientEditFragment = (ClientEditFragment)
                getFragmentManager().findFragmentById(R.id.client_edit_fragment);

        boolean horizontalOrientation = clientEditFragment != null;
        if (horizontalOrientation) {
            clientEditFragment.editClient(clients);
        } else {
            ClientEditFragment newFragment = ClientEditFragment.newInstance(clients);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.main_vertical_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void onNavigationButtonClicked(Clients clients) {
        Intent intent = NavigationService.buildNavigationIntent(clients);
        startActivity(intent);
    }
}
