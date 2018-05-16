package com.vuthithom.fakecall.fakecall;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vuthithom.fakecall.R;

import java.util.ArrayList;
import java.util.List;

public class AdapTerFakeCall extends BaseAdapter{
    private Context context;
    private int layout;
    private List<Item_FakeCall>list;

    public AdapTerFakeCall(Context context, int layout, List<Item_FakeCall> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class Viewholder{
        TextView tvname,tvphone;
        ImageView ivphoto;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder viewholder;
        if (view==null){
            viewholder=new Viewholder();
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            viewholder.tvname=view.findViewById(R.id.it_name);
            viewholder.tvphone=view.findViewById(R.id.it_phone);
            viewholder.ivphoto=view.findViewById(R.id.it_imphoto);
            view.setTag(viewholder);

        }else {
            viewholder=(Viewholder)view.getTag();
        }
        Item_FakeCall item_fakeCall=list.get(i);
        viewholder.tvname.setText(item_fakeCall.getNamefakecall());
        viewholder.tvphone.setText(item_fakeCall.getPhonefakecall());
        byte[]hinhanh=item_fakeCall.getHinh();
        Bitmap bitmap= BitmapFactory.decodeByteArray(hinhanh,0,hinhanh.length);
        viewholder.ivphoto.setImageBitmap(bitmap);
        return view;
    }
}
