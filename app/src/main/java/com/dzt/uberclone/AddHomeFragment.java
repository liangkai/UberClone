package com.dzt.uberclone;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class AddHomeFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    View v;
    boolean home;

    public AddHomeFragment()
    {

    }
    public AddHomeFragment(Boolean home) {
        this.home = home;
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_add_home, container, false);
        AutoCompleteTextView autoCompView = (AutoCompleteTextView) v.findViewById(R.id.add_home_autocomplete);
        autoCompView.setAdapter(new PlacesAutoCompleteAdapter(getActivity(), R.layout.list_item));
        autoCompView.setOnItemClickListener(this);
        Button removeHomeButton = (Button) v.findViewById(R.id.remove_home_button);
        if(!home)
        {
            removeHomeButton.setVisibility(View.INVISIBLE);
        }
        else
        {
            removeHomeButton.setOnClickListener(this);
        }
        SharedPreferences sp = getActivity().getSharedPreferences("Session", Context.MODE_PRIVATE);

        String home =  sp.getString("home","Add Home");
        if(!home.equals("Add Home"))
        {
            autoCompView.setText(home);
        }

        return v;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String str = (String) adapterView.getItemAtPosition(position);

        SharedPreferences sp = getActivity().getSharedPreferences("Session",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor  = sp.edit();
        editor.putString("home", str);
        editor.commit();
        getActivity().getSupportFragmentManager().popBackStackImmediate();
        //Set Home
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.remove_home_button:
                //remove home code
                SharedPreferences sp = getActivity().getSharedPreferences("Session",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor  = sp.edit();
                editor.putString("home", "Add Home");
                editor.commit();
                getActivity().getSupportFragmentManager().popBackStackImmediate();
                break;
        }
    }
}
