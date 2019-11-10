package com.example.myappires;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;
    private Button mButtonDo;
    private TextView mTextView;
    private String JSONURLpost = "http://52.168.76.48/estudianteapi/read.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the application context
        mContext = getApplicationContext();
        mActivity = MainActivity.this;

        // Get the widget reference from XML layout
        mButtonDo = (Button) findViewById(R.id.btn_do);
        mTextView = (TextView) findViewById(R.id.tv);

        // Set a click listener for button widget
        mButtonDo.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                // Empty the TextView
                mTextView.setText("");

                // Initialize a new RequestQueue instance
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                JsonArrayRequest request = new JsonArrayRequest(JSONURLpost, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject objestudent = jsonArray.getJSONObject(i);
                                String ident = objestudent.getString("identificacion");
                                String nomb = objestudent.getString("nombre");
                                String cur = objestudent.getString("curso");
                                String nuno = objestudent.getString("nota1");
                                String ndos = objestudent.getString("nota2");
                                String ntres = objestudent.getString("nota3");
                                mTextView.append(ident + " " + nomb + " " + cur
                                        + " " + nuno + " " + ndos + " " + ntres+ "\n");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                });
                requestQueue.add(request);
            }
        });
    }
}
