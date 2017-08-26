package losbibis.xia;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.String;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

/**
 * Created by 46519757 on 06/11/2016.
 */

public class DiceActivity extends Activity implements View.OnClickListener {
    private Gson gson = new GsonBuilder().create();
        private TextView textResult;
    private TextView textView_test_url;
private TextView textView_result_json;
    private String json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }



        TextView textTitle = (TextView) findViewById(R.id.textTitle);
        textTitle.setText("6 sided dice");

        textResult = ((TextView) findViewById(R.id.textResultat));
        textResult.setText("");

        textView_test_url =  ((TextView) findViewById(R.id.textView_test_url));
        textView_test_url.setText("");

        textView_result_json = ((TextView) findViewById(R.id.textView_json));
        textView_result_json.setText("");

        Button buttonRoll = (Button) findViewById(R.id.buttonRoll);
        buttonRoll.setOnClickListener(this);

        Button button_test_url = (Button) findViewById(R.id.button_test_url);
        button_test_url.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.button_test_url) {
            Requeteur_html requete = new Requeteur_html();


            try {
                textView_test_url.setText(requete.get("http://5.189.163.64/XIA/scripts/user_request.php"));
            json = requete.get("http://5.189.163.64/XIA/scripts/user_request.php");


            } catch (java.io.IOException e) {
                textView_test_url.setText(e.toString());

            }
            Log.d("test","t");
            Gson gson = new GsonBuilder().create();
            //Produit prod = gson.fromJson(json, Produit.class);
            List<Utilisateur> list_users = gson.fromJson(json,new TypeToken<List<Utilisateur>>() {}.getType());

            //if (prod.Produit_nom==null) {
            //    Log.d("test","null");
           // }
             //   Log.d("test",prod.Produit_nom);
           // Log.d("test",prod.Produit_id.toString());
           textView_result_json.setText(list_users.get(0).nom);

        }
        else {
            SecureRandom random = new SecureRandom();
            int result = random.nextInt(6) + 1;
            textResult.setText(String.valueOf(result));
        }
        }
}