package io.jp.awesomemvp.list;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

class GeneratePIModel implements Parcelable {
    private List<String> PIs = new ArrayList<>();

    public List<String> getPIs() {
        return PIs;
    }

    public void addPI(String pi) {
        this.PIs.add(pi);
    }

    GeneratePIModel() {

    }

    protected GeneratePIModel(Parcel in) {
        in.readStringList(PIs);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(PIs);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GeneratePIModel> CREATOR = new Creator<GeneratePIModel>() {
        @Override
        public GeneratePIModel createFromParcel(Parcel in) {
            return new GeneratePIModel(in);
        }

        @Override
        public GeneratePIModel[] newArray(int size) {
            return new GeneratePIModel[size];
        }
    };
}
