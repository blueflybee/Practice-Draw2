package com.hencoder.hencoderpracticedraw2;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

public class PageFragment extends Fragment {
  @LayoutRes
  int sampleLayoutRes;
  @LayoutRes
  int practiceLayoutRes;

  public static PageFragment newInstance(@LayoutRes int sampleLayoutRes, @LayoutRes int practiceLayoutRes) {
    PageFragment fragment = new PageFragment();
    Bundle args = new Bundle();
    args.putInt("sampleLayoutRes", sampleLayoutRes);
    args.putInt("practiceLayoutRes", practiceLayoutRes);
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_page, container, false);

    ViewStub sampleStub = (ViewStub) view.findViewById(R.id.sampleStub);
    sampleStub.setLayoutResource(sampleLayoutRes);
    sampleStub.inflate();

    ViewStub practiceStub = (ViewStub) view.findViewById(R.id.practiceStub);
    practiceStub.setLayoutResource(practiceLayoutRes);
    practiceStub.inflate();

    if (sampleLayoutRes == R.layout.test_xfermode_view
        || sampleLayoutRes == R.layout.exp1_xfermode_view
        || sampleLayoutRes == R.layout.video_download_progress_bar) {
      view.findViewById(R.id.rl_practice).setVisibility(View.GONE);
    }

    return view;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle args = getArguments();
    if (args != null) {
      sampleLayoutRes = args.getInt("sampleLayoutRes");
      practiceLayoutRes = args.getInt("practiceLayoutRes");
    }
  }
}
