package com.pascalhow.rxJavaExample.firstexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.pascalhow.rxJavaExample.MainActivity;
import com.pascalhow.rxJavaExample.R;
import com.pascalhow.rxJavaExample.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by pascal on 26/12/2016.
 */

public class FirstExampleFragment extends Fragment {

    @BindView(R2.id.first_example_text)
    TextView firstExampleText;

    @BindView(R2.id.first_example_edit_text)
    EditText firstExampleEditText;

    private MainActivity mainActivity;
    private static final String TAG = "First Example";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first_example, container, false);

        ButterKnife.bind(this, rootView);

        mainActivity = (MainActivity) getActivity();
        mainActivity.setTitle(R.string.first_example_screen_fragment_title);

        setHasOptionsMenu(true);

        return rootView;
    }

    @OnClick(R2.id.first_example_button)
    public void onFirstExampleButtonClick() {
        sampleObservable().subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sampleSubscriber());
    }

    public Observable<String> sampleObservable() {

        return Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext(firstExampleEditText.getText().toString());
                        sub.onCompleted();
                    }
                }
        );
    }

    public Subscriber<String> sampleSubscriber() {
        return new Subscriber<String>() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onNext(String text) {
                Log.d(TAG, "onNext");
                firstExampleText.setText(text);
            }
        };
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
