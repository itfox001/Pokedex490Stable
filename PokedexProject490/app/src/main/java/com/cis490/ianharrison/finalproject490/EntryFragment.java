package com.cis490.ianharrison.finalproject490;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class EntryFragment extends Fragment {
TextView tn, ts, th, tw, td;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        View view = inflater.inflate(R.layout.fragment_entry, container, false);
        tn = (TextView) view.findViewById(R.id.name_textView);
        ts = (TextView) view.findViewById(R.id.species_textView);
        th = (TextView) view.findViewById(R.id.height_textView);
        tw = (TextView) view.findViewById(R.id.weight_textView);
        td = (TextView) view.findViewById(R.id.description_textView);
        return view;
    }

    public void setData(String name, String dexID, String species, String height, String weight, String description)
    {
        tn.setText(dexID + " " + name);
        ts.setText(species);
        th.setText(height);
        tw.setText(weight);
        td.setText(description);
    }

    String name, dexID, species, height, weight, description;
    public void getData(int index)
    {

        int i = index + 1;
        List<String> traits = new ArrayList<String>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("PokemonData");
        query.whereEqualTo("ID", i);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null) {
                    Log.d("results", "object retrieved!");
                    name = parseObjects.get(0).getString("pokemonName");
                    dexID = parseObjects.get(0).getString("dexID");
                    species = parseObjects.get(0).getString("species");
                    height = parseObjects.get(0).getString("height");
                    weight = parseObjects.get(0).getString("weight");
                    description = parseObjects.get(0).getString("description");
                    Log.d("results", "object name: " + name);
                    setData(name, dexID, species, height, weight, description);
                }
                else
                {
                    Log.d("results", "object NOT retrieved");
                }
            }
        });
                }
            }
