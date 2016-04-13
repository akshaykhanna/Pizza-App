package com.akshaykhanna.actionbar_pizza;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class NumberAndObjectsFragment extends Fragment {


    public NumberAndObjectsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_number_and_objects, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        FrameLayout fl=(FrameLayout)getView().findViewById(R.id.fl_number_objects);
        AnimateObjects drawObj=new AnimateObjects(getActivity(),5);
        fl.addView(drawObj);
    }
}
