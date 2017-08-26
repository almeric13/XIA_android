package losbibis.xia;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import static losbibis.xia.MainActivity.PREFS_NAME;
import static losbibis.xia.MainActivity.SERVER;

/**
 * Created by almeric on 05/12/16.
 */

public class Modification_item extends Menu_Principale {

    private static final String TAG= "Modification_item";
    String login ;
    String json;
    String mdp;
    List<Unite> list_pieces;
    Piece piece_test;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;

    List<String> list_meubles_nom;
    HashMap<String, List<String>> listDataChild;

    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        login = settings.getString("LOGIN", "log");
        mdp = settings.getString("MDP", "log");

        Log.d(TAG, login + mdp);


        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_modification);
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data



        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);


// Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });


// Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });




    }
    /*
     * Preparing the list data
     */
    private void prepareListData() {

    //    list_pieces = piece.list_pieces(Utilisateur.principal());
        Utilisateur utilisateur = Utilisateur.principal(login,mdp);
        Usine usine = new Usine();
        list_pieces = usine.formerlistunit(simplefabric.TypeUnite.localisation,"piece",utilisateur);

        Log.d(TAG, Integer.toString(list_pieces.size()));

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

    //    list_meubles_nom = new ArrayList<String>();

for (int i = 0 ; i < list_pieces.size();i++ ) {

    listDataHeader.add(list_pieces.get(i).nom);
    piece_test = (Piece) list_pieces.get(i);

    // Adding child data

 //   list_meubles_nom =  new ArrayList<>(piece_test.list_noms_meubles(usine.formerlistunit(simplefabric.TypeUnite.localisation,"meuble",utilisateur,list_pieces.get(i).id)));

    listDataChild.put(listDataHeader.get(i), piece_test.list_noms_meubles(usine.formerlistunit(simplefabric.TypeUnite.localisation,"meuble",utilisateur,((Piece) list_pieces.get(i)).localisation_id)));
    Log.d(TAG, list_pieces.get(i).nom);
    Log.d(TAG, Integer.toString(((Piece) list_pieces.get(i)).localisation_id));
      }
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                local_add();
                return true;
            //       case R.id.action_delete:
            //           delete();
            //          return true;
            //       case R.id.action_settings:
            //           return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void local_add() {
        Toast.makeText(getApplicationContext(),
                " Ajout",
                Toast.LENGTH_SHORT).show();

        // TODO Auto-generated method stub
        //On instancie notre layout en tant que View


        LayoutInflater factory = LayoutInflater.from(this);
        final View alertDialogView = factory.inflate(R.layout.dialogajoutxml, null);

        //Création de l'AlertDialog
        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        //On affecte la vue personnalisé que l'on a crée à notre AlertDialog
        adb.setView(alertDialogView);

        //On donne un titre à l'AlertDialog
        adb.setTitle("Ajout d'un Local");

        //On modifie l'icône de l'AlertDialog pour le fun ;)
        adb.setIcon(android.R.drawable.ic_dialog_alert);


        final EditText valsaisie =  (EditText)alertDialogView.findViewById(R.id.EditTextajout);;
        valsaisie.setVisibility(View.GONE);
        final Spinner spinner = (Spinner)alertDialogView.findViewById(R.id.spinner);

        List exempleList = new ArrayList();
        exempleList.add("Piece");
        exempleList.add("Meuble");

        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                exempleList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
// liste deroulante des pieces
        final Spinner spinner2 = (Spinner)alertDialogView.findViewById(R.id.spinner2);

        List piecelist = new ArrayList();
        for (int  i = 0 ; i < list_pieces.size();i++ ) {
        piecelist.add(list_pieces.get(i).nom);

        }
        ArrayAdapter adapter2 = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                piecelist
        );


        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setVisibility(View.GONE);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if (spinner.getSelectedItem().toString().equals("Meuble"))
                {
                valsaisie.setVisibility(View.VISIBLE);
                    spinner2.setVisibility(View.VISIBLE);
                }
                if (spinner.getSelectedItem().toString().equals("Piece"))
                {
                    valsaisie.setVisibility(View.VISIBLE);
                    spinner2.setVisibility(View.GONE);
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        //On affecte un bouton "OK" à notre AlertDialog et on lui affecte un évènement
        adb.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                //Lorsque l'on cliquera sur le bouton "OK", on récupère l'EditText correspondant à notre vue personnalisée (cad à alertDialogView)
                Requeteur_html requete = new Requeteur_html();
        //        EditText valsaisie = (EditText)alertDialogView.findViewById(R.id.EditTextajout);
                if (spinner.getSelectedItem().toString().equals("Meuble"))
                {
                    Utilisateur utilisateur = Utilisateur.principal(login,mdp);
                    try {
                        json = requete.get(SERVER + "add.php?famille_id=" + utilisateur.famille_id + "&xia_version" + getString(R.string.version) + "&type=" + "localisation" + "&sous_type=" + "piece");
                    }
                    catch (java.io.IOException e){
                         String   result = (e.toString());
                        }

                }
                if (spinner.getSelectedItem().toString().equals("Piece"))
                {

                }


                Log.i("","nouvelle valeur recuperere"+valsaisie);
                //On affiche dans un Toast le texte contenu dans l'EditText de notre AlertDialog
                //Toast.makeText(this, valsaisie.getText(), Toast.LENGTH_SHORT).show();
            } });

        //On crée un bouton "Annuler" à notre AlertDialog et on lui affecte un évènement
        adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Lorsque l'on cliquera sur annuler on quittera l'application
                //finish();
            } });

        adb.show();

    }



}

