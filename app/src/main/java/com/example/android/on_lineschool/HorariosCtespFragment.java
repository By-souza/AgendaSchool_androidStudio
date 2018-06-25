package com.example.android.on_lineschool;


import android.content.Intent;
import android.media.effect.EffectUpdateListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class HorariosCtespFragment extends Fragment {


    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    public HorariosCtespFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horarios_ctesp, container, false);

        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        expandableListDetail = DadosExpandableList.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if(expandableListTitle.get(groupPosition).equals("Multimédia")&&
                        expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals("1º Ano")){

                    Uri uri = Uri.parse("http://my.istec.pt/wp-content/uploads/2017/02/Turma-A-1.pdf");
                    Intent i = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(i);



                }else if (expandableListTitle.get(groupPosition).equals("Multimédia")&&
                        expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals("2º Ano")) {
                    Uri uri = Uri.parse("http://my.istec.pt/wp-content/uploads/2017/02/Turma-A-2.pdf");
                    Intent i = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(i);


                }else if (expandableListTitle.get(groupPosition).equals("DDM")&&
                        expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals("1º Ano")){
                    Uri uri = Uri.parse("http://my.istec.pt/wp-content/uploads/2017/01/Turma-A-10.pdf");
                    Intent i = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(i);


                }else if(expandableListTitle.get(groupPosition).equals("DDM")&&
                        expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals("2º Ano")){
                    Uri uri = Uri.parse("http://my.istec.pt/wp-content/uploads/2017/02/Turma-A.pdf");
                    Intent i = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(i);

                }


             /*   Toast.makeText(
                        getActivity(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();*/
                return false;
            }
        });
        return view;
    }

}