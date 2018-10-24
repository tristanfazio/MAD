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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        View view = inflater.inflate(
                R.layout.fragment_areainfo, ui, false);

        descriptionEditText = (EditText)view.findViewById(R.id.descriptionEditText);
        playerPosTextView = (TextView)view.findViewById(R.id.playerposTextView);
        starredImageView = (ImageView)view.findViewById(R.id.starredImageView);
        coordsTextView = (TextView)view.findViewById(R.id.starredImageView);

        gameData = gameData.getInstance();

        //set UI elements from area info

        updateAreaInfo();

        //listener for star press
        starredImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("DEBUG","\n Toggle Star");
                gameData.getArea(gameData.getPlayer().getPosition()).toggleStarred();
                setStarred(gameData.getArea(gameData.getPlayer().getPosition()).getStarred());
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
                gameData.getArea(gameData.getPlayer().getPosition()).setDescription(descriptionEditText.getText().toString());
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
        coordsTextView.setText("" + inCoords[0] + "," + inCoords[1]);
    }

    public void updateAreaInfo()
    {
        setDescription(gameData.getArea(gameData.getPlayer().getPosition()).getDescription());
        setPlayerPos(gameData.getArea(gameData.getPlayer().getPosition()).isTown());
        setStarred(gameData.getArea(gameData.getPlayer().getPosition()).getStarred());
        setCoords(gameData.getPlayer().getPosition());
    }

}

