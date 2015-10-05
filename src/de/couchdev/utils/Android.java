package de.couchdev.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class Android {
	
	/**
	 * Expands the given ListView to its full size based on the size of each child View.
	 * @param listView The ListView to expand.
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter(); 
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

	/**
	 * Expands the given ListView in a similar way to {@link #setListViewHeightBasedOnChildren(ListView)}.
	 * The difference is that it won't consider the size of the whole child View but of a single child of it.
	 * This is needed if you have invisible Views in your ListViews children that would distort the expanded height
	 * of the ListView.
	 * @param listView The ListView to expand.
	 * @param position The position of the View inside of the ListViews child View to consider for height measuring.
	 */
	public static void setListViewHeightBasedOnChildrenChildInPos(ListView listView, int position) {
        ListAdapter listAdapter = listView.getAdapter(); 
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            View child = ((RelativeLayout) listItem).getChildAt(position);
            child.measure(0, 0);
            totalHeight += child.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
