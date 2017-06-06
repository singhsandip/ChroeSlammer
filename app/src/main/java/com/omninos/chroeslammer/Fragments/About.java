package com.omninos.chroeslammer.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omninos.chroeslammer.R;

/**
 * Created by sandeep on 11-05-2017.
 */

public class About extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_about,container,false);
    }
}
