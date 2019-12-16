package com.example.myapplication.Fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.Adapter.FeedAdapter;
import com.example.myapplication.Data.FeedModel;
import com.example.myapplication.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class FragmentNews extends Fragment {
    private SwipeRefreshLayout pullToRefresh;
    private RecyclerView recyclerView;
    private List<FeedModel> feedModelList;

    private FeedAdapter feedAdapter;
    public SendIntent sendIntent;

    public interface SendIntent {
        void sendUrl(String url);
    }


    public static FragmentNews newInstance() {

        Bundle args = new Bundle();
        FragmentNews fragment = new FragmentNews();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        pullToRefresh = view.findViewById(R.id.pullToRefresh);
        recyclerView = view.findViewById(R.id.recyclerViewNews);

        if (feedModelList == null) {
            new FeedTask().execute((Void) null);
        }else{
            feedAdapter = new FeedAdapter(feedModelList, getContext());

            feedAdapter.setOnItemClickListener(new FeedAdapter.ItemClickListener() {
                @Override
                public void onClick(int position) {
                    String url = feedModelList.get(position).getLink();
                    sendIntent.sendUrl(url);
                }
            });

            recyclerView.setAdapter(feedAdapter);
        }


        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);


        //Bắt link bài viết




        //Tạo mới tin tức
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                feedModelList.clear();
                new FeedTask().execute((Void) null);
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    //Lấy dữ liệu bằng AsyncTask
    public class FeedTask extends AsyncTask<Void, Void, Boolean> {
        private String urlLink;

        @Override
        protected void onPreExecute() {
            pullToRefresh.setRefreshing(true);
            urlLink = "https://lienminh.garena.vn/news?t=1570112101&start=10&format=feed&type=rss";
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                URL url = new URL(urlLink);
                InputStream inputStream = url.openConnection().getInputStream();
                feedModelList = parseFeed(inputStream);
                return true;
            } catch (IOException e) {
                Log.e("TAgggg", "Error", e);
            } catch (XmlPullParserException e) {
                Log.e("TAgggg", "Error", e);
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            pullToRefresh.setRefreshing(false);
            if (aBoolean) {

                feedAdapter = new FeedAdapter(feedModelList, getContext());

                feedAdapter.setOnItemClickListener(new FeedAdapter.ItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        String url = feedModelList.get(position).getLink();
                        sendIntent.sendUrl(url);
                    }
                });

                recyclerView.setAdapter(feedAdapter);

            } else {
                Toast.makeText(getActivity(), "Kết nối thất bại, vui lòng kiểm" +
                        " tra đường truyền", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Lọc dữ liệu từ rss
    public List<FeedModel> parseFeed(InputStream inputStream) throws XmlPullParserException, IOException {

        String title = null;
        String link = null;
        String description = null;
        String img = null;
        String pubDate = null;
        boolean isItem = false;
        List<FeedModel> items = new ArrayList<>();

        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);

            xmlPullParser.nextTag();
            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                int eventType = xmlPullParser.getEventType();
                String name = xmlPullParser.getName();
                if (name == null)
                    continue;
                if (eventType == XmlPullParser.END_TAG) {
                    if (name.equalsIgnoreCase("item")) {
                        isItem = false;
                    }
                    continue;
                }
                if (eventType == XmlPullParser.START_TAG) {
                    if (name.equalsIgnoreCase("item")) {
                        isItem = true;
                        continue;
                    }
                }
                Log.d("MyXmlParse", "Parsing name =>>" + name);
                String result = "";
                if (xmlPullParser.next() == XmlPullParser.TEXT) {
                    result = xmlPullParser.getText();
                    xmlPullParser.nextTag();
                }

                if (name.equalsIgnoreCase("title")) {
                    title = result;
                } else if (name.equalsIgnoreCase("link")) {
                    link = result;
                } else if (name.equalsIgnoreCase("description")) {
                    description = result;

//                    int startIndex = description.indexOf("src");
//                    int endIndex = 0;
//                    if (description.indexOf("jpg") != -1) {
//                        endIndex = description.indexOf("jpg");
//                    } else {
//                        endIndex = description.indexOf("png");
//                    }
//
//                    img = description.substring(startIndex + 5, endIndex + 3);

                } else if (name.equalsIgnoreCase("comments")) {
                    img = result;

                } else if (name.equalsIgnoreCase("pubDate")) {
                    pubDate = result;
                }
                if (title != null && link != null && description != null
                        && pubDate != null && img != null) {

                    if (isItem) {

                        String des = description.substring(description.indexOf("<p>") + 3, description.indexOf("</p>"));
                        FeedModel item = new FeedModel(title, link, pubDate, img, des);
                        Log.e("aaaaaa", " " + description.substring(description.indexOf("<p>") + 3, description.indexOf("</p>")));
                        items.add(item);
                    }


                    title = null;
                    link = null;
                    description = null;
                    img = null;
                    pubDate = null;
                    isItem = false;
                }
            }
            return items;
        } finally {
            inputStream.close();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentNews.SendIntent) {
            sendIntent = (FragmentNews.SendIntent) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SendText");
        }
    }
}

