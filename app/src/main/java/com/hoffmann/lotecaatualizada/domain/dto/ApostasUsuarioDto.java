package com.hoffmann.lotecaatualizada.domain.dto;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class ApostasUsuarioDto implements Parcelable {

    private long[] dezenas;
    private boolean selected;

    public ApostasUsuarioDto() {
    }

    public ApostasUsuarioDto(long[] dezenas) {
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

    public static final Creator<ApostasUsuarioDto> CREATOR = new Creator<ApostasUsuarioDto>() {
        @Override
        public ApostasUsuarioDto createFromParcel(Parcel in) {
            return new ApostasUsuarioDto(in);
        }

        @Override
        public ApostasUsuarioDto[] newArray(int size) {
            return new ApostasUsuarioDto[size];
        }
    };

    private ApostasUsuarioDto(Parcel in) {
        dezenas = in.createLongArray();
        selected = in.readByte() != 0;
    }
}
