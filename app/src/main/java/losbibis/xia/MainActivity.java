package losbibis.xia;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.security.SecureRandom;
import java.util.List;

import static losbibis.xia.R.id.button_connect;

//import static R.id.textView_test_url;


public class MainActivity extends Activity {

    public static final String PREFS_NAME = "MyPrefsFile";
   // public static String LOGIN = "mdp";
  //  public static String MDP = "mdp";
    public final static String SERVER = "http://5.189.163.64/XIA/scripts/";
    // déclarations des variables d'affichage:
  public   Utilisateur utilisateur_principale;
    Button button_connect;
    EditText login;
    EditText password;
    TextView result;
    ActionBar actionBar;
    // declaration des variables de communication:

    private String json;
    private Gson gson = new GsonBuilder().create();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        // permission network

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

// création des objets graphique textes

        login = (EditText) this.findViewById(R.id.editText_login);
        password = (EditText) this.findViewById(R.id.editText_password);


        result = (TextView) this.findViewById(R.id.textView_result);


        button_connect = (Button) this.findViewById(R.id.button_connect);


        button_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

                // SharedPreferences.Editor editor = preferences.edit();
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();

                editor.putString("LOGIN", String.valueOf(login.getText()));
                editor.putString("MDP", String.valueOf(password.getText()));


                editor.commit();


                Requeteur_html requete = new Requeteur_html();


                try {
                    //   textView_test_url.setText(requete.get("http://5.189.163.64/XIA/scripts/user_request.php"));
                    json = requete.get(SERVER + "log.php?log=" + login.getText() + "&mdp=" + password.getText());
                } catch (java.io.IOException e) {
                    result.setText(e.toString());
                }
                result.setText(json);
                if (json.equals("[]")) {
                    result.setText(R.string.wrong_user);
                } else {
                    Gson gson = new GsonBuilder().create();
                    List<Utilisateur> list_users = gson.fromJson(json, new TypeToken<List<Utilisateur>>() {
                    }.getType());
                    utilisateur_principale = list_users.get(0);
                    result.setText(list_users.get(0).nom);
                    Intent test = new Intent(MainActivity.this, Menu_Principale.class);
                    MainActivity.this.startActivity(test);
                }
            }
        });


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
//        return true;
//   }

    }
}


