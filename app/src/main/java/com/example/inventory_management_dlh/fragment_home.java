package com.example.inventory_management_dlh;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.grid.BoxAdapterGrid;
import com.example.grid.CustomAdapter;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class fragment_home extends Fragment {

    public static String [] NameList = {"Add","Report","Admin","Products","Help"};
    public static String [] isiBox = {"Data Stock","Pemasukan \n Barang","Pengeluaran \n Barang"};

    public static int [] ImageList = {R.drawable.ic_baseline_create_24,
            R.drawable.ic_baseline_description_24,
            R.drawable.ic_baseline_assignment_ind_24,
            R.drawable.ic_baseline_assignment_24,
            R.drawable.ic_baseline_help_24};
    public static  int [] bgBox = {R.color.white,R.color.white,R.color.white};

    private static int [] carouselData = {R.drawable.carousel_1,R.drawable.carousel_2};

    GridView gridView;
    GridView box;

    CarouselView carouselView;

    public fragment_home() {
        // Required empty public constructor
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(carouselData[position]);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        gridView = v.findViewById(R.id.gridview);
        gridView.setAdapter(new CustomAdapter((Home) getActivity(),NameList,ImageList));

        box = v.findViewById(R.id.boxGridview);
        box.setAdapter(new BoxAdapterGrid((Home) getActivity(), isiBox, bgBox));

        carouselView = v.findViewById(R.id.carousel_view);
        carouselView.setPageCount(carouselData.length);
        carouselView.setImageListener(imageListener);

        return v;
//        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
    }
}
