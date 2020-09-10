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
import com.darlington.chigumbu.SkillsIQ.Skills;
import com.darlington.chigumbu.SkillsIQ.SkillsIQAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class IQLeaders extends Fragment {


    String url = "https://gadsapi.herokuapp.com/api/skilliq";
    RecyclerView recyclerView;
    SkillsIQAdapter adaptor;
    private Context context;

    ArrayList<Skills> mSkillsArrayList;

    public IQLeaders() {
        // Required empty public constructor
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        adaptor = new SkillsIQAdapter(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_final);

        recyclerView.setHasFixedSize(true);
        //recyclerView.setAdapter(questionAdapter);
        LinearLayoutManager setLayot = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(setLayot);

        adaptor = new SkillsIQAdapter(context);
        recyclerView.setAdapter(adaptor);
        mSkillsArrayList = new ArrayList<>();
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

                        Skills skills = new Skills();
                        skills.setImage(jsonObject.getString("badgeUrl"));
                        skills.setName(jsonObject.getString("name"));
                        skills.setScore(jsonObject.getString("score"));
                        skills.setCountry(jsonObject.getString("country"));
                        mSkillsArrayList.add(skills);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adaptor.setData(mSkillsArrayList);
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