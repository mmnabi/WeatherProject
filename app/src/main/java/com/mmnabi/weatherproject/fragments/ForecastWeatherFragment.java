package com.mmnabi.weatherproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmnabi.weatherproject.R;
import com.mmnabi.weatherproject.activities.MainActivity;
import com.mmnabi.weatherproject.adapters.ForecastAdapter;
import com.mmnabi.weatherproject.common.helpers.WeatherServiceHelper;
import com.mmnabi.weatherproject.models.ForecastDTO;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastWeatherFragment extends Fragment implements WeatherServiceHelper
        .ForecastResponseListener {

    private ArrayList<ForecastDTO> forecastList;

    private RecyclerView rvForecastWeather;

    private WeatherServiceHelper serviceHelper;

    private ForecastAdapter adapter;

    public ForecastWeatherFragment() {
        // Required empty public constructor
    }

    public static ForecastWeatherFragment getInstance() {
        Bundle bundle = new Bundle();
        ForecastWeatherFragment fragment = new ForecastWeatherFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forecast_weather, container, false);

        // Initiating View
        rvForecastWeather = view.findViewById(R.id.rvForecastWeather);

        serviceHelper = new WeatherServiceHelper(this);

        if (MainActivity.getWeatherUsingLatLon)
            getForecastWeather(MainActivity.latitude, MainActivity.longitude);
        else
            getForecastWeather();

        // Inflate the layout for this fragment
        return view;


    }

    private void getForecastWeather() {
        serviceHelper.getForecastWeather();
    }

    private void getForecastWeather(Double lat, Double lon) {
        serviceHelper.getForecastWeather(lat, lon);
    }

    @Override
    public void onForecastDataReceived(ArrayList<ForecastDTO> forecastList) {
        this.forecastList = forecastList;

        adapter = new ForecastAdapter(getContext(), forecastList);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,
                false);
        rvForecastWeather.setLayoutManager(manager);
        rvForecastWeather.setAdapter(adapter);
    }
}
