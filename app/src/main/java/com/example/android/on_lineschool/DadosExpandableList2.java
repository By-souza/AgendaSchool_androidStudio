package com.example.android.on_lineschool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Duarte on 12-02-2017.
 */

public class DadosExpandableList2 {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> cricket = new ArrayList<String>();
        cricket.add("1º Ano");
        cricket.add("2º Ano");
        cricket.add("3º Ano");

        List<String> football = new ArrayList<String>();
        football.add("1º Ano");
        football.add("2º Ano");
        football.add("3º Ano");

        expandableListDetail.put("Engenharia Multimedia", cricket);
        expandableListDetail.put("Engenharia Informatica", football);


        return expandableListDetail;
    }
}