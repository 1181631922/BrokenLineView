package com.fanyafeng.brokenlineview.view;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author： fanyafeng
 * Data： 17/2/5 下午5:58
 * Email: fanyafeng@live.cn
 */
public class BrokenLinePointBean implements Parcelable {
    //按照理论来说x值基本上没有用
    public float x;
    //范围[0,1]，最好不要取值0或者1这种极值
    public float y;

    public BrokenLinePointBean(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public BrokenLinePointBean() {
    }

    protected BrokenLinePointBean(Parcel in) {
        x = in.readFloat();
        y = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(x);
        dest.writeFloat(y);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BrokenLinePointBean> CREATOR = new Creator<BrokenLinePointBean>() {
        @Override
        public BrokenLinePointBean createFromParcel(Parcel in) {
            return new BrokenLinePointBean(in);
        }

        @Override
        public BrokenLinePointBean[] newArray(int size) {
            return new BrokenLinePointBean[size];
        }
    };

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "BrokenLinePointBean{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
