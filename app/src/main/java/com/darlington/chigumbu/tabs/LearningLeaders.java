package com.darlington.chigumbu.tabs;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.darlington.chigumbu.Leaders.Leaders;
import com.darlington.chigumbu.Leaders.LeadersAdapter;
import com.darlington.chigumbu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class LearningLeaders extends Fragment {

    String url = "https://gadsapi.herokuapp.com/api/hours";
    RecyclerView recyclerView;
    LeadersAdapter adaptor;
    private Context context;

    ArrayList<Leaders> mLeadersArrayList;

    public LearningLeaders() {
        // Required empty public constructor
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        adaptor = new LeadersAdapter(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);
       recyclerView = view. findViewById(R.id.recycler_view_final);

        recyclerView.setHasFixedSize(true);
        //recyclerView.setAdapter(questionAdapter);
        LinearLayoutManager setLayot= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(setLayot);

        adaptor = new LeadersAdapter(context);
        recyclerView.setAdapter(adaptor);
        mLeadersArrayList = new ArrayList<>();
        getData();

        return view;
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Leaders leader = new Leaders();
                        leader.setImage(jsonObject.getString("badgeUrl"));
                        leader.setName(jsonObject.getString("name"));
                        leader.setHours(jsonObject.getString("hours"));
                        leader.setCountry(jsonObject.getString("country"));
                        mLeadersArrayList.add(leader);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adaptor.setData(mLeadersArrayList);
                adaptor.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

}

