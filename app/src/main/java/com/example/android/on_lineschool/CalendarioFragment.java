package com.example.android.on_lineschool;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarioFragment extends Fragment implements CalendarView.OnDateChangeListener {

    public CalendarView calendarView;
    String type;
    String userid;
    ListView caldetails;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listcaldetails = new ArrayList<>();
    String datalist;
    View view;

    private DatabaseReference refCal = FirebaseDatabase.getInstance().getReference("calendar");

    public CalendarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_calendario, container, false);

        calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(this);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        DatabaseReference reftype = FirebaseDatabase.getInstance().getReference("users");
        userid = firebaseAuth.getCurrentUser().getUid().toString();

        //get type database user
        reftype.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d:
                        dataSnapshot.getChildren()) {
                    if(userid.contentEquals(d.getKey())){

                        users u = d.getValue(users.class);

                        type = u.getType();
                        Toast.makeText(getActivity(), "Valor recebido", Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Toast.makeText(getActivity(), "Falha ao ler o valor", Toast.LENGTH_LONG).show();
            }
        });

        return view;

    }


    @Override
    public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth) {
       datalist = (dayOfMonth + "-" + month + "-" + year);

      if(type.equals("aluno")) {

            caldetails = (ListView) view.findViewById(R.id.lvInfocal);

            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,listcaldetails);

            caldetails.setAdapter(adapter);
            DatabaseReference entrydetail = refCal.child(datalist.toString());

            entrydetail.addValueEventListener(new ValueEventListener() {
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

             Toast.makeText(getActivity(), datalist, Toast.LENGTH_LONG).show();
         }else{

            FragmentManager manag = getFragmentManager();
            Calendar calendar = Calendar.getInstance();
            String data = calendar.toString();
            Bundle bundle = new Bundle();
            bundle.putInt("ano", year);
            bundle.putInt("mes", month+1);
            bundle.putInt("dia", dayOfMonth);
            bundle.putString("data", data);
            FormularioFragment FormularioFrag = new FormularioFragment();
            FormularioFrag.setArguments(bundle);
            String backStateName = FormularioFrag.getClass().getName();
            manag.beginTransaction().replace(
                    R.id.content_main_page,
                    FormularioFrag,
                    FormularioFrag.getTag())
                    .addToBackStack(backStateName)
                    .commit();
        }
    }
}
