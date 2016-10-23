package com.example.sse.customlistview_sse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    private
    ListView lvEpisodes;
    ListAdapter lvAdapter;
    int current;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvEpisodes = (ListView)findViewById(R.id.lvEpisodes);
        lvAdapter = new MyCustomAdapter(this.getBaseContext());  //instead of passing the boring default string adapter, let's pass our own, see class MyCustomAdapter below!
        lvEpisodes.setAdapter(lvAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);   //get rid of default behavior.

        // Inflate the menu; this adds items to the action bar
        getMenuInflater().inflate(R.menu.my_test_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.mnu_zero) {
            Toast.makeText(getBaseContext(), "Menu Zero.", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.mnu_one) {
            Toast.makeText(getBaseContext(), "Ring ring, Hi Mom.", Toast.LENGTH_LONG).show();
            return true;
        }


            return super.onOptionsItemSelected(item);  //if none of the above are true, do the default and return a boolean.
    }
}


//***************************************************************//
//create a  class that extends BaseAdapter
//you will be prompted to implement methods... choose yes.
//***************************************************************//


class MyCustomAdapter extends BaseAdapter {

    private
    SharedPreferences sharedPreferences;
    ArrayList<Episode> episodes;
    int current;




    Context context;   //What does refer to?  Context enables access to application specific resources.  Eg, spawning & receiving intents, locating the various managers.

    public MyCustomAdapter(Context aContext) {
        episodes = new ArrayList<Episode>();
        context = aContext;  //saving the context we'll need it again.
        Episode spock = new Episode(aContext.getResources().getStringArray(R.array.episodes)[0],
                aContext.getResources().getStringArray(R.array.episode_descriptions)[0],
                R.drawable.st_spocks_brain,
               aContext.getResources().getStringArray(R.array.imdb_identifiers)[0],
                aContext.getString(R.string.spock_star_key));
        Episode arena = new Episode(aContext.getResources().getStringArray(R.array.episodes)[1],
                aContext.getResources().getStringArray(R.array.episode_descriptions)[1],
                R.drawable.st_arena__kirk_gorn,
                aContext.getResources().getStringArray(R.array.imdb_identifiers)[1],
                aContext.getString(R.string.arena_star_key));
        Episode paradise = new Episode(aContext.getResources().getStringArray(R.array.episodes)[2],
                        aContext.getResources().getStringArray(R.array.episode_descriptions)[2],
                        R.drawable.st_this_side_of_paradise__spock_in_love,
                        aContext.getResources().getStringArray(R.array.imdb_identifiers)[2],
                        aContext.getString(R.string.paradise_star_key));
        Episode mirror = new Episode(aContext.getResources().getStringArray(R.array.episodes)[3],
                aContext.getResources().getStringArray(R.array.episode_descriptions)[3],
                R.drawable.st_mirror_mirror__evil_spock_and_good_kirk,
                aContext.getResources().getStringArray(R.array.imdb_identifiers)[3],
                aContext.getString(R.string.mirror_star_key));
        Episode plato = new Episode(aContext.getResources().getStringArray(R.array.episodes)[4],
                aContext.getResources().getStringArray(R.array.episode_descriptions)[4],
                R.drawable.st_platos_stepchildren__kirk_spock,
                aContext.getResources().getStringArray(R.array.imdb_identifiers)[4],
                aContext.getString(R.string.plato_star_key));
        Episode time = new Episode(aContext.getResources().getStringArray(R.array.episodes)[5],
                aContext.getResources().getStringArray(R.array.episode_descriptions)[5],
                R.drawable.st_the_naked_time__sulu_sword,
                aContext.getResources().getStringArray(R.array.imdb_identifiers)[5],
                aContext.getString(R.string.time_star_key));
        Episode tribbles = new Episode(aContext.getResources().getStringArray(R.array.episodes)[6],
                aContext.getResources().getStringArray(R.array.episode_descriptions)[6],
                R.drawable.st_the_trouble_with_tribbles__kirk_tribbles,
                aContext.getResources().getStringArray(R.array.imdb_identifiers)[6],
                aContext.getString(R.string.tribble_star_key));
        episodes.add(spock);
        episodes.add(arena);
        episodes.add(paradise);
        episodes.add(mirror);
        episodes.add(plato);
        episodes.add(time);
        episodes.add(tribbles);



        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file),
                Context.MODE_PRIVATE);
    }

    @Override
    public int getCount() {
//        return episodes.size();  //all of the arrays are same length, so return length of any... ick!  But ok for now. :)
        return episodes.size();  //all of the arrays are same length, so return length of any... ick!  But ok for now. :)

    }

    @Override
    public Object getItem(int position) {
//        return episodes.get(position);  //In Case you want to use an ArrayList
        return episodes.get(position);  //really should be retuning entire object... Crash?!?

    }

    @Override
    public long getItemId(int position) {
        return position;  //don't really use this, but have to do something since we had to implement.
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {  //convertView is Row, parent is the layout that has the row Views.
        //THIS IS WHERE THE ACTION HAPPENS.  Let's optimize a bit...
        ViewHolder viewHolder = null;
        View row;  //this will refer to the row to be inflated or displayed if it's already been displayed. (listview_row.xml)
        if (convertView == null){  //indicates this is the first time we are creating this row.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  //CRASH
            row = inflater.inflate(R.layout.listview_row, parent, false);
        }
        else
        {
            row = convertView;
        }

        Episode e = episodes.get(position);
        current = position;

        // 2. Now that we have a valid row instance, we need to get references to the views within that row.
        ImageView imgEpisode = (ImageView) row.findViewById(R.id.imgEpisode);  //notice we prefixed findViewByID with row, why?  row, is the container.
        TextView tvEpisodeTitle = (TextView) row.findViewById(R.id.tvEpisodeTitle);
        TextView tvEpisodeDescription = (TextView) row.findViewById(R.id.tvEpisodeDescription);
        RatingBar rb = (RatingBar) row.findViewById(R.id.rbEpisode);
        rb.setRating(sharedPreferences.getFloat(e.getPref_key(),0));
//        tvEpisodeTitle.setText(episodes.get(position));  //puts the predefined titles in the textview.
//
        tvEpisodeTitle.setText(e.getName());
        tvEpisodeDescription.setText(e.getDesc());

        imgEpisode.setImageResource(e.getDrawable());
        viewHolder = new ViewHolder();
        viewHolder.position = position;




        imgEpisode.setOnClickListener(viewHolder);
        tvEpisodeTitle.setOnClickListener(viewHolder);
        rb.setOnRatingBarChangeListener(viewHolder);
        tvEpisodeDescription.setOnClickListener(viewHolder);



        return row;  //once the row is fully constructed, return it.  Hey whatif we had buttons, can we target onClick Events within the rows, yep!
    }
    private class ViewHolder implements View.OnClickListener, RatingBar.OnRatingBarChangeListener
    {
        int position;

        @Override
        public void onClick(View v) {
            Uri uri = Uri.parse("http://imdb.com/title/tt" + episodes.get(position).getImdb());
            Intent launch = new Intent(Intent.ACTION_VIEW, uri);
            launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(launch);

        }

        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            if(fromUser) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putFloat(episodes.get(position).getPref_key(), rating);
                editor.commit();
                Uri uri = Uri.parse("http://imdb.com/title/tt" + episodes.get(position).getImdb());
                Intent launch = new Intent(Intent.ACTION_VIEW, uri);
                launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(launch);
            }
        }
    }



}