package com.pascalhow.rxJavaExample.secondexample;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import rx.functions.Action1;

/**
 * Created by pascal on 26/12/2016.
 */

public class SecondExampleFragment extends Fragment {

    @BindView(R2.id.second_example_text)
    TextView secondExampleText;

    @BindView(R2.id.second_example_edit_text)
    EditText secondExampleEditText;

    private MainActivity mainActivity;
    private static final String TAG = "Second Example";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_second_example, container, false);

        ButterKnife.bind(this, rootView);

        mainActivity = (MainActivity) getActivity();
        mainActivity.setTitle(R.string.second_example_screen_fragment_title);

        setHasOptionsMenu(true);

        return rootView;
    }

    @OnClick(R2.id.second_example_button)
    public void onSecondExampleButtonClick() {
        sampleObservable().subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sampleSubscriber());
    }

    public Observable<String> sampleObservable() {

        return Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        try {
                            Thread.sleep(3000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String[] strings = secondExampleEditText.getText().toString().split("\n");
                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < strings.length; i++) {
                            builder.append((i+1) + ". " + strings[i] + "\n");
                        }
                        sub.onNext(builder.toString());
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
                secondExampleText.setText(text);
            }
        };
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_settings);
        item.setVisible(true);
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
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
