package com.example.mycsucompanion;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String PORTAL_URL = "https://myschool.carsu.edu.ph/";

    private static final int NAVY = Color.rgb(16, 45, 82);
    private static final int BLUE = Color.rgb(28, 91, 155);
    private static final int BACKGROUND = Color.rgb(242, 246, 251);
    private static final int TEXT_DARK = Color.rgb(35, 48, 65);
    private static final int TEXT_MUTED = Color.rgb(103, 117, 135);
    private static final int WHITE = Color.WHITE;

    private LinearLayout root;
    private WebView webView;
    private boolean showingPortal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showDashboard();
    }

    private void showDashboard() {
        showingPortal = false;

        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);
        scrollView.setBackgroundColor(BACKGROUND);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(20), dp(24), dp(20), dp(28));
        scrollView.addView(root);

        TextView eyebrow = new TextView(this);
        eyebrow.setText("STUDENT PORTAL");
        eyebrow.setTextColor(BLUE);
        eyebrow.setTextSize(12);
        eyebrow.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        eyebrow.setLetterSpacing(0.12f);
        root.addView(eyebrow);

        TextView title = new TextView(this);
        title.setText("MyCSU Companion");
        title.setTextColor(NAVY);
        title.setTextSize(28);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.topMargin = dp(5);
        root.addView(title, titleParams);

        TextView subtitle = new TextView(this);
        subtitle.setText("Your school portal, one tap away.");
        subtitle.setTextColor(TEXT_MUTED);
        subtitle.setTextSize(15);
        LinearLayout.LayoutParams subtitleParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        subtitleParams.topMargin = dp(6);
        subtitleParams.bottomMargin = dp(20);
        root.addView(subtitle, subtitleParams);

        LinearLayout infoCard = new LinearLayout(this);
        infoCard.setOrientation(LinearLayout.VERTICAL);
        infoCard.setPadding(dp(16), dp(16), dp(16), dp(16));
        infoCard.setBackground(roundRect(WHITE, dp(18)));
        TextView infoTitle = new TextView(this);
        infoTitle.setText("Quick access");
        infoTitle.setTextColor(NAVY);
        infoTitle.setTextSize(18);
        infoTitle.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        infoCard.addView(infoTitle);

        TextView infoText = new TextView(this);
        infoText.setText("Choose a service below to open the official MySchool portal.");
        infoText.setTextColor(TEXT_MUTED);
        infoText.setTextSize(14);
        LinearLayout.LayoutParams infoTextParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        infoTextParams.topMargin = dp(6);
        infoCard.addView(infoText, infoTextParams);
        root.addView(infoCard);

        String[] services = {
                "My Grades",
                "Class Schedule",
                "Announcements",
                "Clearance",
                "School Balance / Bills",
                "Offered Subjects",
                "Online Enrollment"
        };

        for (String service : services) {
            Button button = new Button(this);
            button.setText(service);
            button.setAllCaps(false);
            button.setTextSize(15);
            button.setTextColor(NAVY);
            button.setGravity(Gravity.CENTER_VERTICAL);
            button.setPadding(dp(14), 0, dp(14), 0);
            button.setBackground(roundRect(WHITE, dp(14)));
            button.setElevation(dp(1));
            button.setOnClickListener(v -> openPortal(service));

            LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, dp(54));
            buttonParams.topMargin = dp(10);
            root.addView(button, buttonParams);
        }

        Button openPortalButton = new Button(this);
        openPortalButton.setText("Open MySchool Portal");
        openPortalButton.setAllCaps(false);
        openPortalButton.setTextSize(16);
        openPortalButton.setTextColor(WHITE);
        openPortalButton.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        openPortalButton.setBackground(roundRect(BLUE, dp(14)));
        openPortalButton.setOnClickListener(v -> openPortal("MySchool Portal"));

        LinearLayout.LayoutParams portalParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, dp(56));
        portalParams.topMargin = dp(18);
        root.addView(openPortalButton, portalParams);

        TextView note = new TextView(this);
        note.setText("Independent student project • Not an official CSU app");
        note.setTextColor(TEXT_MUTED);
        note.setTextSize(12);
        note.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams noteParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        noteParams.topMargin = dp(18);
        root.addView(note, noteParams);

        setContentView(scrollView);
    }

    private void openPortal(String serviceName) {
        showingPortal = true;

        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setBackgroundColor(WHITE);

        LinearLayout toolbar = new LinearLayout(this);
        toolbar.setOrientation(LinearLayout.HORIZONTAL);
        toolbar.setGravity(Gravity.CENTER_VERTICAL);
        toolbar.setPadding(dp(8), dp(4), dp(12), dp(4));
        toolbar.setBackgroundColor(NAVY);

        Button homeButton = new Button(this);
        homeButton.setText("Home");
        homeButton.setAllCaps(false);
        homeButton.setTextColor(WHITE);
        homeButton.setBackgroundColor(Color.TRANSPARENT);
        homeButton.setOnClickListener(v -> showDashboard());
        toolbar.addView(homeButton, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, dp(48)));

        TextView toolbarTitle = new TextView(this);
        toolbarTitle.setText(serviceName);
        toolbarTitle.setTextColor(WHITE);
        toolbarTitle.setTextSize(16);
        toolbarTitle.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        toolbarTitle.setPadding(dp(8), 0, 0, 0);
        toolbar.addView(toolbarTitle, new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        page.addView(toolbar);

        webView = new WebView(this);
        webView.setBackgroundColor(WHITE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                // Keep normal HTTPS portal navigation inside this WebView.
                // The portal may still require a browser for some sign-in flows.
                return false;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request,
                                        android.webkit.WebResourceError error) {
                super.onReceivedError(view, request, error);
                if (request.isForMainFrame()) {
                    Toast.makeText(MainActivity.this,
                            "The portal could not load. Check your internet connection.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        page.addView(webView, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));

        setContentView(page);
        webView.loadUrl(PORTAL_URL);
    }

    private GradientDrawable roundRect(int color, int radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(radius);
        return drawable;
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }

    @Override
    public void onBackPressed() {
        if (showingPortal && webView != null && webView.canGoBack()) {
            webView.goBack();
        } else if (showingPortal) {
            showDashboard();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.stopLoading();
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}
