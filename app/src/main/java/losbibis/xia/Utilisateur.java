package losbibis.xia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;


import static losbibis.xia.MainActivity.SERVER;


/**
 * Created by almeric on 26/11/16.
 */

public class Utilisateur {


    private static String json;
    private static String requete;
    private static String result;


    Integer utilisateur_id;
    String nom;
    String prenom;
    String login;
    String mdp;
    Integer famille_id;
    Integer position_id;
    private static final String TAG= "Utilisateur";

public static Utilisateur principal(String login, String mdp) {



    Requeteur_html requete = new Requeteur_html();
Utilisateur utilisateur = new Utilisateur();
    Log.d(TAG, login + " " + mdp);
        try {
            //   textView_test_url.setText(requete.get("http://5.189.163.64/XIA/scripts/user_request.php"));
            json = requete.get(SERVER + "log.php?log=" + login + "&mdp=" + mdp);
        } catch (java.io.IOException e)
            {
                result = (e.toString());
            }
        result = (json);
        if (json.equals("[]")) {
            result = "wrong_user";
            Log.d(TAG, result);
        }
        else {
            Gson gson = new GsonBuilder().create();
            List<Utilisateur> list_users = gson.fromJson(json, new TypeToken<List<Utilisateur>>() {
            }.getType());
            utilisateur =   list_users.get(0);
            Log.d(TAG, utilisateur.nom);
        }
        ;
        return utilisateur;
    };

}