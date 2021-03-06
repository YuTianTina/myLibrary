package yutiantian.mylibrary.SlidingTabLayout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yutiantian.mylibrary.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFra2 extends Fragment {


    private  boolean hasLoad=false;
    private String LOGNAME="TabFra2";
    private boolean isVisible;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            isVisible=true;
//            if(!hasLoad){
                Log.i(LOGNAME,"setUserVisibleHint-true");
//                hasLoad=!hasLoad;
//            }
        }else {
            isVisible=true;
            Log.i(LOGNAME,"setUserVisibleHint-false");
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOGNAME,"onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i(LOGNAME,"onCreateView");
        return inflater.inflate(R.layout.fragment_tab_fra2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(LOGNAME,"onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(LOGNAME,"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
//        if(!hasLoad){
            Log.i(LOGNAME,"onResume"+isVisible);
//            hasLoad=!hasLoad;
//        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(LOGNAME,"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(LOGNAME,"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(LOGNAME,"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOGNAME,"onDestroy");
    }

    public TabFra2() {
        // Required empty public constructor
    }

}
