package com.study.riseof.contactbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactListAdapter extends ArrayAdapter<AbbreviatedContact> {

    private LayoutInflater inflater;
    private int layout;
    private List<AbbreviatedContact> contacts;

    public ContactListAdapter(@NonNull Context context, int layout, @NonNull List<AbbreviatedContact> contacts) {
        super(context, layout, contacts);
        this.contacts = contacts;
        this.layout = layout;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ContactListAdaptersHolder contactListAdaptersHolder;
        View view = convertView;
        if (view == null || view.getTag() == null) {
            view = inflater.inflate(this.layout, parent, false);
            contactListAdaptersHolder = new ContactListAdaptersHolder(view);
            view.setTag(contactListAdaptersHolder);
        } else {
            contactListAdaptersHolder = (ContactListAdaptersHolder) view.getTag();
        }

        AbbreviatedContact contact = contacts.get(position);
        Log.d("myLog", " contact  "+ contact+" position "+ position);
        contactListAdaptersHolder.itemText.setText(contact.getContactName());
        return view;
    }

    static final class ContactListAdaptersHolder {
        @BindView(R.id.contact_list_item_text)
        TextView itemText;

        public ContactListAdaptersHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }

}
/*
public class StatisticsListAdapter extends ArrayAdapter<Statistics> {
    public StatisticsListAdapter(Context context, List<Statistics> statisticsList) {
        super(context, R.layout.item_statistics, statisticsList);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        StatisticsHolder statisticsHolder;
        View view = convertView;
        if (view == null || view.getTag() == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.item_statistics, parent, false);
            statisticsHolder = new StatisticsHolder(view);
            view.setTag(statisticsHolder);
        } else {
            statisticsHolder = (StatisticsHolder) view.getTag();
        }

        Statistics statistics = getItem(position);
        String homePoint = String.valueOf(statistics.getHomePoints());
        String awayPoint = String.valueOf(statistics.getAwaypoints());
        if (statistics.isPersent()) {
            homePoint = homePoint + '%'; //NOPMD
            awayPoint = awayPoint + '%'; //NOPMD
        }

        statisticsHolder.textViewHomeTeamPoint.setText(homePoint);
        statisticsHolder.textViewAwayTeamPoint.setText(awayPoint);
        statisticsHolder.textViewStatisticsTitle.setText(statistics.getAction());

        return view;
    }


    static final class StatisticsHolder {
        @InjectView(R.id.tv_home_team_point)
        TextView textViewHomeTeamPoint;
        @InjectView(R.id.tv_away_team_point)
        TextView textViewAwayTeamPoint;
        @InjectView(R.id.tv_statistics_title)
        TextView textViewStatisticsTitle;

        public StatisticsHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
*/