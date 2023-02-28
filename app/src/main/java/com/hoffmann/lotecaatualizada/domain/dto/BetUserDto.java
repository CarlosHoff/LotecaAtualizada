package com.hoffmann.lotecaatualizada.domain.dto;

import android.os.Parcel;
import android.os.Parcelable;

public class BetUserDto implements Parcelable {

    private long[] dezenas;
    private boolean selected;

    public BetUserDto() {
    }

    public BetUserDto(long[] dezenas) {
        this.dezenas = dezenas;
    }

    public long[] getDezenas() {
        return dezenas;
    }

    public void setDezenas(long[] dezenas) {
        this.dezenas = dezenas;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLongArray(dezenas);
        dest.writeByte((byte) (selected ? 1 : 0));
    }

    public static final Creator<BetUserDto> CREATOR = new Creator<BetUserDto>() {
        @Override
        public BetUserDto createFromParcel(Parcel in) {
            return new BetUserDto(in);
        }

        @Override
        public BetUserDto[] newArray(int size) {
            return new BetUserDto[size];
        }
    };

    private BetUserDto(Parcel in) {
        dezenas = in.createLongArray();
        selected = in.readByte() != 0;
    }
}
