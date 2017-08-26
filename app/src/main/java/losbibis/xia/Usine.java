package losbibis.xia;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import static losbibis.xia.MainActivity.SERVER;



/**
 * Created by almeric on 11/12/16.
 */

    // Classe usine qui représente un bâtiment capable de construire des unités.
    public class Usine
    {
        private static final String TAG= "Usine";
        private String json;
        private String result;

        private simplefabric simpleFabrique;// Attribut contenant la fabrique simple.

        // Le constructeur permet de sélectionner la fabrique à utiliser.
        public Usine()
        {
            this.simpleFabrique = new simplefabric();
        }

        // Méthode qui permet de construire l'ensemble des unités.
        public Unite formerUnite(simplefabric.TypeUnite type)
        {
            Unite unite = this.simpleFabrique.creerUnite(type);
           return unite;
        }

        // liste sans depandance et sans sous_type

        public ArrayList<Unite> formerlistunit (simplefabric.TypeUnite type, Utilisateur utilisateur) {
            ArrayList<Unite> list_unite = new ArrayList<Unite>();
            list_unite = formerlistunit(type,"sans", utilisateur,0);
            return list_unite;
        }


        //  liste sans depandance

        public ArrayList<Unite> formerlistunit (simplefabric.TypeUnite type, String sous_type, Utilisateur utilisateur) {
            ArrayList<Unite> list_unite = new ArrayList<Unite>();
        list_unite = formerlistunit(type,sous_type, utilisateur,0);
        return list_unite;
        }


    public ArrayList<Unite> formerlistunit (simplefabric.TypeUnite type, String sous_type, Utilisateur utilisateur,Integer id_pere)
    {
        Requeteur_html requete = new Requeteur_html();


        String type_bdd = String.valueOf(type);
        ArrayList<Unite> list_unite = new ArrayList<Unite>();

        try {
            //   textView_test_url.setText(requete.get("http://5.189.163.64/XIA/scripts/user_request.php"));

            if (sous_type.equals("sans") && id_pere == 0)

                 {
                json = requete.get(SERVER + "find_list.php?famille_id=" + utilisateur.famille_id + "&type=" + type + "&type=" + sous_type  );
                 }
            else {
                    if (id_pere== 0)
                    {
                        json = requete.get(SERVER + "find_list_sous_type.php?famille_id=" + utilisateur.famille_id + "&type=" + type + "&sous_type=" + sous_type  );
                    }
                    else
                    {
                        json = requete.get(SERVER + "find_list_depandance.php?famille_id=" + utilisateur.famille_id + "&type=" + type + "&sous_type=" + sous_type +"&id_pere=" + id_pere );
                    }
                Log.d(TAG, Integer.toString(id_pere));

            }
        //    Log.d(TAG, SERVER + "find_list_depandance.php?famille_id=" + utilisateur.famille_id + "&type=" + type + "&sous_type=" + sous_type +"&id_pere=" + id_pere );
        //    Log.d(TAG,json);
        }

        catch (java.io.IOException e) {
            result = (e.toString());
        }

        if (json.equals("[]")) {
            result = "wrong_user";
        }
        else {
            Gson gson = new GsonBuilder().create();
            Log.d(TAG, String.valueOf(type));
            if (String.valueOf(type).equals("localisation") && sous_type.equals("piece"))
            {
                list_unite = gson.fromJson(json, new TypeToken<List<Piece>>() {}.getType());
                Log.d(TAG, "piece initialise");
                Log.d(TAG, Integer.toString(list_unite.size()));
                Log.d(TAG,json);
            }

            if (String.valueOf(type).equals("localisation") && !sous_type.equals("piece"))
            {

                list_unite = gson.fromJson(json, new TypeToken<List<Localisation>>() {}.getType());
                Log.d(TAG, "localisation initialise");
                Log.d(TAG, Integer.toString(list_unite.size()));

            }
            if (String.valueOf(type).equals("PRODUIT"))
            {
                list_unite = gson.fromJson(json, new TypeToken<List<Produit>>() {}.getType());
            }

        }
        ;
        Log.d(TAG, "unite cree" );
        return list_unite;
    }

    }





