package com.shawckz.ipractice.scoreboard.practice.state;

import com.shawckz.ipractice.player.IPlayer;
import com.shawckz.ipractice.player.PlayerState;
import com.shawckz.ipractice.queue.Queue;
import com.shawckz.ipractice.scoreboard.internal.XLabel;
import com.shawckz.ipractice.scoreboard.internal.XScoreboard;
import com.shawckz.ipractice.scoreboard.internal.label.BasicLabel;
import com.shawckz.ipractice.scoreboard.practice.label.ValueLabel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;

public class SpawnBoardType implements PracticeBoardType {

    private Set<ValueLabel> valueLabels = new HashSet<>();

    public SpawnBoardType(XScoreboard scoreboard, IPlayer player) {

        valueLabels.add(new ValueLabel(scoreboard, player, 6, ChatColor.GOLD+""+ ChatColor.GRAY + "" +
                ChatColor.STRIKETHROUGH +"", new ValueLabel.CallableValue() {
            @Override
            public String call(IPlayer player) {
                return "-------------------"+ChatColor.GREEN+""+ChatColor.YELLOW;
            }
        }));

        valueLabels.add(new ValueLabel(scoreboard, player, 5, ChatColor.GOLD + "Elo: ", new ValueLabel.CallableValue() {
            @Override
            public String call(IPlayer player) {
                return ChatColor.AQUA+""+player.getAverageElo();
            }
        }));

        valueLabels.add(new ValueLabel(scoreboard, player, 4, ChatColor.GOLD + "Matches: ", new ValueLabel.CallableValue() {
            @Override
            public String call(IPlayer player) {
                return ChatColor.AQUA+""+player.getTotalMatchesAllLadders();
            }
        }));

        valueLabels.add(new ValueLabel(scoreboard, player, 3, ChatColor.GOLD + "Kills: ", new ValueLabel.CallableValue() {
            @Override
            public String call(IPlayer player) {
                return ChatColor.AQUA + "" + player.getKillsAllLadders();
            }
        }));

        valueLabels.add(new ValueLabel(scoreboard, player, 2, ChatColor.GOLD + "Deaths: ", new ValueLabel.CallableValue() {
            @Override
            public String call(IPlayer player) {
                return ChatColor.AQUA + "" + player.getDeathsAllLadders();
            }
        }));

        valueLabels.add(new ValueLabel(scoreboard, player, 1, ChatColor.GRAY + "" +
                ChatColor.STRIKETHROUGH, new ValueLabel.CallableValue() {
            @Override
            public String call(IPlayer player) {
                return "-------------------"+ChatColor.RED+"";
            }
        }));

        for(XLabel label : valueLabels){
            scoreboard.addLabel(label);
        }
    }

    @Override
    public void update(XScoreboard scoreboard) {
        for(ValueLabel label : valueLabels){
            if(!scoreboard.hasLabel(label)){
                scoreboard.addLabel(label);
            }
            label.setVisible(true);
            label.updateValue();
        }
    }

    @Override
    public void remove(XScoreboard scoreboard) {
        for(ValueLabel label : valueLabels){
            scoreboard.removeLabel(label);
        }
    }

    @Override
    public boolean isApplicable(IPlayer player) {
        return player.getState() == PlayerState.AT_SPAWN && !Queue.inAnyQueue(player) && player.getParty() == null;
    }
}
