package com.nacho.tame.frases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.ResourceBundle;

public class MainActivity extends AppCompatActivity implements Controller.ResponseListener {

    QuoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Controller controller = new Controller(this);
        controller.start();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new QuoteAdapter(this,null);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

    }

    @Override
    public void onResponse(List<Quote> list) {
        adapter.setData(list);
    }

    @Override
    public void onResponseFailed(String response) {
        Toast.makeText(this,response,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }
}
