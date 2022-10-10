package com.example.inclass08;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class AddContact extends Fragment {

EditText editTextName,editTextPhone,editTextPhoneType,editTextEmail;
Button buttonSubmit,buttonCancel;
private OkHttpClient client = new OkHttpClient();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_contact, container, false);

        editTextName = view.findViewById(R.id.editTextTextPersonName);
        editTextEmail = view.findViewById(R.id.editTextTextPersonEmail);
        editTextPhone = view.findViewById(R.id.editTextPhone);
        editTextPhoneType = view.findViewById(R.id.editTextTextPersonPhoneType);

        buttonSubmit = view.findViewById(R.id.buttonSubmit);
        buttonCancel = view.findViewById(R.id.buttonCancel);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    create();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });




        return view;
    }
    public void create(){
        HttpUrl urlCreate =  HttpUrl.parse("https://www.theappsdr.com/contact/json/create");

        String name=null,email=null,phone=null,phoneType=null;

        name = editTextName.getText().toString();
        email = editTextEmail.getText().toString();
        phone = editTextPhone.getText().toString();
        phoneType = editTextPhoneType.getText().toString();
        if(!name.isEmpty()|| !email.isEmpty() || !phone.isEmpty() || !phoneType.isEmpty()){
            FormBody formBody = new FormBody.Builder()
                    .add("name",name)
                    .add("email",email)
                    .add("phone",phone)
                    .add("type",phoneType)
                    .build();

            Request request = new Request.Builder()
                    .url(urlCreate)
                    .post(formBody)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String msg = response.body().string();
                            if(response.isSuccessful()){
                                getActivity().getSupportFragmentManager().popBackStack();
                            }else{
                                Log.d("demo", "onResponse: Something went wrong"+msg);
                            }
                }
            });
        }else{
            Toast.makeText(getContext(), "Enter Valid Information", Toast.LENGTH_SHORT).show();
        }



    }
}