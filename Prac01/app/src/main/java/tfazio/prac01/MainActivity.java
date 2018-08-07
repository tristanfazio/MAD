package tfazio.prac01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    private EditText inputOne;
    private EditText inputTwo;
    private Button plusButton;
    private Button minusButton;
    private Button timesButton;
    private Button divideButton;
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputOne = (EditText)findViewById(R.id.inputOne);
        inputTwo = (EditText)findViewById(R.id.inputTwo);
        output = (TextView)findViewById(R.id.output);
        plusButton = (Button)findViewById(R.id.plusButton);
        minusButton = (Button)findViewById(R.id.minusButton);
        timesButton = (Button)findViewById(R.id.timesButton);
        divideButton = (Button)findViewById(R.id.divideButton);



        plusButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Do something when the button is pressed.

                //get inputs
                double numberOne = Double.parseDouble(inputOne.getText().toString());
                double numberTwo = Double.parseDouble(inputTwo.getText().toString());

                //compute
                double result = numberOne + numberTwo;

                //output result
                output.setText(Double.toString(result));

            }
        });

        minusButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Do something when the button is pressed.

                //get inputs
                double numberOne = Double.parseDouble(inputOne.getText().toString());
                double numberTwo = Double.parseDouble(inputTwo.getText().toString());

                //compute
                double result = numberOne - numberTwo;

                //output result
                output.setText(Double.toString(result));
            }
        });

        timesButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Do something when the button is pressed.

                //get inputs
                double numberOne = Double.parseDouble(inputOne.getText().toString());
                double numberTwo = Double.parseDouble(inputTwo.getText().toString());

                //compute
                double result = numberOne * numberTwo;

                //output result
                output.setText(Double.toString(result));
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Do something when the button is pressed.

                //get inputs
                double numberOne = Double.parseDouble(inputOne.getText().toString());
                double numberTwo = Double.parseDouble(inputTwo.getText().toString());

                //compute
                double result = numberOne / numberTwo;

                //output result
                output.setText(Double.toString(result));
            }
        });

    }


}
