package com.groundzero.appfactory.music.handlers;

import android.content.Context;

import com.groundzero.appfactory.R;
import com.groundzero.appfactory.music.adapters.TopAlbumsAdapter;
import com.groundzero.appfactory.music.models.TopAlbums;
import com.groundzero.appfactory.music.view.TopAlbumsView;

import retrofit2.Response;

public class TopAlbumsResponseHandler extends ResponseHandler<TopAlbumsView> {

  public TopAlbumsResponseHandler(Context context, TopAlbumsView view) {
    super(context, view);
  }

  @Override
  public void onError() {
    view.generateToast(context.getResources().getString(R.string.warning_no_data_fetched)).show();
  }

  public void topAlbumResponseSuccess(Response<TopAlbums> response) {
    if (response != null) {
      TopAlbumsAdapter topAlbumsAdapter = new TopAlbumsAdapter(
              context,
              response.body().getTopAlbums().getAlbumDetails(),
              response.body().getTopAlbums().getArtist().getArtist());

      view.fetchRecyclerView().setAdapter(topAlbumsAdapter);
    }
  }
}
