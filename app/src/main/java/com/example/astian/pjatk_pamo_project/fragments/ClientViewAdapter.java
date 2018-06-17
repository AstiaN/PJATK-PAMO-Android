package com.example.astian.pjatk_pamo_project.fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import com.example.astian.pjatk_pamo_project.R;
import com.example.astian.pjatk_pamo_project.model.Clients;

public class ClientViewAdapter extends RecyclerView.Adapter<ClientViewAdapter.ClientViewHolder> {

    private final List<Clients> mValues;
    private final ClientListFragment.OnClientClickListener onClientClickListener;
    private final ClientListFragment.OnNavigateButtonClickListener onNavigateButtonClickListener;


    public ClientViewAdapter(List<Clients> mValues,
                             ClientListFragment.OnClientClickListener onClientClickListener,
                             ClientListFragment.OnNavigateButtonClickListener onNavigateButtonClickListener) {
        this.mValues = mValues;
        this.onClientClickListener = onClientClickListener;
        this.onNavigateButtonClickListener = onNavigateButtonClickListener;
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_view, parent, false);
        return new ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ClientViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onClientClickListener) {
                    onClientClickListener.onClientClick(holder.mItem);
                }
            }
        });

        holder.mNavigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onNavigateButtonClickListener) {
                    onNavigateButtonClickListener.onNavigationButtonClicked(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ClientViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mContentView;
        final ImageButton mNavigateButton;
        Clients mItem;

        ClientViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
            mNavigateButton = (ImageButton) view.findViewById(R.id.navigate_btn);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}