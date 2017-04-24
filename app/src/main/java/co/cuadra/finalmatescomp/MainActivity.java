package co.cuadra.finalmatescomp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.cuadra.finalmatescomp.Fragments.CheckoutFragment;
import co.cuadra.finalmatescomp.Fragments.HomeFragment;
import co.cuadra.finalmatescomp.Fragments.SelectionFragment;

public class MainActivity extends AppCompatActivity implements HomeFragment.onHInteractionListener,SelectionFragment.onSInteractionListener, CheckoutFragment.onCInteractionListener
{
    @BindView(R.id.navigation) BottomNavigationView navigation;

    private int state;
    private String word;

    private HomeFragment mHF;
    private SelectionFragment sHF;

    private static final String TAG = "MAIN";

    private FragmentManager fragmentManager;
    private boolean blocked = true;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            if (blocked) return false;

            FragmentTransaction ft;
            switch (item.getItemId())
            {
                case R.id.navigation_add:
                    ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.fragment_container, mHF);
                    //ft.addToBackStack(null);
                    ft.commit();
                    return true;
                case R.id.navigation_selection:
                    ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.fragment_container, sHF);
                    //ft.addToBackStack(null);
                    ft.commit();
                    return true;
                case R.id.navigation_checkout:
                    ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.fragment_container,CheckoutFragment.newInstance(word));
                    //ft.addToBackStack(null);
                    ft.commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        state = 0; //q0
        word = ""; //Aun no hay letras

        fragmentManager = getSupportFragmentManager();
        mHF = HomeFragment.newInstance();
        sHF = SelectionFragment.newInstance();

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setSelectedItem(R.id.navigation_add );
    }

    @Override
    public void onHInteraction(String w)
    {
        this.word += w;
        Log.d(TAG,word);
        setSelectedItem(R.id.navigation_selection );
    }

    @Override
    public void onCInteraction() {}

    @Override
    public void onSInteraction(String w, int button)
    {
        switch (button)
        {
            case 0:
                word = word.substring(0,word.length()-1);
                setSelectedItem(R.id.navigation_add);
                break;
            case 1:
                word += w;
                setSelectedItem(R.id.navigation_add);
                break;
            case 2:
                word += w;
                setSelectedItem(R.id.navigation_checkout);
                break;
        }
        Log.d(TAG,word);
    }

    public void setSelectedItem(int iid)
    {
        blocked = false;
        navigation.setSelectedItemId( iid);
        blocked = true;
    }
}
