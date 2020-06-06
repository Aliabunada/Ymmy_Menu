package com.example.resturant_menu.ui.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.resturant_menu.Adapter.MenuAdapter;
import com.example.resturant_menu.DB.Mnuedb;
import com.example.resturant_menu.R;
import com.example.resturant_menu.models.Menu_Categories;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
  public  static ArrayList<Menu_Categories> arrayList = new ArrayList<>();
    Mnuedb db ;
    MenuAdapter menuAdapter ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


       db = new Mnuedb(getContext());
       arrayList = db.getAllMENUE();
        ListView menu_Listview = root.findViewById(R.id.menu_listview);
        menuAdapter = new MenuAdapter(arrayList,getContext());
        menu_Listview.setAdapter(menuAdapter);

        return root;
    }
}
