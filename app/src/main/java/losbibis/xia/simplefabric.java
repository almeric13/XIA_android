package losbibis.xia;

/**
 * Created by almeric on 11/12/16.
 */

public class simplefabric {
    // La création d'une unité en fonction de son type est encapsulée dans la fabrique.
    public Unite creerUnite(TypeUnite type)
    {
        Unite unite = null;;
        switch(type)
        {
            case piece:unite = new Piece();break;
            case meuble:unite = new Meuble();break;
            case produit:unite = new Produit();break;
            case localisation:unite = new Localisation();break;
        }
        return unite;
    }


// Enumération des types d'unités.
public enum TypeUnite
{
    piece,
    meuble,
    produit,
    localisation
}

}
