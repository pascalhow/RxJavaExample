package com.pascalhow.navigationdrawerbaseapp.mainfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pascalhow.navigationdrawerbaseapp.MainActivity;
import com.pascalhow.navigationdrawerbaseapp.R;
import com.pascalhow.navigationdrawerbaseapp.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pascal on 26/12/2016.
 */

public class MainFragment extends Fragment {

    @BindView(R2.id.main_text)
    TextView mainText;

    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, rootView);

        mainActivity = (MainActivity) getActivity();
        mainActivity.setTitle(R.string.main_screen_fragment_title);
        mainActivity.showFloatingActionButton();

        setHasOptionsMenu(true);

        return rootView;
    }

    @OnClick(R2.id.main_button)
    public void onMainButtonClick() {
        mainText.setText(getString(R.string.main_screen_content_text));
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_settings);
        item.setVisible(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_settings:
                //  Save new trip
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
