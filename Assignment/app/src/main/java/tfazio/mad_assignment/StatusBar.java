package tfazio.mad_assignment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class StatusBar extends Fragment
{
    //class fields
    private Button resetButton;
    private Button optionsButton;
    private TextView healthTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        View view = inflater.inflate(
                R.layout.fragment_statusbar, ui, false);

        resetButton = (Button)view.findViewById(R.id.resetButton);
        healthTextView = (TextView)view.findViewById(R.id.healthNo);


        //add listener for reset press
        resetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                restart(v);
            }
        });

        return  view;
    }

    public void updateHealth(double inHealth)
    {
        healthTextView.setText(Double.toString(inHealth));
    }

    public void restart(View view)
    {

    }
}
