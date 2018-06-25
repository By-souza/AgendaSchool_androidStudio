package com.example.android.on_lineschool;


import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import static android.R.attr.id;


/**
 * A simple {@link Fragment} subclass.
 */

public class HorariosFragment extends Fragment implements View.OnClickListener {

    public HorariosFragment() {
        // Required empty public constructor

    }


    public ImageView ctesp;
    public ImageView licenciaturas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horarios, container, false);

        ctesp = (ImageView) view.findViewById(R.id.iv_ctesp);
        ctesp.setOnClickListener(this);

        licenciaturas =(ImageView) view.findViewById(R.id.iv_licenciaturas);
        licenciaturas.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {
        FragmentManager manag = getFragmentManager();

        switch (v.getId()) {


            case R.id.iv_ctesp:
            HorariosCtespFragment HorariosCtespFrag = new HorariosCtespFragment();
                String backStateName = HorariosCtespFrag.getClass().getName();
            manag.beginTransaction()
                    .replace(
                    R.id.content_main_page,
                    HorariosCtespFrag,
                    HorariosCtespFrag.getTag()
            )
                    .addToBackStack(backStateName)
                    .commit();
            break;

            case R.id.iv_licenciaturas:
            HorariosLicenciaturaFragment HorariosLicenciaturaFrag = new HorariosLicenciaturaFragment();
                backStateName = HorariosLicenciaturaFrag.getClass().getName();
            manag.beginTransaction()
                    .replace(
                    R.id.content_main_page,
                    HorariosLicenciaturaFrag,
                    HorariosLicenciaturaFrag.getTag()
            )
                    .addToBackStack(backStateName)
                    .commit();
            break;

            default:
                break;
        }
    }
}
