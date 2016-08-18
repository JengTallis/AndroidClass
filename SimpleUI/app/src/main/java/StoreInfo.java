import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseObject;

/**
 * Created by alog1024 on 8/18/16.
 */
public class StoreInfo extends ParseObject implements Parcelable {

    static final String STORENAME_COL = "storeName";
    static final String STOREADDRESS_COL = "storeAddress";

    public String getStorenameCol() {
        return STORENAME_COL;
    }

    public void setStorenameCol(){

    }

    public String getStoreaddressCol() {
        return STOREADDRESS_COL;
    }

    public void setStoreaddressCol(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected StoreInfo(Parcel in) {
    }

    public static final Creator<StoreInfo> CREATOR = new Creator<StoreInfo>() {
        @Override
        public StoreInfo createFromParcel(Parcel in) {
            return new StoreInfo(in);
        }

        @Override
        public StoreInfo[] newArray(int size) {
            return new StoreInfo[size];
        }
    };

}
