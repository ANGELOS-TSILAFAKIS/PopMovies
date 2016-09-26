package br.com.tiagohs.popmovies.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ProgressBar;

import java.util.List;

import br.com.tiagohs.popmovies.R;

public class ViewUtils {

    public static int getColorFromResource(Context context, int resourceID) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return context.getColor(resourceID);
        else
            return context.getResources().getColor(resourceID);
    }

    public static Drawable getDrawableFromResource(Context context, int drawableID) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return context.getDrawable(drawableID);
        else
            return context.getResources().getDrawable(drawableID);
    }

    public static void createRatingGadget(Context context, float rating, ProgressBar rankingProgress, int max) {

        if (rating < (max / 2))
            setRankingState(context, R.drawable.progress_circle_red, rankingProgress);
        else if (rating >= (max / 2) && rating < (max / 1.6))
            setRankingState(context, R.drawable.progress_circle_yellow, rankingProgress);
        else if (rating >= (max / 1.6) && rating <= max)
            setRankingState(context, R.drawable.progress_circle_green, rankingProgress);
        else
            setRankingState(context, R.color.secondary_text, rankingProgress);

        rankingProgress.setProgress(Math.round(rating));
        rankingProgress.setMax(max);

    }

    public static String createDefaultPersonBiography(String name, String areas, List<String> knowForMovies) {
        StringBuilder biography = new StringBuilder();

        biography.append(name + " is an ");
        biography.append(areas);

        if (!knowForMovies.isEmpty()) {
            biography.append(", known for ");

            for (String movie : knowForMovies) {
                biography.append("'" + movie + "', ");
            }
        }

        biography.append(".");

        return biography.toString();
    }


    private static void setRankingState(Context context, int state, ProgressBar rankingProgress) {
        rankingProgress.setProgressDrawable(ViewUtils.getDrawableFromResource(context, state));
    }

}
