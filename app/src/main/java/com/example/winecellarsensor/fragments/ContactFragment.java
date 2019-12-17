package com.example.winecellarsensor.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.winecellarsensor.R;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class ContactFragment extends Fragment {

    private CardView call;
    private CardView email;
    private CardView message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        message = rootView.findViewById(R.id.contactMessage);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(view);
            }
        });
        email = rootView.findViewById(R.id.contactEmail);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail(view);
            }
        });
        call = rootView.findViewById(R.id.contactPhone);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            makeCall(view);
            }
        });
        return rootView;
    }

    private void sendMessage(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_MESSAGING);
        startActivity(intent);
    }


    public void makeCall(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_CONTACTS);
        startActivity(intent);
    }


    public void sendEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_EMAIL);
        startActivity(intent);

    }



}
