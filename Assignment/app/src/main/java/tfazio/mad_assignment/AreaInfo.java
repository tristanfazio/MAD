package tfazio.mad_assignment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AreaInfo extends Fragment
{
    private ImageView starredImageView;
    private EditText descriptionEditText;
    private TextView playerPosTextView;
    private TextView coordsTextView;
    GameData gameData;
    Area area;
    Player player;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        View view = inflater.inflate(
                R.layout.fragment_areainfo, ui, false);

        descriptionEditText = (EditText)view.findViewById(R.id.descriptionEditText);
        playerPosTextView = (TextView)view.findViewById(R.id.playerposTextView);
        starredImageView = (ImageView)view.findViewById(R.id.starredImageView);
        coordsTextView = (TextView)view.findViewById(R.id.coordsTextView);

        gameData = gameData.getInstance();
        player=gameData.getPlayer();
        area = gameData.getArea(player.getPosition());
        //set UI elements from area info

        updateAreaInfo();

        //listener for star press
        starredImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("DEBUG","\n Toggle Star");
                area.toggleStarred();
                setStarred(area.getStarred());

                if(getActivity() instanceof Overview)
                {
                    ((Overview) getActivity()).getMapFrag().notifyAdapter();
                }
            }
        });

        //listener for edit text change
        descriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable)
            {
                area.setDescription(descriptionEditText.getText().toString());
            }
        });



        return  view;
    }

    public String getPlayerPosText()
    {
        return playerPosTextView.toString();
    }

    public void setDescription(String inDescription)
    {
        descriptionEditText.setText(inDescription);
    }

    public void setPlayerPos(boolean inPos)
    {
        if(inPos)
        {
            playerPosTextView.setText("Town");
            Log.d("DEBUG","Set text to town");
        }
        else
        {
            playerPosTextView.setText("Wilderness");
            Log.d("DEBUG","Set text to wilderness");
        }
    }

    public void setStarred(boolean inStarred)
    {
        if(inStarred)
        {
            starredImageView.setImageResource(android.R.drawable.star_big_on);
        }
        else
        {
            starredImageView.setImageResource(android.R.drawable.star_big_off);
        }
    }

    public void setCoords(int[] inCoords)
    {
        //set the coords label, but manipulate so that it makes more sense from an x,y map perspective, rather than a 2D array
        coordsTextView.setText("" + inCoords[1] + "," + (gameData.getYMax()-inCoords[0]));
    }

    public void updateAreaInfo()
    {
        //update from current player pos
        setDescription(gameData.getArea(gameData.getPlayer().getPosition()).getDescription());
        setPlayerPos(gameData.getArea(gameData.getPlayer().getPosition()).isTown());
        setStarred(gameData.getArea(gameData.getPlayer().getPosition()).getStarred());
        setCoords(gameData.getPlayer().getPosition());
    }

    public void setAreaInfo(Area inArea)
    {
        //update from selected area from overview
        area = inArea;
        setDescription(area.getDescription());
        setPlayerPos(area.isTown());
        setStarred(area.getStarred());
        setCoords(area.getXY());
    }

}

