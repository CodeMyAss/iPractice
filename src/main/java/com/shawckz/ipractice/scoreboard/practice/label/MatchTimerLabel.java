package com.shawckz.ipractice.scoreboard.practice.label;

import com.shawckz.ipractice.match.Match;
import com.shawckz.ipractice.scoreboard.internal.XScoreboard;
import com.shawckz.ipractice.scoreboard.internal.XScoreboardTimer;
import org.bukkit.ChatColor;

/**
 * Created by 360 on 9/12/2015.
 */
public class MatchTimerLabel extends XScoreboardTimer {

    private final Match match;

    private final String valueBase = ChatColor.LIGHT_PURPLE+"Duration: "+ChatColor.GREEN;

    public MatchTimerLabel(XScoreboard scoreboard, int score, Match match) {
        super(scoreboard, score, "", 0, 20);
        this.match = match;
    }

    @Override
    public void onUpdate() {
        if(match.isStarted()){
            setValue(valueBase+getTimeString());
        }
    }

    private String getTimeString(){
        int minutes = (int)(Math.round(getTime()) % 3600) / 60;
        int seconds = (int) Math.round(getTime()) % 60;
        return minutes+":"+seconds;
    }

    @Override
    public void complete() {
        getScoreboard().removeLabel(this);
    }

    @Override
    public void updateTime() {
        setTime(getTime() + 1);
    }

    @Override
    public boolean isComplete() {
        return match.isOver();
    }
}
