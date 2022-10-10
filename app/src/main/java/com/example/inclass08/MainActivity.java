package com.example.inclass08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity implements  MainFragment.MFListener{

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.containerView,new MainFragment(), "MainFragment")
                .commit();


    }


    @Override
    public void sendDetails(Contact contact) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerView, ContactDetails.newInstance(contact),"ContactDetails")
                .addToBackStack(null)
                .commit();



    }
}