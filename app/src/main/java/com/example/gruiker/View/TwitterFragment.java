package com.example.gruiker.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gruiker.HeaderInterceptor;
import com.example.gruiker.Model.MainActivity;
import com.example.gruiker.Model.Tweet;
import com.example.gruiker.Model.TweetApi;
import com.example.gruiker.R;
import com.example.gruiker.ViewModel.TwitterViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.twitter.sdk.android.core.internal.TwitterApi;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TwitterFragment extends Fragment {

    private TwitterViewModel twitterViewModel;
    private Boolean translated;
    private TextView textView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        twitterViewModel =
                ViewModelProviders.of(this).get(TwitterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_twitter, container, false);
        textView = root.findViewById(R.id.twitterTextView);
        twitterViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        Button button = root.findViewById(R.id.trad_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch_lang();
            }
        });
        Button search_button = root.findViewById(R.id.search_button);
        /*search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateTweets();
            }
        });*/

        sharedPreferences = getContext().getSharedPreferences("", Context.MODE_PRIVATE);

        translated = true;
        switch_lang();
        return root;
    }
/*
    private String generateTweets(){
        String text = "TWEETS:";
        String BASE_URL = "https://api.twitter.com/";

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(logging);

        trustAllCertificates(builder);


        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,
                        CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA)
                .build();
        builder.connectionSpecs(Collections.singletonList(spec));

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit.Builder retrofitbuilder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(builder.build());
        Retrofit retrofit = retrofitbuilder.build();
        TweetApi twitterApi = retrofit.create(TweetApi.class);
        CallApi(twitterApi);
        / *
        List<Tweet> tweets = getTweetsFromDatabase();
        String test_string = tweets.get(0).getText();
        textView.setText(test_string);
        System.out.println(test_string);
        * /
        return text;
    }


    private static OkHttpClient trustAllCertificates(OkHttpClient.Builder builder) {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            X509Certificate[] cArrr = new X509Certificate[0];
                            return cArrr;                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private List<Tweet> getTweetsFromDatabase(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Elements","");
        return gson.fromJson(json, new TypeToken<List<Tweet>>(){}.getType());
    }

    void CallApi(TweetApi tweetApi){
        Call<List<Tweet>> call = tweetApi.loadChanges();
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                List<Tweet> meubleList = response.body();
                Gson gson = new Gson();
                String json = gson.toJson(meubleList);
                System.out.println("JSON : "+json);
                //sharedPreferences.edit().putString("Elements",json).putInt("Elements_number",meubleList.size()).apply();
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                Log.d("ERROR", "Api error");
            }
        });
    }
*/
    private String language(String text){
        String begin = sharedPreferences.getString("current_beginning","");
        String middle = sharedPreferences.getString("current_middle","");
        String ending = sharedPreferences.getString("current_ending","");

        String new_text = "";
        Boolean first_word;

        String[] sentences = text.split("\\.|\\?");//     ".|\\?"
        Boolean first_sentence=true;
        for(String sentence : sentences) {
            first_word = true;
            String new_sentence = "";

            if(!first_sentence){
                sentence = sentence.substring(1);
            } else{
                first_sentence = false;
            }

            String[] propositions = sentence.split(", ");
            for (String proposition : propositions) {//traitement des propositions (",")
                String new_proposition = "";
                String[] words = proposition.split(" ");
                String new_word = "";
                for (String word : words) {//traitement du mot
                    if (word.length() >= begin.length() + middle.length() + ending.length()) {
                        if(first_word){
                            String upper_begin = begin.substring(0,1).toUpperCase() + begin.substring(1);
                            new_word = upper_begin;
                        } else {
                            new_word = begin;
                        }
                        for (int i = 0; i < word.length() - begin.length() - ending.length(); i++) {
                            new_word += middle;
                        }
                        new_word += ending;
                    } else {
                        new_word = word;
                    }
                    first_word = false;

                    new_word += " ";
                    new_proposition += new_word;
                }
                new_proposition = new_proposition.substring(0, new_proposition.length() - 1) + ", ";
                new_sentence += new_proposition;
            }
            new_sentence = new_sentence.substring(0, new_sentence.length() - 2) + ". ";
            new_text += new_sentence;
        }
        return new_text;
    }

    public void switch_lang(){
        String raw_text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi " +
                "ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit " +
                "in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia " +
                "deserunt mollit anim id est laborum.";
        String text = "";
        if(translated){
            text = raw_text;
            translated = false;
        } else{
            text = language(raw_text);
            translated = true;
        }
        textView.setText(text);

        /*mText = new MutableLiveData<>();
        mText.setValue(text);*/
    }
}