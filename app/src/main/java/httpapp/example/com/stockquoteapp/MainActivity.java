package httpapp.example.com.stockquoteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText editText;
        TextView textView;


        findViewById(R.id.btn_1).setOnClickListener(this);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView2);
    }

    @Override
    public void onClick(View v){

        String price;

        switch (v.getId()) {
            case R.id.btn_1:


                EditText enteredSymbol = (EditText) findViewById(R.id.editText);     //get the ticker a user entered
                String thisSymbol = enteredSymbol.getText().toString();              //Get the String instead of id

                Call_Quandls_API x = new Call_Quandls_API();                         //Create an object of API call to use


                x.load_info(thisSymbol);                                             //Pass the symbol into the API class
                price = x.getPrice();

                TextView myPrice = (TextView) findViewById(R.id.textView2);           //Create an instance of the TextView
                myPrice.setText(price);                                              //Set the price to that TestView

                break;

        }
    }
}