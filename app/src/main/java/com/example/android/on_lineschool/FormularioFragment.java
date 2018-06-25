package com.example.android.on_lineschool;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioFragment extends CalendarioFragment implements View.OnClickListener {

    TextView data;
    Button button;
    TextView turma;
    TextView disciplina;
    Spinner spinner;
    ListView details;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> listdetails = new ArrayList<>();


    private DatabaseReference entryCal = FirebaseDatabase.getInstance().getReference("calendar");

    public FormularioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_formulario, container, false);
        data = (TextView) view.findViewById(R.id.tvDia);

        int ano = getArguments().getInt("ano");
        int mes = getArguments().getInt("mes");
        int dia = getArguments().getInt ("dia");


        if (ano != 0 && mes != 0 && dia !=0){

            data.setText(dia + "-" + mes +"-" + ano);

        }

        button = (Button) view.findViewById(R.id.bconfirma);
        turma = (TextView) view.findViewById(R.id.tvTurma);
        disciplina = (TextView) view.findViewById(R.id.tvDisciplina);
        spinner = (Spinner) view.findViewById(R.id.spTipo);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spTbExame, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appear
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        button.setOnClickListener(this);

        details = (ListView) view.findViewById(R.id.lvInfo);

        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,listdetails);

        details.setAdapter(arrayAdapter);
        DatabaseReference entrydetail = entryCal.child(data.getText().toString());
        entrydetail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }

                listdetails.clear();
                listdetails.addAll(set);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        if (turma != null && disciplina != null) {

            String detail = (disciplina.getText().toString()+":"+turma.getText().toString()+":"+spinner.getSelectedItem().toString());
            Map<String,Object> map = new HashMap<String, Object>();
            map.put(data.getText().toString(),"");

            if(map.isEmpty()){
                entryCal.updateChildren(map);


            }else {
                DatabaseReference entrydetail = entryCal.child(data.getText().toString());
                Map<String, Object> mapdet = new HashMap<String, Object>();
                mapdet.put(detail.toString(), "");
                entrydetail.updateChildren(mapdet);
            }

            Toast.makeText(getActivity(), "Registo efetuado com sucesso!", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getActivity(), "Deve de preencher todos os campos", Toast.LENGTH_LONG).show();
        }

    }
}
