package com.example.android.on_lineschool;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class AvaliacoesFragment extends Fragment {


    String user_uid;
    ListView caldetails;
    ListView prima;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter2;
    private ArrayList<String> listcaldetails = new ArrayList<>();
    private ArrayList<String> listcaldetails2 = new ArrayList<>();


    public AvaliacoesFragment() {
        // Required empty public constructor
    }

    FirebaseAuth firebaseAUTH = FirebaseAuth.getInstance();
    DatabaseReference refnotas = FirebaseDatabase.getInstance().getReference("notas");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_avaliacoes, container, false);

        user_uid = firebaseAUTH.getCurrentUser().getUid().toString();

        DatabaseReference refuid = refnotas.child(user_uid.toString());

        caldetails = (ListView) view.findViewById(R.id.lvdisciplinas);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,listcaldetails);

        caldetails.setAdapter(adapter);

        refuid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }

                listcaldetails.clear();
                listcaldetails.addAll(set);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        prima = (ListView) view.findViewById(R.id.lvnotas);

        adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,listcaldetails2);

        prima.setAdapter(adapter2);

        refuid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getValue().toString());
                }

                listcaldetails2.clear();
                listcaldetails2.addAll(set);

                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }

}
