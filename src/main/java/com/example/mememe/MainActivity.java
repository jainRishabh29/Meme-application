package com.example.mememe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
 ImageView meme = findViewById(R.id.meme);
    private Context  a = this;
//    public void AlbumAdapter(Context context){
//        mContext = context;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   //     loadMeme();
    }
    private void loadMeme(){
        Log.d("loadMeme", "loadMeme: hello");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://meme-api.herokuapp.com/gimme";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("finder", "onResponse: in the onResponse");
                        String memeUrl = null;
                        try {
                            memeUrl = response.getString("url");
                            Log.d("memeViewer", "onResponse: url ");
                            Glide.with(a).load(memeUrl).into(meme);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("error", "onResponse: fail ");
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("responseError", "onErrorResponse: ");
            }
        });
        queue.add(jsonObjectRequest);
    }

    public void next(View view) {
        loadMeme();
    }

    public void share(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
          intent.putExtra("key", "send this meme...");
        startActivity(intent);
    }
}