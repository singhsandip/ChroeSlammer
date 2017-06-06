package com.omninos.chroeslammer.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.omninos.chroeslammer.Helper.Constants;
import com.omninos.chroeslammer.R;
import com.squareup.picasso.Picasso;


public class Project_Details extends AppCompatActivity implements View.OnClickListener {

    LayoutInflater li;
    View layout;
    Button request;
    private TextView job_title, name, posted_date, description;
    private Button category;
    private LinearLayout menuLT;
    private ImageView job_image;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project__details);

        li = getLayoutInflater();

        intent = getIntent();

        job_title = (TextView) findViewById(R.id.job_title);
        name = (TextView) findViewById(R.id.job_worker_name);
        posted_date = (TextView) findViewById(R.id.created_date);
        description = (TextView) findViewById(R.id.description);
        category = (Button) findViewById(R.id.job_category);
        job_image = (ImageView) findViewById(R.id.job_image);

        job_title.setText(intent.getStringExtra(Constants.JOBTITLE));
        //Log.d("titl",intent.getStringExtra(Constants.JOBTITLE));
        category.setText(intent.getStringExtra(Constants.CATEGORY));
        Log.d("titl", intent.getStringExtra(Constants.CATEGORY));
        name.setText("By:" + intent.getStringExtra(Constants.USERNAME));
        Log.d("titl", intent.getStringExtra(Constants.USERNAME));
        posted_date.setText(intent.getStringExtra(Constants.CREATED_DATE));
        Log.d("titl", intent.getStringExtra(Constants.CREATED_DATE));
        description.setText(intent.getStringExtra(Constants.DESCRIPTION));
        Log.d("titl", intent.getStringExtra(Constants.DESCRIPTION));
        Picasso.with(this).load(intent.getStringExtra(Constants.IMAGE)).into(job_image);
        Log.d("titl", intent.getStringExtra(Constants.IMAGE));
        request = (Button) findViewById(R.id.request_btn);
        menuLT = (LinearLayout) findViewById(R.id.menuLT);

        request.setOnClickListener(this);
        menuLT.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.menuLT:
                startActivity(new Intent(this, PostList.class));
                finish();
                break;

            case R.id.request_btn:
                //  AlertDialog basic_reg;
               /* AlertDialog.Builder builder ;
                builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
                View view = li.inflate(R.layout.custom_toast_layout, null);
                TextView alertname = (TextView) view.findViewById(R.id.alert_name);
                TextView alertcatgory = (TextView) view.findViewById(R.id.alert_category);
                alertname.setText(intent.getStringExtra(Constants.USERNAME));
                alertcatgory.setText(" "+intent.getStringExtra(Constants.CATEGORY));
                builder.setView(view);
                builder.setCancelable(true);
                builder.create();
                builder.show();*/
                //  basic_reg = builder.show();


                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + intent.getStringExtra(Constants.PHONE)));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);

               /* Intent i=new Intent(Intent.ACTION_DIAL, Uri.parse(intent.getStringExtra(Constants.PHONE)));
                startActivity(i);*/
                break;
        }

    }
}
