package com.example.inclass08;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends  RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{
    ArrayList<Contact> contats;
    CAListener caListener;


    public ContactAdapter(ArrayList<Contact> contats,ContactAdapter.CAListener caListener ) {
        this.contats = contats;
        this.caListener = caListener;

    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        ContactViewHolder contactViewHolder  = new ContactViewHolder(view,contats,caListener);



        return contactViewHolder;
    }

    @Override
    public int getItemCount() {
        return this.contats.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contats.get(position);
        holder.textViewName.setText(contact.Name);
        holder.textViewEmail.setText(contact.Email);
        holder.textViewPhoneType.setText(contact.PhoneType);
        holder.textViewPhoneNumber.setText(contact.Phone);
        holder.position = position;
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caListener.delete(position);
            }
        });
    }



    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName,textViewEmail,textViewPhoneNumber,textViewPhoneType;
        ImageButton delete;
        int position;
        ContactAdapter.CAListener caListener;

        public ContactViewHolder(@NonNull View itemView,ArrayList<Contact> contacts,ContactAdapter.CAListener caListener ) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    caListener.contactDetails(position);
                }
            });
            this.caListener = caListener;
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPhoneNumber  = itemView.findViewById(R.id.textViewPhoneNumber);
            textViewPhoneType = itemView.findViewById(R.id.textViewPhoneType);
            delete = itemView.findViewById(R.id.delete);
        }


    }

    public interface CAListener{
        public void delete(int pos);
        public void contactDetails(int position);
    }
}
