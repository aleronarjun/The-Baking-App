package com.example.android.thebakingapp;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidget extends AppWidgetProvider {
    static long id = 1;
    static RecipeAndIngredient recipeAndIngredient = RecipeAndIngredient.findById(RecipeAndIngredient.class, id);

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        //CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
        //views.setTextViewText(R.id.appwidget_text, widgetText);

        if(recipeAndIngredient!=null) {
            views.setTextViewText(R.id.wid_name, recipeAndIngredient.title);
            views.setTextViewText(R.id.wid_ing, recipeAndIngredient.ingredients);
        }
        else {
            views.setTextViewText(R.id.wid_name, "Nothing in DB");
            views.setTextViewText(R.id.wid_ing, "Add to DB to see recipe name and ingredients here!");
        }
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

