package com.omninos.chroeslammer.Activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.omninos.chroeslammer.Helper.Retrofit_Instance;
import com.omninos.chroeslammer.Helper.SharedPrefManager;
import com.omninos.chroeslammer.Interface.Post_Chore_Api;
import com.omninos.chroeslammer.Models.PostCoreResponseModel;
import com.omninos.chroeslammer.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class PostChoreActivity extends AppCompatActivity implements View.OnClickListener {



    private LinearLayout submit, add_image, menuLT;
    Intent intent,camera_gallery_intent;
    String type,imagePath;
    private EditText category_et, title_et, description_et, location_et, budget_et;
    ImageView add_image_imgview,menu;
    private DrawerLayout drawerLayout;
    private Uri uri;
    private  int CAMERA_REQUEST = 1;
    private  int GALLERY_REQUEST = 2;
    private ProgressDialog progressDialog;
    private Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_chore);

        intent = getIntent();
        type = intent.getStringExtra("type");
        add_image_imgview = (ImageView) findViewById(R.id.add_image_img_view);
        submit = (LinearLayout) findViewById(R.id.loginLT);
        menuLT = (LinearLayout) findViewById(R.id.menuLT);
        drawerLayout = (DrawerLayout) findViewById(R.id.drwaer_layout);
        add_image = (LinearLayout) findViewById(R.id.add_image);

        menu = (ImageView)findViewById(R.id.menu);

        menu.setVisibility(View.GONE);

        category_et = (EditText) findViewById(R.id.category_et);
        title_et = (EditText) findViewById(R.id.title_et);
        location_et = (EditText) findViewById(R.id.location_et);
        description_et = (EditText) findViewById(R.id.description_et);
        budget_et = (EditText) findViewById(R.id.budget_et);

        category_et.setText(type);


        progressDialog = new ProgressDialog(this);

        menuLT.setOnClickListener(this);
        submit.setOnClickListener(this);
        add_image.setOnClickListener(this);
        location_et.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menuLT:
                drawerLayout.openDrawer(GravityCompat.START);
                break;

            case R.id.loginLT:
                


                if (bitmap == null)
                {
                    Toast.makeText(this, "Please Add Image", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String user_id = SharedPrefManager.getInstance(this).getUserId();
                    String category = category_et.getText().toString();
                    String tittle = title_et.getText().toString();
                    String location = location_et.getText().toString();
                    String description = description_et.getText().toString();
                    String budget = budget_et.getText().toString();

                    TypedFile image = new TypedFile("multipart/form-data", new File(imagePath));


                    if (category.equalsIgnoreCase(""))
                    {
                        category_et.setError("");
                    }
                    else if(tittle.equalsIgnoreCase(""))
                    {
                        title_et.setError("Enter tittle");
                    }
                    else if(description.equalsIgnoreCase(""))
                    {
                        description_et.setError("Enter Job Description ");
                    }
                    else if(location.equalsIgnoreCase(""))
                    {
                        location_et.setError("Enter your location");
                    }

                    else if(budget.equalsIgnoreCase(""))
                    {
                        budget_et.setError("Enter Budget");
                    }
                    else
                    {
                        progressDialog.setMessage("Submitting...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                        postData(user_id,tittle,description,location,image,budget,category);
                    }


                }


                break;

            case R.id.add_image:


                startDilog();

               /* AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
                alertdialog.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 1);

                    }
                });

                alertdialog.setNegativeButton("gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image*//*");
                        startActivityForResult(intent, 2);

                    }
                });

                alertdialog.show();*/
                break;

            case R.id.location_et:

                break;


        }

    }

    private void postData(String user_id, String tittle, String description, String location, TypedFile image, String budget, String category)
    {

        final Post_Chore_Api post_chore_api = Retrofit_Instance.getRetrofit().create(Post_Chore_Api.class);
        post_chore_api.postChore(user_id, tittle, description, location, image
                , budget, category, new Callback<PostCoreResponseModel>() {

                    @Override
                    public void success(PostCoreResponseModel postCoreResponseModel, Response response) {

                        if (response != null)
                        {
                            category_et.setText(null);
                            title_et.setText(null);
                            description_et.setText(null);
                            location_et.setText(null);
                            budget_et.setText(null);
                            progressDialog.dismiss();
                            String su = postCoreResponseModel.getSuccess();
                            String message = postCoreResponseModel.getMessage();
                            Toast.makeText(PostChoreActivity.this, ""+message, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(PostChoreActivity.this,MainActivity.class));
                            finish();

                        }
                        else
                        {
                            Toast.makeText(PostChoreActivity.this, "no response", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                       // Log.e("my retrofit errot", String.valueOf(error));

                        progressDialog.dismiss();
                        Toast.makeText(PostChoreActivity.this, "failed", Toast.LENGTH_SHORT).show();


                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == MainActivity.RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            uri = getImageUri(PostChoreActivity.this, bitmap);
            Log.d("uri",String.valueOf(uri));
          //  File finalFile = new File();

            imagePath = getRealPathFromUri(uri);
            add_image_imgview.setImageBitmap(bitmap);
        }
        if (requestCode == GALLERY_REQUEST && resultCode == MainActivity.RESULT_OK) {
            final Uri uri = data.getData();

            //Bitmap new_bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                imagePath = getRealPathFromUri(uri);

            } catch (IOException e) {
                e.printStackTrace();
            }
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

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private Uri getImageUri(PostChoreActivity youractivity, Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
        return Uri.parse(path);
    }

    // Function for open Camera
    private void startDilog() {
        AlertDialog.Builder myAlertDilog = new AlertDialog.Builder(PostChoreActivity.this);
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


}
