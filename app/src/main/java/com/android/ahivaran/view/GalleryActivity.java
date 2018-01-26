package com.android.ahivaran.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.android.ahivaran.Presenter.FlikrApiPresenter;
import com.android.ahivaran.R;
import com.android.ahivaran.adapter.GalleryAdapter;
import com.android.ahivaran.data.api.FlikrImageApi;
import com.android.ahivaran.data.repository.FlickrAPiRepository;
import com.android.ahivaran.data.retrofit.ApiRetrofitFactory;
import com.android.ahivaran.domain.FlickrImage;
import com.android.ahivaran.domain.Photos;
import com.android.ahivaran.utils.CommonSubscriber;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GalleryActivity extends AppCompatActivity {
    public static final String TAG = GalleryActivity.class.getSimpleName();

    private GalleryAdapter galleryAdapter;
    private LinearLayoutManager layoutManager;
    private FlikrApiPresenter flikrApiPresenter;
    private int totalPage = 0;
    private boolean isLoading;
    private int currentPage = 0;
    private boolean isLastPage;

    @Bind(R.id.galleryRecyclerView)
    RecyclerView galleryRecyclerView;

    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorPrimaryDark,
                R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (currentPage == 0) {
                    loadMoreItems();
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        setActionBar();
        flikrApiPresenter = new FlikrApiPresenter(new FlickrAPiRepository(ApiRetrofitFactory.getInstance().create(FlikrImageApi.class)));
        galleryAdapter = new GalleryAdapter(this);
        galleryRecyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        galleryRecyclerView.setLayoutManager(layoutManager);
        galleryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        galleryRecyclerView.setAdapter(galleryAdapter);
        galleryRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);
        loadMoreItems();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= totalPage) {
                    loadMoreItems();
                }
            }
        }
    };

    private void loadMoreItems() {
        swipeRefreshLayout.setRefreshing(true);
        isLoading = true;
        currentPage++;
        Log.d(TAG, "loadMoreItems(): pageNo: "+currentPage);
        flikrApiPresenter.getImagesFromFlickr(currentPage, new CommonSubscriber<Photos>() {
            @Override
            public void onNext(Photos photos) {
                super.onNext(photos);
                Log.d(TAG, "onNext(): ");
                swipeRefreshLayout.setRefreshing(false);
                isLoading = false;
                if (currentPage == Integer.parseInt(photos.getPages())){
                    isLastPage = true;
                }
                totalPage = Integer.parseInt(photos.getPerPage());
                galleryAdapter.setPhotoList(photos.getPhotoList());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                swipeRefreshLayout.setRefreshing(false);
                isLoading = false;
                currentPage--;
            }
        });
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        flikrApiPresenter.destroy();
    }
}
