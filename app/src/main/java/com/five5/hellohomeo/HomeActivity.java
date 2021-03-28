package com.five5.hellohomeo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.five5.hellohomeo.adapters.CrewAdapter;
import com.five5.hellohomeo.apis.GetAllAPI;
import com.five5.hellohomeo.dao.CrewDao;
import com.five5.hellohomeo.database.DatabaseCrew;
import com.five5.hellohomeo.models.Crew;
import com.five5.hellohomeo.models.CrewEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    GetAllAPI myApi;
    List<Crew> crews=new ArrayList<>();
    LinearLayoutManager lmp= new LinearLayoutManager(this);
    RecyclerView recyclerView;
    CrewAdapter crewAdapter;
    Button delete,refresh;
    DatabaseCrew db;
    CrewDao crewDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        checkInternet();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeData();
                setupRecycler();
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFromInternet();
            }
        });

    }

    private void checkInternet() {
        ConnectivityManager connectivityManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            loadFromInternet();

            
        }
        else
        { loadFromDatabase();}
    }

    private void loadFromDatabase() {
        crews=crewDao.getAll();
        setupRecycler();
    }

    private void loadFromInternet() {
        removeData();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.spacexdata.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myApi = retrofit.create(GetAllAPI.class);
        Call<List<Crew>> call=myApi.getAllCrewDetails();

        call.enqueue(new Callback<List<Crew>>() {
            @Override
            public void onResponse(Call<List<Crew>> call, Response<List<Crew>> response) {
                crews.addAll(response.body());
                setupRecycler();
                for(int i=0;i<crews.size();i++){
                    crewDao.insert(response.body().get(i));
                }
                Log.d("RoomData",crewDao.getAll().size()+"");
            }

            @Override
            public void onFailure(Call<List<Crew>> call, Throwable t) {

            }
        });
    }

    private void removeData() {
        crewDao.deleteAll();
        crews.clear();
    }


    private void setupRecycler() {
        crewAdapter= new CrewAdapter(crews,this.getApplication());
        recyclerView.setAdapter(crewAdapter);
        recyclerView.setLayoutManager(lmp);
        recyclerView.setHasFixedSize(true);
    }

    private void init() {
        recyclerView= findViewById(R.id.recyCrew);
        delete=findViewById(R.id.btnDelete);
        refresh=findViewById(R.id.btnRefresh);
        db = Room.databaseBuilder(getApplicationContext(),
                DatabaseCrew.class, "crews").allowMainThreadQueries().build();
        crewDao=db.crewDao();
    }
}