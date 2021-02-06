package com.example.test.onboardingscreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import home.social_media;

public class AddPost extends AppCompatActivity {

    private static final int ADD_POST = 1;
    private ImageView image;
    private Button post;
    private FirebaseAuth auth;
    private EditText caption;
    private Uri uri;
    private long count,gcount;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        auth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);
        pd.setMessage("Wait....");
        pd.setCanceledOnTouchOutside(false);
        initViews();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(i,"Select Pic"),ADD_POST);
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                pd.show();
                if(uri != null)
                {
                    UploadPost(uri);
                }
                else
                    {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(),"Try Again",Toast.LENGTH_SHORT).show();
                        finish();
                    }
            }
        });

    }

    private void UploadPost(final Uri uri) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(auth.getUid())
                .child("Posts");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 count = snapshot.getChildrenCount()+1;
                StorageReference sref = FirebaseStorage.getInstance().getReference()
                        .child("User")
                        .child(auth.getUid())
                        .child("Post")
                        .child("image"+count);

                sref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                        while(!uri.isComplete());
                        Uri url = uri.getResult();
                        Log.e("url",url.toString());
                        UploadToDb(url);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("UploadError",e.getMessage());
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

    }

    private void UploadToDb(final Uri url) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(auth.getUid())
                .child("Posts")
                .child("Post"+count);
        ref.child("ImageUrl").setValue(url.toString());
        if(!caption.getText().toString().isEmpty())
        {
            ref.child("Caption").setValue(caption.getText().toString().trim());
        }

        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference().child("GlobalPosts");
        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gcount = snapshot.getChildrenCount()+1;
                DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference()
                        .child("GlobalPosts")
                        .child("Post"+gcount);
                ref3.child("ImageUrl").setValue(url.toString());
                if(!caption.getText().toString().isEmpty())
                {
                    ref3.child("Caption").setValue(caption.getText().toString().trim());
                }
                pd.dismiss();
                startActivity(new Intent(AddPost.this, social_media.class));
                finish();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

    }

    private void initViews() {
        image = findViewById(R.id.add_image);
        post = findViewById(R.id.post_button);
        caption = findViewById(R.id.caption);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_POST && resultCode == RESULT_OK && data!=null && data.getData()!= null)
        {
            uri = data.getData();
            SetPic(uri);
        }
    }

    private void SetPic(Uri uri) {
        Picasso.get().load(uri).fit().into(image);
    }

}