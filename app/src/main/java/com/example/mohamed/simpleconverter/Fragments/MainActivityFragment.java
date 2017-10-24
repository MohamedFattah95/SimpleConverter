package com.example.mohamed.simpleconverter.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohamed.simpleconverter.Activities.MainActivity;
import com.example.mohamed.simpleconverter.Adapters.mRecyclerAdapter;
import com.example.mohamed.simpleconverter.Listeners.RecyclerItemClickListener;
import com.example.mohamed.simpleconverter.Models.Categories;
import com.example.mohamed.simpleconverter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    RecyclerView mRecycler;
    com.example.mohamed.simpleconverter.Adapters.mRecyclerAdapter mRecyclerAdapter;
    List<Categories> mCategory = new ArrayList<>();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mRecycler = (RecyclerView) rootView.findViewById(R.id.mRecycler);
        setUpLayout();

        final Categories temperature = new Categories(R.drawable.temperature, R.string.temp);
        Categories length = new Categories(R.drawable.length, R.string.length);
        Categories weight = new Categories(R.drawable.weight, R.string.weight);
        Categories volume = new Categories(R.drawable.volume, R.string.volume);
        Categories time = new Categories(R.drawable.time, R.string.time);
        Categories area = new Categories(R.drawable.area, R.string.area);
        Categories digitalStorage = new Categories(R.drawable.digital, R.string.storage);
        Categories numSystem = new Categories(R.drawable.binary, R.string.number);

        mCategory.add(temperature);
        mCategory.add(length);
        mCategory.add(weight);
        mCategory.add(volume);
        mCategory.add(time);
        mCategory.add(area);
        mCategory.add(digitalStorage);
        mCategory.add(numSystem);

        mRecyclerAdapter = new mRecyclerAdapter(getActivity(), mCategory);
        mRecycler.setAdapter(mRecyclerAdapter);

        mRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        mCategory.get(position);
                        if (position == 0) {
                            ((MainActivity) getActivity()).replaceFragments(TemperatureActivityFragment.class);
                            getActivity().setTitle(R.string.temp);
                        } else if (position == 1) {
                            ((MainActivity) getActivity()).replaceFragments(LengthActivityFragment.class);
                            getActivity().setTitle(R.string.length);
                        } else if (position == 2) {
                            ((MainActivity) getActivity()).replaceFragments(WeightActivityFragment.class);
                            getActivity().setTitle(R.string.weight);
                        } else if (position == 3) {
                            ((MainActivity) getActivity()).replaceFragments(VolumeActivityFragment.class);
                            getActivity().setTitle(R.string.volume);
                        } else if (position == 4) {
                            ((MainActivity) getActivity()).replaceFragments(TimeActivityFragment.class);
                            getActivity().setTitle(R.string.time);
                        } else if (position == 5) {
                            ((MainActivity) getActivity()).replaceFragments(AreaActivityFragment.class);
                            getActivity().setTitle(R.string.area);
                        } else if (position == 6) {
                            ((MainActivity) getActivity()).replaceFragments(DigitalStorageActivityFragment.class);
                            getActivity().setTitle(R.string.storage);
                        } else if (position == 7) {
                            ((MainActivity) getActivity()).replaceFragments(NumberSystemActivityFragment.class);
                            getActivity().setTitle(R.string.number);
                        }

                    }
                })
        );

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        setUpLayout();
        mRecyclerAdapter = new mRecyclerAdapter(getActivity(), mCategory);
        mRecycler.setAdapter(mRecyclerAdapter);

    }

    public void setUpLayout() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String layout = prefs.getString(getString(R.string.pref_layout_key),
                getString(R.string.pref_layout_grid));

        RecyclerView.LayoutManager layoutManager;

        switch (layout) {
            case "grid":
            default:
                layoutManager = new GridLayoutManager(getActivity(), 3);
                mRecycler.setLayoutManager(layoutManager);
                break;
            case "linear":
                LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(getActivity());
                mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
                mRecycler.setLayoutManager(mLinearLayoutManagerVertical);
                break;
            case "grid2":
                layoutManager = new GridLayoutManager(getActivity(), 2);
                mRecycler.setLayoutManager(layoutManager);
                break;
        }

    }
}
