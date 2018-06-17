package com.example.astian.pjatk_pamo_project.services;

import android.content.Context;

import com.example.astian.pjatk_pamo_project.data.ClientDao;
import com.example.astian.pjatk_pamo_project.data.AppDatabase;
import com.example.astian.pjatk_pamo_project.model.Clients;

public class ClientService {

    private static volatile ClientService instance;

    private ClientService(){}

    public static ClientService getInstance() {
        if (instance == null) {
            instance = new ClientService();
        }

        return instance;
    }

    public void save(Clients clients, Context context) {
        ClientDao dao = AppDatabase.getInstance(context)
                .getClientDao();

        if (clients.getId() == 0) {
            dao.insert(clients);
        } else {
            dao.update(clients);
        }
    }

    public void delete(Clients clients, Context context) {
        ClientDao dao = AppDatabase.getInstance(context)
                .getClientDao();

        if (clients.getId() > 0) {
            dao.delete(clients);
        }
    }
}
