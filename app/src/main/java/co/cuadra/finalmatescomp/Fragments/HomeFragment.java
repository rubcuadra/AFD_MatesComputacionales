package co.cuadra.finalmatescomp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.cuadra.finalmatescomp.R;

/**
 * Created by rubcuadra on 4/23/17.
 */

public class HomeFragment extends Fragment
{
    private Activity CONTEXT;
    private static String TAG="HOMEFRAGMENT";
    private onHInteractionListener mListener;
    private Unbinder unbinder;

    public HomeFragment() {}

    public static HomeFragment newInstance()
    {
        Log.d(TAG,"NEW");
        HomeFragment fragment = new HomeFragment();
        //fragment.setArguments( Parser.UserToBundle(u) );
        return fragment;
    }

    @Override public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        CONTEXT = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof onHInteractionListener)
        {
            mListener = (onHInteractionListener) context;
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    public interface onHInteractionListener
    {
        void onHInteraction(String w);
    }
    @OnClick(R.id.upadImg)
    void uPadClick(View v)
    {
        mListener.onHInteraction( "К" );
    }
    @OnClick(R.id.upodImg)
    void uPodClick(View v)
    {
        mListener.onHInteraction( "Я" );
    }
    @OnClick(R.id.uphoneImg)
    void uPhoneClick(View v)
    {
        mListener.onHInteraction( "П" );
    }
}
