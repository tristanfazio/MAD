package tfazio.mad_assignment.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tfazio.mad_assignment.DataClasses.Area;
import tfazio.mad_assignment.fragments.AreaInfo;
import tfazio.mad_assignment.DataClasses.GameData;
import tfazio.mad_assignment.fragments.OverviewMap;
import tfazio.mad_assignment.DataClasses.Player;
import tfazio.mad_assignment.R;
import tfazio.mad_assignment.fragments.StatusBar;

public class Overview extends AppCompatActivity
{
    //game objects
    GameData gameData;
    Player player;
    private Area area;
    //UI objects
    private TextView titleTextView;
    private TextView flavourTextView;
    private Button closeButton;
    private StatusBar statusBarFrag;
    private OverviewMap mapFrag;
    private AreaInfo areaInfoFrag;


    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_overview);

        //get game data
        gameData = gameData.getInstance();
        player = gameData.getPlayer();
        area = gameData.getArea(player.getPosition());

        //Fragment manager
        FragmentManager fm = getSupportFragmentManager();
        //create statusbar
        if(statusBarFrag == null)//frag doesn't exist
        {
            statusBarFrag = new StatusBar();
            fm.beginTransaction()
                    .add(R.id.f_statusBar,statusBarFrag)
                    .commit();
        }
        //create areaInfo fragment
        areaInfoFrag = (AreaInfo)fm.findFragmentById(R.id.f_areaInfo);
        if(areaInfoFrag==null)//frag doesnt exist
        {
            areaInfoFrag = new AreaInfo();
            fm.beginTransaction()
                    .add(R.id.f_areaInfo,areaInfoFrag)
                    .commit();
        }
        //create map
        mapFrag = (OverviewMap)fm.findFragmentById(R.id.map);
        if(mapFrag == null)
        {
            mapFrag = new OverviewMap();
            fm.beginTransaction()
                    .add(R.id.map,mapFrag)
                    .commit();
        }

        //identify UI elements
        closeButton = (Button)findViewById(R.id.closeButton);

        closeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        /* update all UI elements */
        super.onResume();
        areaInfoFrag.updateAreaInfo();
        statusBarFrag.updateStatusBar();
    }

    public AreaInfo getAreaInfoFrag()
    {
        return areaInfoFrag;
    }

    public OverviewMap getMapFrag()
    {
       return mapFrag;
    }
}
