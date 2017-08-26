package losbibis.xia;

import java.util.ArrayList;
import java.util.List;

import static losbibis.xia.simplefabric.TypeUnite.meuble;

/**
 * Created by almeric on 10/12/16.
 */

public class Piece extends Localisation {


    ArrayList<String> list_noms_meubles (ArrayList<Unite> meubles)
    {
        ArrayList<String> list = new ArrayList<String>();
        for (int i= 0; i < meubles.size();i++) {
        list.add(meubles.get(i).nom);

        }
        return list;

    }


            ;


}
