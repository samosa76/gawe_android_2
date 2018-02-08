package gawe.imb.karya.model.objects;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.List;

/**
 * Created by korneliussendy on 2/4/18.
 */

public class GaweCat implements Parcelable {

    private String id;
    private Double pengali;
    private List<GaweCat> children;
    private Integer rangeKm;
    private String icon;
    private String name;
    private String show;
    private String description;
    private String catType;
    private String tags;

    public GaweCat() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPengali(Double pengali) {
        this.pengali = pengali;
    }

    public Double getPengali() {
        return pengali;
    }

    public List<GaweCat> getChildren() {
        return children;
    }

    public void setChildren(List<GaweCat> children) {
        this.children = children;
    }

    public void setRangeKm(Integer rangeKm) {
        this.rangeKm = rangeKm;
    }

    public Integer getRangeKm() {
        return rangeKm;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getShow() {
        return show;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCatType() {
        return catType;
    }

    public void setCatType(String catType) {
        this.catType = catType;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTags() {
        return tags;
    }



    @Override
    public String toString() {
        return
                "Gawecat" +
                        "pengali = '" + pengali + '\'' +
                        ",children = '" + children + '\'' +
                        ",range_km = '" + rangeKm + '\'' +
                        ",icon = '" + icon + '\'' +
                        ",name = '" + name + '\'' +
                        ",show = '" + show + '\'' +
                        ",description = '" + description + '\'' +
                        ",type = '" + type + '\'' +
                        ",tags = '" + tags + '\'' +
                        "}";
    }

    private boolean isExpandable = true;
    private boolean isInitiallyExpanded = false;
    private int dot;
    private int type;
    private String info;

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public void setInitiallyExpanded(boolean initiallyExpanded) {
        isInitiallyExpanded = initiallyExpanded;
    }

    public int getDot() {
        return dot;
    }

    public void setDot(int dot) {
        this.dot = dot;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private GaweCat(Parcel in) {
        isExpandable = in.readByte() != 0;
        isInitiallyExpanded = in.readByte() != 0;
        dot = in.readInt();
        type = in.readInt();
        info = in.readString();
        children = in.createTypedArrayList(GaweCat.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isExpandable ? 1 : 0));
        dest.writeByte((byte) (isInitiallyExpanded ? 1 : 0));
        dest.writeInt(dot);
        dest.writeInt(type);
        dest.writeString(info);
        dest.writeTypedList(children);
    }

    public static final Creator<GaweCat> CREATOR = new Creator<GaweCat>() {
        @Override
        public GaweCat createFromParcel(Parcel in) {
            return new GaweCat(in);
        }

        @Override
        public GaweCat[] newArray(int size) {
            return new GaweCat[size];
        }
    };

}
