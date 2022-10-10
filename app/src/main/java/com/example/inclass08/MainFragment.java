package com.example.inclass08;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class MainFragment extends Fragment implements  ContactAdapter.CAListener {

    HttpUrl url = HttpUrl.parse("https://www.theappsdr.com/contacts/json");
    HttpUrl urlDelete = HttpUrl.parse("https://www.theappsdr.com/contact/json/delete");
    ArrayList<Contact> contacts;
    MFListener listener;

    private final OkHttpClient client = new OkHttpClient();
    ContactAdapter adapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Button buttonAddContact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        buttonAddContact = view.findViewById(R.id.buttonAdd);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.containerView,new AddContact(),"Add Contact Fragment")
                            .addToBackStack(null)
                            .commit();
            }
        });


        showContacts();

        return view;
    }
    @Override
    public void delete(int pos) {

        FormBody formBody = new FormBody.Builder()
                .add("id",contacts.get(pos).Cid)
                .build();

        Request request = new Request.Builder()
                .url(urlDelete)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                showContacts();
            }
        });


    }

    @Override
    public void contactDetails(int position) {

        Contact contact  = contacts.get(position);
        listener.sendDetails(contact);
    }



    public void showContacts(){
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    ResponseBody responseBody = response.body();

                    Gson gson = new Gson();

                    ContactsResponse contactsResponse = gson.fromJson(responseBody.charStream(), ContactsResponse.class);

                    contacts =  contactsResponse.contacts;

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter = new ContactAdapter(contacts, MainFragment.this);
                            recyclerView.setAdapter(adapter);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof  MFListener){
                listener = (MFListener) context;
        }else{
            throw  new RuntimeException(context.toString()+" Must Implement MFListener");
        }
    }

    public interface MFListener{
        public void sendDetails(Contact contact);
    }



}
