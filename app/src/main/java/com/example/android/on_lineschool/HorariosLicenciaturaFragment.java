package com.example.android.on_lineschool;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HorariosLicenciaturaFragment extends Fragment {

    ExpandableListView expandableListView2;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    public HorariosLicenciaturaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horarios_licenciatura, container, false);

        expandableListView2 = (ExpandableListView) view.findViewById(R.id.expandableListView2);
        expandableListDetail = DadosExpandableList2.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
        expandableListView2.setAdapter(expandableListAdapter);
        expandableListView2.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView2.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView2.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if (expandableListTitle.get(groupPosition).equals("Engenharia Multimedia") &&
                        expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals("1º Ano")) {

                    Uri uri = Uri.parse("http://my.istec.pt/wp-content/uploads/2016/11/1-Ano-Mul.pdf");
                    Intent i = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(i);


                } else if (expandableListTitle.get(groupPosition).equals("Engenharia Multimedia") &&
                        expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals("2º Ano")) {
                    Uri uri = Uri.parse("http://my.istec.pt/wp-content/uploads/2016/11/2-Ano-Mul.pdf");
                    Intent i = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(i);

                } else if (expandableListTitle.get(groupPosition).equals("Engenharia Multimedia") &&
                        expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals("3º Ano")) {
                    Uri uri = Uri.parse("http://my.istec.pt/wp-content/uploads/2016/11/3-Ano-Mul.pdf");
                    Intent i = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(i);


                } else if (expandableListTitle.get(groupPosition).equals("Engenharia Informatica") &&
                        expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals("1º Ano")) {
                    Uri uri = Uri.parse("http://my.istec.pt/wp-content/uploads/2016/10/1-PL-INF-2.pdf");
                    Intent i = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(i);


                } else if (expandableListTitle.get(groupPosition).equals("Engenharia Informatica") &&
                        expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals("2º Ano")) {
                    Uri uri = Uri.parse("http://my.istec.pt/wp-content/uploads/2016/11/2-PL-INF.pdf");
                    Intent i = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(i);

                } else if (expandableListTitle.get(groupPosition).equals("Engenharia Informatica") &&
                        expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals("3º Ano")) {
                    Uri uri = Uri.parse("http://my.istec.pt/wp-content/uploads/2016/11/3-PL-INF-1.pdf");
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