package org.mozzartbet.hackathon.observers;

import org.mozzartbet.hackathon.Player;
import org.mozzartbet.hackathon.TableType;
import org.mozzartbet.hackathon.Card;

import java.util.List;

/**
 * Created by hash on 9/4/15.
 */
public interface IActObserver {

    public void playerStartThinking(Player player);

    public void playerStopThinking(Player player);

    public void joinedTable(TableType type, int bigBlind, List<Player> players);

    public void messageReceived(String message);

    public void handStarted(Player dealer);

    public void actorRotated(Player actor);

    public void boardUpdated(List<Card> cards, int bet, int pot);

    public void playerUpdated(Player player);
}
