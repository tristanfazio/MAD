package tfazio.mad_assignment.fragments;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import tfazio.mad_assignment.DataClasses.GameData;
import tfazio.mad_assignment.R;
import tfazio.mad_assignment.activities.Navigation;

public class StatusBar extends Fragment
{
    //class fields
    private Button resetButton;
    private Button optionsButton;
    private TextView healthTextView;
    private TextView equipTextView;
    private TextView cashTextView;
    GameData gameData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        View view = inflater.inflate(
                R.layout.fragment_statusbar, ui, false);

        resetButton = (Button)view.findViewById(R.id.resetButton);
        healthTextView = (TextView)view.findViewById(R.id.healthNo);
        equipTextView = (TextView)view.findViewById(R.id.equipNo);
        cashTextView = (TextView)view.findViewById(R.id.cashNo);

        gameData = gameData.getInstance();



        //add listener for reset press
        resetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                gameData.restart();
                startActivity(new Intent(getActivity(),Navigation.class));
            }
        });

        return  view;
    }

    public void setHealth()
    {
        healthTextView.setText(Double.toString(gameData.getPlayer().getHealth()));
    }

    public void setEquip()
    {
        equipTextView.setText(Double.toString(gameData.getPlayer().getEquipmentMass()));
    }

    public void setCash()
    {
        cashTextView.setText(Integer.toString(gameData.getPlayer().getCash()));
    }

    public void updateStatusBar()
    {
        setHealth();
        setEquip();
        setCash();
    }
}
