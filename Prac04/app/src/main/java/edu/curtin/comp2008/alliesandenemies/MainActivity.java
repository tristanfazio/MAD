package edu.curtin.comp2008.alliesandenemies;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // We've set everything up inside a fragment. This isn't strictly necessary, but can assist
        // maintainability, because fragments permit greater UI design flexibility.
        FragmentManager fm = getSupportFragmentManager();
        MainFragment frag = (MainFragment) fm.findFragmentById(R.id.fragmentContainer);
        if(frag == null)
        {
            frag = new MainFragment();
            fm.beginTransaction()
                .add(R.id.fragmentContainer, frag)
                .commit();
        }
    }
}
