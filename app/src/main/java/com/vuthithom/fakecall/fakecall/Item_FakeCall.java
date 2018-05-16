package com.vuthithom.fakecall.fakecall;

import android.graphics.Bitmap;

public class Item_FakeCall {
    private int id;
    private String namefakecall;
    private String phonefakecall;
    private byte[] hinh;

    public Item_FakeCall(int id, String namefakecall, String phonefakecall, byte[] hinh) {
        this.id = id;
        this.namefakecall = namefakecall;
        this.phonefakecall = phonefakecall;
        this.hinh = hinh;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamefakecall() {
        return namefakecall;
    }

    public void setNamefakecall(String namefakecall) {
        this.namefakecall = namefakecall;
    }

    public String getPhonefakecall() {
        return phonefakecall;
    }

    public void setPhonefakecall(String phonefakecall) {
        this.phonefakecall = phonefakecall;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}
