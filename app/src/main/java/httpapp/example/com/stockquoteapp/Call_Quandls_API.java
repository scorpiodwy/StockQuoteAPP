package httpapp.example.com.stockquoteapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Call_Quandls_API {

    private String date;
    private String price;


    public void load_info(String s) throws IOException {

        String ticker = s;
        String[] values = null;

        try {

            //Passing the ticker, one row return, and the close column to the API call e
            URL url = new URL("https://www.quandl.com/api/v3/datasets/WIKI/" + ticker + "/data.csv?rows=1&column_index=close&exclude_column_names=true");


            URLConnection connection = url.openConnection();
            InputStreamReader in = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(in);

            String line;

            while ((line = br.readLine()) != null) {

                values = line.split(",");
                date = values[0];
                price = values[1];

            }
        } catch (IOException ie) {
            ie.printStackTrace();

        }
    }

    public String getDate() {
        return date;
    }

    public String getPrice() {
        return price;
    }
}


/*
//Get T-1 to pass yesterdays date into the API call
String api_date;
DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
Calendar cal = Calendar.getInstance();
cal.add(Calendar.DATE, -1);
api_date = (dateFormat.format(cal.getTime()));
System.out.println(api_date);
--Add this to the API if I want to pass the date &date="+ api_date
*/
