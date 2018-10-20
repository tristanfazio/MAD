package tfazio.mad_assignment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AreaInfo extends Fragment
{
    private ImageView starredImageView;
    private EditText descriptionEditText;
    private TextView playerPosTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        View view = inflater.inflate(
                R.layout.fragment_areainfo, ui, false);

         descriptionEditText = (EditText)view.findViewById(R.id.descriptionEditText);
         playerPosTextView = (TextView)view.findViewById(R.id.playerposTextView);
         starredImageView = (ImageView)view.findViewById(R.id.starredImageView);




        return  view;
    }
}
