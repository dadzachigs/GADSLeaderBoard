package com.darlington.chigumbu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.darlington.chigumbu.googleform.Constants;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;


public class GoogleForm extends AppCompatActivity {




        ProgressDialog progressDialog;

        EditText edtName, edSurname, edEmail, edGitlink;
        ImageView submit;
        ImageButton back;
        RequestQueue queue;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_google_form);


            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading...");


            edtName =  findViewById(R.id.edtName);
            edSurname =  findViewById(R.id.edtSurname);
            edEmail =  findViewById(R.id.edtEmail);
            edGitlink =  findViewById(R.id.github_link);
            back =  findViewById(R.id.imageButton);
            submit= findViewById(R.id.imageView_submit_form);

            // Initializing Queue for Volley
            queue = Volley.newRequestQueue(getApplicationContext());
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GoogleForm.this, MainActivity.class);
                    startActivity(intent);
                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (edtName.getText().toString().trim().length() > 0 && edSurname.getText().toString().trim().length()>0 && edEmail.getText().toString().trim().length() > 0 && edGitlink.getText().toString().trim().length()> 0) {
                        postData(edtName.getText().toString().trim(), edSurname.getText().toString().trim(),edEmail.getText().toString().trim(),edGitlink.getText().toString().trim());
                    } else {
                       Snackbar.make(view, "Required Fields Missing", Snackbar.LENGTH_LONG).show();
                    }
                }
            });

        }


        public void postData( final String address, final String firstName,final String surname,final String projectLink) {

            progressDialog.show();
            StringRequest request = new StringRequest(
                    Request.Method.POST,
                    Constants.url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("TAG", "Response: " + response);
                            if (response.length() > 0) {
                               Snackbar.make(submit, "Successfully Posted", Snackbar.LENGTH_LONG).show();
                                edEmail.setText(null);
                                edtName.setText(null);
                                edSurname.setText(null);
                                edGitlink.setText(null);
                            } else {
                                Snackbar.make(submit, "Try Again", Snackbar.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                  Snackbar.make(submit, "Error while Posting Data", Snackbar.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put(Constants.address, address);
                    params.put(Constants.firstName, firstName);
                    params.put(Constants.surname, surname);
                    params.put(Constants.projectLink, projectLink);
                    return params;
                }
            };
            request.setRetryPolicy(new DefaultRetryPolicy(
                    50000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(request);
        }

    }
