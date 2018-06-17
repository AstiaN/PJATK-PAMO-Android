package com.example.astian.pjatk_pamo_project.services;

import android.content.Intent;
import android.net.Uri;

import com.example.astian.pjatk_pamo_project.model.Clients;

public class NavigationService {
    public static Intent buildNavigationIntent(Clients a) {
        String client = a.getStreet() + " " + a.getHouseNumber() + ",+" + a.getCity() + " " + a.getCountry();
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + client);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        return mapIntent;
    }
}
