package co.cuadra.finalmatescomp.Fragments;

/**
 * Created by rubcuadra on 4/23/17.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.cuadra.finalmatescomp.R;

/**
 * Created by rubcuadra on 4/23/17.
 */

public class CheckoutFragment extends Fragment
{
    @BindView(R.id.checkoutCode) TextView mTVcheckout;
    private Activity CONTEXT;
    private static String TAG="CHECKOUT";
    private onCInteractionListener mListener;
    private Unbinder unbinder;
    private String word;

    public CheckoutFragment() {}

    public static CheckoutFragment newInstance(String word)
    {
        Log.d(TAG,"NEW");
        CheckoutFragment fragment = new CheckoutFragment();
        Bundle b = new Bundle();
        b.putString("WORD",word);
        fragment.setArguments(b);
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
        word = getArguments().getString("WORD");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        unbinder = ButterKnife.bind(this, view);
        mTVcheckout.setText( word );
        return view;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof onCInteractionListener)
        {
            mListener = (onCInteractionListener) context;
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

    public interface onCInteractionListener
    {
        void onCInteraction();
    }
}
