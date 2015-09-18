package com.shawckz.ipractice.match;

import java.util.Set;

import com.shawckz.ipractice.match.handle.MatchManager;
import org.bukkit.entity.Player;

public interface PracticeMatch {

    void startMatch(MatchManager matchManager);

    void endMatch();

    boolean isStarted();

    boolean isOver();

    Set<Player> getPlayers();

}
