package gawe.imb.karya.model.objects;

/**
 * Created by korneliussendy on 1/27/18.
 */

public class GaweCategory {

    private String cat_descr;
    private String cat_id;
    private String cat_icon;
    private String cat_name;
    private double cat_pengali = 1d;
    private String cat_type;
    private String cat_show = "NONE";
    private String cat_formType;

    public GaweCategory() {
    }

//    public GaweCategory(GaweCategoryNested categoryNested) {
//        this.cat_descr = categoryNested.getDescription();
//        this.cat_id = categoryNested.getId();
//        this.cat_icon = categoryNested.getIcon();
//        this.cat_name = categoryNested.getName();
//        this.cat_pengali = categoryNested.getPengali();
//        this.cat_type = categoryNested.getType();
//        this.cat_show = categoryNested.getShow();
//        this.cat_formType = "";
//    }

    public String getCat_descr() {
        return cat_descr;
    }

    public void setCat_descr(String cat_descr) {
        this.cat_descr = cat_descr;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_icon() {
        return cat_icon;
    }

    public void setCat_icon(String cat_icon) {
        this.cat_icon = cat_icon;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public double getCat_pengali() {
        return cat_pengali;
    }

    public void setCat_pengali(double cat_pengali) {
        this.cat_pengali = cat_pengali;
    }

    public String getCat_type() {
        return cat_type;
    }

    public void setCat_type(String cat_type) {
        this.cat_type = cat_type;
    }

    public String getCat_show() {
        return cat_show;
    }

    public void setCat_show(String cat_show) {
        this.cat_show = cat_show;
    }

    public String getCat_formType() {
        return cat_formType;
    }

    public void setCat_formType(String cat_formType) {
        this.cat_formType = cat_formType;
    }
}
