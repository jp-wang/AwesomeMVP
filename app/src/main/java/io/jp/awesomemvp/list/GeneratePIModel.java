package io.jp.awesomemvp.list;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

class GeneratePIModel implements Parcelable {
    private List<String> PIs = new ArrayList<>();
    private long seed = 0L;

    public List<String> getPIs() {
        return PIs;
    }

    public void addPI(String pi) {
        this.PIs.add(pi);
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    GeneratePIModel() {

    }

    protected GeneratePIModel(Parcel in) {
        in.readStringList(PIs);
        this.seed = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(PIs);
        dest.writeLong(this.seed);
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
