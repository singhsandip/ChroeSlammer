package com.omninos.chroeslammer.Activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.omninos.chroeslammer.Helper.Constants;
import com.omninos.chroeslammer.Helper.Retrofit_Instance;
import com.omninos.chroeslammer.Helper.SharedPrefManager;
import com.omninos.chroeslammer.Interface.Get_Profile_Api;
import com.omninos.chroeslammer.Models.UpdateProfileModel;
import com.omninos.chroeslammer.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;


public class Update_Profile_Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView add_image_tv;
    private LinearLayout update, menuLT,add_image_ll;
    private  int CAMERA_REQUEST = 1;
    private  int GALLERY_REQUEST = 2;
    private String type,imagePath,username,cateogoty,address,second_category,image;
    Intent intent,camera_gallery_intent,intentprofile;
    private Bitmap bitmap = null;
    private Uri uri;
    private ImageView add_image_imgview;
    private ProgressDialog progressDialog;
    private EditText category_et, secondcatogery_et, username_et, address_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile_);

        username_et = (EditText) findViewById(R.id.username_et);

        intent = getIntent();

        username = intent.getStringExtra(Constants.USERNAME);
        image = intent.getStringExtra(Constants.IMAGE);

        username_et.setText(username);





        progressDialog = new ProgressDialog(this);

        add_image_tv = (TextView)findViewById(R.id.add_image_tv);
        add_image_imgview = (ImageView) findViewById(R.id.add_image_img_view);

        category_et = (EditText) findViewById(R.id.category_et);

        cateogoty = intent.getStringExtra(Constants.CATEGORY);

        category_et.setText(cateogoty);



        secondcatogery_et = (EditText) findViewById(R.id.second_catogery_et);

        second_category = intent.getStringExtra(Constants.SECOND_CATEGORY);

        secondcatogery_et.setText(second_category);

        address_et = (EditText) findViewById(R.id.address_et);

        address = intent.getStringExtra(Constants.ADDRESS);

        address_et.setText(address);
        update = (LinearLayout) findViewById(R.id.updateLT);
        menuLT = (LinearLayout) findViewById(R.id.menuLT);
        add_image_ll = (LinearLayout) findViewById(R.id.add_image);



        add_image_ll.setOnClickListener(this);
        update.setOnClickListener(this);
        menuLT.setOnClickListener(this);


    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.menuLT:
                Intent intent = new Intent(this,Profile.class);
                intent.putExtra(Constants.USERNAME,username);
                intent.putExtra(Constants.IMAGE,image);
                intent.putExtra(Constants.ADDRESS,address);
                intent.putExtra(Constants.CATEGORY,cateogoty);
                intent.putExtra(Constants.SECOND_CATEGORY,second_category);
                startActivity(intent);
                break;

            case R.id.add_image:
                showDialog();
                break;

            case R.id.updateLT:
                validate();
                break;
        }

    }

    private void validate() {

        if (bitmap == null)
        {
            Toast.makeText(this, "Please Add Image", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String user_id = SharedPrefManager.getInstance(this).getUserId();
            String category = category_et.getText().toString();
            String username = username_et.getText().toString();
            String address = address_et.getText().toString();
            String second_catogery = secondcatogery_et.getText().toString();

            TypedFile image = new TypedFile("multipart/form-data", new File(imagePath));


            if (username.equalsIgnoreCase(""))
            {
                username_et.setError("Enter Name Please");
            }
            else if(category.equalsIgnoreCase(""))
            {
                category_et.setError("Enter tittle");
            }
            else if(address.equalsIgnoreCase(""))
            {
                address_et.setError("Enter Your Address");
            }
            else if(second_catogery.equalsIgnoreCase(""))
            {
                secondcatogery_et.setError("Enter your work type");
            }

            else
            {
                progressDialog.setMessage("Updating.......");
                progressDialog.setCancelable(false);
                progressDialog.show();

                updateData(user_id,username,category,address,second_catogery,image);
            }


        }

    }

    private void updateData(String user_id, String username, String category, String address, String second_catogery, TypedFile image)
    {

        Get_Profile_Api get_profile_api = Retrofit_Instance.getRetrofit().create(Get_Profile_Api.class);
        get_profile_api.updateProflie(user_id, image, username, category, address, second_catogery, new Callback<UpdateProfileModel>() {
            @Override
            public void success(UpdateProfileModel updateProfileModel, Response response)
            {
                progressDialog.dismiss();
                if ((updateProfileModel.getSuccess()).matches("1"))
                {
                    intentprofile = new Intent(Update_Profile_Activity.this,Profile.class);
                    String image = updateProfileModel.getImage();
                    Log.e("IMageeeeeee",image);
                    Log.e("Userrrnameee",updateProfileModel.getUsername());
                    Log.e("categoryyyyyy",updateProfileModel.getCategory());
                    Log.e("second category",updateProfileModel.getCategory());
                    Log.e("addresssss",updateProfileModel.getAddress());
                    intentprofile.putExtra(Constants.USERNAME,updateProfileModel.getUsername());
                    intentprofile.putExtra(Constants.IMAGE,image);
                    intentprofile.putExtra(Constants.CATEGORY,updateProfileModel.getCategory());
                    intentprofile.putExtra(Constants.SECOND_CATEGORY,updateProfileModel.getSecondCategory());
                    intentprofile.putExtra(Constants.ADDRESS,updateProfileModel.getAddress());

                    Toast.makeText(Update_Profile_Activity.this, ""+updateProfileModel.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    startActivity(intentprofile);
                }

            }

            @Override
            public void failure(RetrofitError error)
            {
                progressDialog.dismiss();
                Toast.makeText(Update_Profile_Activity.this, "failed to upadate", Toast.LENGTH_SHORT).show();
                Log.e("errorrrrrrr", String.valueOf(error));
            }
        });
    }

    private void showDialog()
    {
        AlertDialog.Builder myAlertDilog = new AlertDialog.Builder(Update_Profile_Activity.this);
        myAlertDilog.setTitle("Upload picture option..");
        myAlertDilog.setMessage("Upload picture from");
        myAlertDilog.setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                camera_gallery_intent = new Intent(Intent.ACTION_GET_CONTENT, null);
                camera_gallery_intent.setType("image/*");
                camera_gallery_intent.putExtra("return_data", true);
                startActivityForResult(camera_gallery_intent, GALLERY_REQUEST);
            }
        });
        myAlertDilog.setNegativeButton("Camera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                camera_gallery_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_gallery_intent, CAMERA_REQUEST);
            }
        });
        myAlertDilog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == MainActivity.RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            uri = getImageUri(Update_Profile_Activity.this, bitmap);
           // Log.d("uri",String.valueOf(uri));
            //  File finalFile = new File();

            imagePath = getRealPathFromUri(uri);

            add_image_tv.setVisibility(View.GONE);

            add_image_imgview.setImageBitmap(bitmap);
        }
        if (requestCode == GALLERY_REQUEST && resultCode == MainActivity.RESULT_OK) {
            final Uri uri = data.getData();

            //Bitmap new_bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                imagePath = getRealPathFromUri(uri);

            } catch (IOException e)
            {
                e.printStackTrace();
            }

            add_image_tv.setVisibility(View.GONE);
            add_image_imgview.setImageBitmap(bitmap);


        }
    }

    private String getRealPathFromUri(Uri uri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = this.getContentResolver().query(uri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

    }

    private Uri getImageUri(Update_Profile_Activity youractivity, Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
        return Uri.parse(path);
    }
}
