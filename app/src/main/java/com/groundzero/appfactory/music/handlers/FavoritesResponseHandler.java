package com.groundzero.appfactory.music.handlers;

import android.content.Context;

import com.groundzero.appfactory.R;
import com.groundzero.appfactory.music.adapters.FavoriteAlbumsAdapter;
import com.groundzero.appfactory.music.models.SingleAlbum;
import com.groundzero.appfactory.music.view.FavoritesView;

import java.util.List;

public class FavoritesResponseHandler extends ResponseHandler<FavoritesView> {
  private List<SingleAlbum> singleAlbums;

  public FavoritesResponseHandler(Context context, FavoritesView view) {
    super(context, view);
  }

  @Override
  public void onError() {
    view.generateToast(context.getResources().getString(R.string.warning_no_data_fetched)).show();
  }

  public void onLoadData(List<SingleAlbum> albums) {
    this.singleAlbums = albums;
    view.setFavoritesSubtitle(hasBookmarks(albums));
    FavoriteAlbumsAdapter adapter = new FavoriteAlbumsAdapter(context, albums);
    view.fetchRecyclerView().setAdapter(adapter);
  }

  private boolean hasBookmarks(List<SingleAlbum> albums) {
    return albums.size() > 0;
  }

  public List<SingleAlbum> getSingleAlbums() {
    return singleAlbums;
  }
}
