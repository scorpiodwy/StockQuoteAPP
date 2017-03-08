package httpapp.example.com.stockquoteapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_1).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btn_1:

                EditText enteredSymbol = (EditText) findViewById(R.id.editText);     //get the ticker a user entered
                String thisSymbol = enteredSymbol.getText().toString();              //Get the String instead of id

                myStockInfo data = new myStockInfo();                                //create an object of the myStockInfo method
                data.execute(thisSymbol);                                          //Execute this function with my symbol
                //data.doInBackground(thisSymbol);


                break;

        }
    }

    private class myStockInfo extends AsyncTask<String, Void, String> {

        TextView myPrice = (TextView) findViewById(R.id.textView2);                 //Create an instance of the TextView

        protected String doInBackground(String... thisSymbol) {

            String price = "";


            try {
                Call_Quandls_API x = new Call_Quandls_API();                        //Create an object of API class to use
                x.load_info(thisSymbol[0]);                                         //Pass the symbol into the API class
                price = x.getPrice();
            } catch (Exception e) {
                System.out.println("This is breaking " + e);
            }
            return price;
        }

        protected void onPostExecute(String message) {

            String price = message.toString();
            myPrice.setText(price);                                                 //Set the price to that TestView
        }
    }

}