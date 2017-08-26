package losbibis.xia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by almeric on 10/12/16.
 */

public class Json_interface {

    private String json;
    private String result;


    public ArrayList<Local> update_list (Utilisateur utilisateur, String type) {
        Requeteur_html requete = new Requeteur_html();



        ArrayList<Local> list_object = new ArrayList<Local>();

        try {
            //   textView_test_url.setText(requete.get("http://5.189.163.64/XIA/scripts/user_request.php"));
            json = requete.get("local.php?id=" + utilisateur.famille_id + "&type="+ type);
        }

        catch (java.io.IOException e) {
            result = (e.toString());
        }

        if (json.equals("[]")) {
            result = "wrong_user";
        }
        else {
            Gson gson = new GsonBuilder().create();
            if (type.equals("piece"))
            {
                list_object = gson.fromJson(json, new TypeToken<List<Piece>>() {}.getType());

            }
            if (type.equals("meuble"))
            {
                list_object = gson.fromJson(json, new TypeToken<List<Meuble>>() {}.getType());
            }


        }
        ;
        return list_object;    }
}
