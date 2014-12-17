package com.cis490.ianharrison.finalproject490;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,this.getResources().getStringArray(R.array.pokemon_names));
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    String name;
    String dexID;
    String species;
    String height;
    String weight;
    String description;
    ParseFile picture;
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //new ViewSwapTask().execute(name, dexID, species, height, weight, description);
        Intent intent = new Intent(getApplicationContext(), EntryActivity.class);
        intent.putExtra("index", i);
        startActivity(intent);

    }

    protected class ViewSwapTask extends AsyncTask<String, Void, List<String>> {

        @Override
        protected List<String> doInBackground(String... params) {
            int count = params.length;
            List<String> result = new ArrayList<String>();
            for(int i=0; i < count; i++)
            {
                result.add(params[i]);
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<String> traits)
        {
            setContentView(R.layout.fragment_entry);
            TextView tn = (TextView) findViewById(R.id.name_textView);
            TextView ts = (TextView) findViewById(R.id.species_textView);
            TextView th = (TextView) findViewById(R.id.height_textView);
            TextView tw = (TextView) findViewById(R.id.weight_textView);
            TextView td = (EditText) findViewById(R.id.description_textView);

            tn.setText(traits.get(0)+ " " + traits.get(1));
            ts.setText(traits.get(2));
            th.setText(traits.get(3));
            tw.setText(traits.get(4));
            td.setText(traits.get(5));

            setContentView(R.layout.activity_main);
        }

    }
}


