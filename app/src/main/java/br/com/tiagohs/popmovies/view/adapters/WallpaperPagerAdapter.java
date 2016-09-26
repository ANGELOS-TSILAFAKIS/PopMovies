package br.com.tiagohs.popmovies.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.List;

import br.com.tiagohs.popmovies.R;
import br.com.tiagohs.popmovies.model.dto.ImageDTO;
import br.com.tiagohs.popmovies.util.ImageUtils;
import br.com.tiagohs.popmovies.util.enumerations.ImageSize;
import uk.co.senab.photoview.PhotoView;

public class WallpaperPagerAdapter extends PagerAdapter implements View.OnClickListener {

    private Context mContext;
    private List<ImageDTO> mImageDTOs;
    private ActionBar mToolbar;
    private boolean mIsVisible = true;

    public WallpaperPagerAdapter(Activity context, List<ImageDTO> imageDTOs, ActionBar toolbar) {
        mContext = context;
        mImageDTOs = imageDTOs;
        this.mToolbar = toolbar;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_wallpaper_detail, container, false);
        PhotoView img = (PhotoView) view.findViewById(R.id.wallpaper_img);
        view.setOnClickListener(this);
        ProgressWheel progressWheel = (ProgressWheel) view.findViewById(R.id.image_item_progress);

        ImageUtils.load(mContext, mImageDTOs.get(position).getImagePath(), img, R.drawable.placeholder_images_default, R.drawable.placeholder_images_default, ImageSize.BACKDROP_1280, progressWheel);

        ((ViewGroup) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewGroup) container).removeView((View) object);
    }

    @Override
    public int getCount() {
        return mImageDTOs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void onClick(View view) {
        mIsVisible = !mIsVisible;
        if (mIsVisible)
            mToolbar.show();
        else
            mToolbar.hide();
    }
}
