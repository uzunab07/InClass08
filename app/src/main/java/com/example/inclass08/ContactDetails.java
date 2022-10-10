package com.example.inclass08;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactDetails extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Contact contact;

    public ContactDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param contact Parameter 1.
     * @return A new instance of fragment ContactDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactDetails newInstance(Contact contact) {
        ContactDetails fragment = new ContactDetails();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, contact);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            contact = (Contact) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    TextView name,email,phone,type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_contact_details, container, false);

        name = view.findViewById(R.id.textViewContactName);
        email  = view.findViewById(R.id.textViewContactEmail);
        phone = view.findViewById(R.id.textViewnumber);
        type = view.findViewById(R.id.textViewphoneType);

        if(contact!=null){
            name.setText(" "+contact.Name);
            email.setText(" "+contact.Email);
            phone.setText(" "+contact.Phone);
            type.setText(" "+contact.PhoneType);
        }
        return  view;
    }

}