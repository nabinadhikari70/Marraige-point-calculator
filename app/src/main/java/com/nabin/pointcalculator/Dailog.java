package com.nabin.pointcalculator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class Dailog extends AppCompatDialogFragment {

    private EditText editTextPlayer1;
    private EditText editTextPlayer2;
    private EditText editTextPlayer3;
    private EditText editTextPlayer4;
    private EditText editTextPlayer5;
    private DailogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dailog,null);

        editTextPlayer1=view.findViewById(R.id.player1Entry);
        editTextPlayer2=view.findViewById(R.id.player2Entry);
        editTextPlayer3=view.findViewById(R.id.player3Entry);
        editTextPlayer4=view.findViewById(R.id.player4Entry);
        editTextPlayer5=view.findViewById(R.id.player5Entry);

        builder.setView(view)
                .setTitle("Enter Player Names:")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name1 = editTextPlayer1.getText().toString();
                        String name2 = editTextPlayer2.getText().toString();
                        String name3 = editTextPlayer3.getText().toString();
                        String name4 = editTextPlayer4.getText().toString();
                        String name5 = editTextPlayer5.getText().toString();

                        listener.applyTexts(name1,name2,name3,name4,name5);
                    }
                });


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener=(DailogListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString()+"must implement DailogListener");
        }
    }

    public interface DailogListener{
        void applyTexts(String name1,String name2,String name3,String name4,String name5);
    }
}
