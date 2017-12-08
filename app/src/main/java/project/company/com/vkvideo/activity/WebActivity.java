package project.company.com.vkvideo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.company.com.vkvideo.R;

public class WebActivity extends AppCompatActivity {
    @BindView(R.id.web_view)
    com.vk.sdk.WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        String s = getIntent().getStringExtra("EXTRA_SESSION_ID");
        mWebView.loadUrl(s);
    }
}
