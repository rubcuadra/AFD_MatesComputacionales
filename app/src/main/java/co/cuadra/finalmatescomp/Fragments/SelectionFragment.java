package co.cuadra.finalmatescomp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.cuadra.finalmatescomp.R;

/**
 * Created by rubcuadra on 4/23/17.
 */

public class SelectionFragment extends Fragment
{
    @BindView(R.id.colorsChoice) Spinner mSpinnerColor;
    @BindView(R.id.storageChoice) Spinner mSpinnerStorage;
    @BindView(R.id.totalAirpods) EditText mETAirpods;
    @BindView(R.id.totalCable) EditText mETCable;


    private Activity CONTEXT;
    private static String TAG="HOMEFRAGMENT";
    private onSInteractionListener mListener;
    private Unbinder unbinder;

    private String color;
    private String storage;

    public SelectionFragment() {}

    public static SelectionFragment newInstance()
    {
        Log.d(TAG,"NEW");
        SelectionFragment fragment = new SelectionFragment();
        //fragment.setArguments( Parser.UserToBundle(u) );
        return fragment;
    }

    public String repeat(String e,int times)
    {
        String r = "";
        for (int i = 0; i<times;++i)  r+=e;
        return r;
    }

    public String getCode()
    {
        int tAP = Integer.parseInt(mETAirpods.getText().toString());
        int tLC = Integer.parseInt(mETCable.getText().toString());
        String r = storage + color + repeat("C",tLC) + repeat("A",tAP);

        return r;
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
        View view = inflater.inflate(R.layout.fragment_selection, container, false);
        unbinder = ButterKnife.bind(this, view);
        mSpinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case 1:
                        color = "B";
                        break;
                    case 2:
                        color = "D";
                        break;
                    case 3:
                        color = "R";
                        break;
                    default:
                        color = "N";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        mSpinnerStorage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case 0:
                        storage = "3";
                        break;
                    case 1:
                        storage = "6";
                        break;
                    default:
                        storage = "1";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        return view;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof onSInteractionListener)
        {
            mListener = (onSInteractionListener) context;
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

    @OnClick(R.id.cancel)
    public void cancel(View v)
    {
        mListener.onSInteraction("",0); //Debe quitar la letra del uPod/uPhone/uPad
    }
    @OnClick(R.id.addOther)
    public void _continue(View v)
    {
        mListener.onSInteraction( getCode()+"$" , 1); //Nos regresa a Home y ya
    }
    @OnClick(R.id.checkout)
    public void checkout(View v)
    {
        mListener.onSInteraction(getCode(),2);  //Nos Manda a Checkout
    }

    public interface onSInteractionListener
    {
        void onSInteraction(String word, int button);
    }
}
