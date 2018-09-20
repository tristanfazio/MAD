package tfazio.prac03;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm=getSupportFragmentManager();
        Fragment fragMap = fm.findFragmentById(R.id.map);
        if(fragMap == null)
        {
            fragMap = new Map();
            fm.beginTransaction().add(R.id.map,fragMap).commit();
        }

        Fragment fragSelect = fm.findFragmentById(R.id.map);
        if(fragSelect == null)
        {
            fragSelect = new Map();
            fm.beginTransaction().add(R.id.map,fragSelect).commit();
        }

    }
}
