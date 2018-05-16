package com.vuthithom.fakecall.fakecall;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.vuthithom.fakecall.activity.MortSettingActivity;
import com.vuthithom.fakecall.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FakeCallFragment extends Fragment {
    private static final int PICK_IMAGE = 14;
    private ImageView selectphone,addvoide,moresetting;
    private static final int REQUEST_CALL = 1;
    private static final int REQUEST_PHOTO = 2;
    private EditText mcallphne, mname;
    private Button btsave;
    private ImageView imgphoto;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fakecall, container, false);
        mcallphne = (EditText) view.findViewById(R.id.edphone);
        mname = (EditText) view.findViewById(R.id.ednamephne);
        btsave = view.findViewById(R.id.btsave);
        imgphoto = view.findViewById(R.id.imgphoto);
        view();
        onclick();
        imgphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PHOTO);
                } else {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(galleryIntent, PICK_IMAGE);
                }

            }
        });

        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable bitmapDrawable=(BitmapDrawable)imgphoto.getDrawable();
                Bitmap bitmap=bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayInputStream=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayInputStream);
                byte[] hinhanh=byteArrayInputStream.toByteArray();
                if(ListFakeCall.databaseHelper==null) ListFakeCall.databaseHelper = new DatabaseHelper(getContext(),"Call.sqlite",null,1);
                ListFakeCall.databaseHelper.INSERT_CALL(
                        mname.getText().toString().trim(),
                        mcallphne.getText().toString().trim(),
                        hinhanh
                );
                startActivity(new Intent(getActivity(),ListFakeCall.class));
                makephoneCall();
            }
        });
        return view;
    }

    private void makephoneCall() {
        String number = mcallphne.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));

            }
        } else {
            Toast.makeText(getActivity(), "enter", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CALL:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makephoneCall();
                } else {
                    Toast.makeText(getActivity(), "Permission DENIED", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    public void onclick(){
        selectphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        addvoide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        moresetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent=new Intent(getActivity(),MortSettingActivity.class);
            startActivity(intent);
            }
        });
    }

    public void view(){
        selectphone=(ImageView)view.findViewById(R.id.im_selectphone);
        addvoide=(ImageView)view.findViewById(R.id.im_addvoice);
        moresetting=(ImageView)view.findViewById(R.id.im_moreSetting);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE  && data !=null){
            Uri contentURI = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                imgphoto.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

