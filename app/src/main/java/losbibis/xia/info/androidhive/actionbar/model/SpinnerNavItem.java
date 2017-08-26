package losbibis.xia.info.androidhive.actionbar.model;

/**
 * Created by almeric on 04/12/16.
 */

public class SpinnerNavItem {

    private String title;
    private int icon;

    public SpinnerNavItem(String title, int icon){
        this.title = title;
        this.icon = icon;
    }

    public String getTitle(){
        return this.title;
    }

    public int getIcon(){
        return this.icon;
    }

}
