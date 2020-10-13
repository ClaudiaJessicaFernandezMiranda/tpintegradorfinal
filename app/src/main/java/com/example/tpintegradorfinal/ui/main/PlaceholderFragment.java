package com.example.tpintegradorfinal.ui.main;

import androidx.fragment.app.Fragment;

import com.example.tpintegradorfinal.ui.login.InformacionFragment;
import com.example.tpintegradorfinal.ui.login.LoginFragment;

public class PlaceholderFragment extends Fragment {

    public static Fragment newInstance(int index) {
        Fragment fragment = new Fragment();
        switch (index){
            case 1: fragment = new LoginFragment(); break;
            case 2: fragment = new InformacionFragment(); break;

        }
        return fragment;
    }

}